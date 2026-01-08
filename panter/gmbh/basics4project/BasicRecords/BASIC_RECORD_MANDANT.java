package panter.gmbh.basics4project.BasicRecords;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BASIC_RECORD_MANDANT extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JD_MANDANT";
    public static String IDFIELD   = "ID_MANDANT";
    





	public BASIC_RECORD_MANDANT() throws myException
	{
		super();
		this.set_TABLE_NAME("JD_MANDANT");
	}


	public BASIC_RECORD_MANDANT(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_MANDANT");
	}



	public BASIC_RECORD_MANDANT(BASIC_RECORD_MANDANT recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_MANDANT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(BASIC_RECORD_MANDANT.TABLENAME);
	}


	//2014-04-03
	public BASIC_RECORD_MANDANT(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_MANDANT");
		this.set_Tablename_To_FieldMetaDefs(BASIC_RECORD_MANDANT.TABLENAME);
	}




	public BASIC_RECORD_MANDANT(long lID_Unformated) throws myException
	{
		super("JD_MANDANT","ID_MANDANT",""+lID_Unformated);
		this.set_TABLE_NAME("JD_MANDANT");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(BASIC_RECORD_MANDANT.TABLENAME);
	}

	public BASIC_RECORD_MANDANT(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JD_MANDANT");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT", "ID_MANDANT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(BASIC_RECORD_MANDANT.TABLENAME);
	}
	
	

	public BASIC_RECORD_MANDANT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_MANDANT","ID_MANDANT",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_MANDANT");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(BASIC_RECORD_MANDANT.TABLENAME);

	}


	public BASIC_RECORD_MANDANT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_MANDANT");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT", "ID_MANDANT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(BASIC_RECORD_MANDANT.TABLENAME);
		
		
	}

	
	

	
	
    @Override
	public String get_TABLENAME()
	{
		return BASIC_RECORD_MANDANT.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MANDANT";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MANDANT_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MANDANT_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in BASIC_RECORD_MANDANT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_MANDANT", bibE2.cTO(), "ID_MANDANT="+this.get_ID_MANDANT_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in BASIC_RECORD_MANDANT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_MANDANT", bibE2.cTO(), "ID_MANDANT="+this.get_ID_MANDANT_cUF(), null);
	}
	
	
	/*
	 * 2012-09-18: simples update, nur geaenderte felder 
	 */
	public MyE2_MessageVector UPDATE(boolean bCommit) throws myException
	{
	    //2015-05-06:  geaendert von bibDB.ExecMultiSQLVector() nach this.ExecMultiSQLVector()
		return this.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STD()),bCommit);
	}
	
	
	
	public MyE2_MessageVector UPDATE(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
	    //2015-05-06:  geaendert von bibDB.ExecMultiSQLVector() nach this.ExecMultiSQLVector()
		return this.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STATEMENT(vFieldsNotInUpdate,bOnlyChangedFields)),true);
	}
	
	public MyE2_MessageVector DELETE() throws myException
	{
        //2015-05-06:  geaendert von bibDB.ExecMultiSQLVector() nach this.ExecMultiSQLVector()
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF();
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
			if (S.isFull(this.get_ID_MANDANT_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT", "ID_MANDANT="+this.get_ID_MANDANT_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	

	
	//2014-04-02: factory-klassen
	public static BASIC_RECORD_MANDANT create_Instance() throws myException {
		return new BASIC_RECORD_MANDANT();
	}
	
	public static BASIC_RECORD_MANDANT create_Instance(MyConnection Conn)   throws myException {
		return new BASIC_RECORD_MANDANT(Conn);
    }

	public static BASIC_RECORD_MANDANT create_Instance(BASIC_RECORD_MANDANT recordOrig) {
		return new BASIC_RECORD_MANDANT(recordOrig);
    }

	public static BASIC_RECORD_MANDANT create_Instance(long lID_Unformated) throws myException {
		return new BASIC_RECORD_MANDANT(lID_Unformated);
    }

	public static BASIC_RECORD_MANDANT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new BASIC_RECORD_MANDANT(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static BASIC_RECORD_MANDANT create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new BASIC_RECORD_MANDANT(lID_Unformated, Conn);
	}

	public static BASIC_RECORD_MANDANT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new BASIC_RECORD_MANDANT(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static BASIC_RECORD_MANDANT create_Instance(MyRECORD recordOrig) throws myException {
		return new BASIC_RECORD_MANDANT(recordOrig);	    
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
    public BASIC_RECORD_MANDANT build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
      }
      //return new BASIC_RECORD_MANDANT(this.get_cSQL_4_Build());
      BASIC_RECORD_MANDANT rec = new BASIC_RECORD_MANDANT();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
	
	
	
	
	



		private RECORD_LAND UP_RECORD_LAND_id_land = null;




		private RECLIST_MANDANT_STEUERVERMERK DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant = null ;




		private RECLIST_MANDANT_ZUSATZ DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant = null ;




		private RECORD_WAEHRUNG UP_RECORD_WAEHRUNG_id_waehrung = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dummy = null;


		
		private RECLIST_EMAIL_SEND_SCHABLONE  DOWN_RECLIST_email_send_schablone = null;


	
	/**
	 * References the Field ID_LAND
	 * Falls keine this.get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land==null)
		{
			this.UP_RECORD_LAND_id_land = new RECORD_LAND(this.get_ID_LAND_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land;
	}
	





	/**
	 * References the Field ID_MANDANT 
	 * Falls keine get_ID_MANDANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_STEUERVERMERK get_DOWN_RECORD_LIST_MANDANT_STEUERVERMERK_id_mandant() throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant==null)
		{
			this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant = new RECLIST_MANDANT_STEUERVERMERK (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_STEUERVERMERK WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF()+" ORDER BY ID_MANDANT_STEUERVERMERK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MANDANT 
	 * Falls keine get_ID_MANDANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_STEUERVERMERK get_DOWN_RECORD_LIST_MANDANT_STEUERVERMERK_id_mandant(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_STEUERVERMERK WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant = new RECLIST_MANDANT_STEUERVERMERK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_mandant;
	}


	





	/**
	 * References the Field ID_MANDANT 
	 * Falls keine get_ID_MANDANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_ZUSATZ get_DOWN_RECORD_LIST_MANDANT_ZUSATZ_id_mandant() throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant==null)
		{
			this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant = new RECLIST_MANDANT_ZUSATZ (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF()+" ORDER BY ID_MANDANT_ZUSATZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MANDANT 
	 * Falls keine get_ID_MANDANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_ZUSATZ get_DOWN_RECORD_LIST_MANDANT_ZUSATZ_id_mandant(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant = new RECLIST_MANDANT_ZUSATZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant;
	}


	





	
	/**
	 * References the Field ID_WAEHRUNG
	 * Falls keine this.get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WAEHRUNG get_UP_RECORD_WAEHRUNG_id_waehrung() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WAEHRUNG_id_waehrung==null)
		{
			this.UP_RECORD_WAEHRUNG_id_waehrung = new RECORD_WAEHRUNG(this.get_ID_WAEHRUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WAEHRUNG_id_waehrung;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL_BEZ_DUMMY
	 * Falls keine this.get_ID_ARTIKEL_BEZ_DUMMY_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_BEZ get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dummy() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_DUMMY_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dummy==null)
		{
			this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dummy = new RECORD_ARTIKEL_BEZ(this.get_ID_ARTIKEL_BEZ_DUMMY_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dummy;
	}
	
	
	
	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MANDANT 
	 * Falls keine get_ID_MANDANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_SEND_SCHABLONE get_DOWN_RECORD_LIST_email_send_schablone(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_email_send_schablone==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_SEND_SCHABLONE WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_email_send_schablone = new RECLIST_EMAIL_SEND_SCHABLONE(cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_email_send_schablone;
	}


	
	
	
	
	
	

	public static String FIELD__ALLOW_EDIT_ABZUG_IN_FUHREN_RG = "ALLOW_EDIT_ABZUG_IN_FUHREN_RG";
	public static String FIELD__ALLOW_EDIT_PRICE_IN_FUHREN_RG = "ALLOW_EDIT_PRICE_IN_FUHREN_RG";
	public static String FIELD__ANR1_GLEICHHEIT_FUHRE_STELLEN = "ANR1_GLEICHHEIT_FUHRE_STELLEN";
	public static String FIELD__ANREDE = "ANREDE";
	public static String FIELD__APPEND_ATTACHMENT_PDF_TO_RG = "APPEND_ATTACHMENT_PDF_TO_RG";
	public static String FIELD__AUSSEN_STEUER_VERMERK = "AUSSEN_STEUER_VERMERK";
	public static String FIELD__BELEGDRUCK_BANK = "BELEGDRUCK_BANK";
	public static String FIELD__BELEGDRUCK_BLZ = "BELEGDRUCK_BLZ";
	public static String FIELD__BELEGDRUCK_EMAIL = "BELEGDRUCK_EMAIL";
	public static String FIELD__BELEGDRUCK_HANDELSREG_NR = "BELEGDRUCK_HANDELSREG_NR";
	public static String FIELD__BELEGDRUCK_KONTO = "BELEGDRUCK_KONTO";
	public static String FIELD__BELEGDRUCK_REGISTERGERICHT = "BELEGDRUCK_REGISTERGERICHT";
	public static String FIELD__BELEGDRUCK_STEUERNR = "BELEGDRUCK_STEUERNR";
	public static String FIELD__BELEGDRUCK_TELEFAX = "BELEGDRUCK_TELEFAX";
	public static String FIELD__BELEGDRUCK_TELEFON = "BELEGDRUCK_TELEFON";
	public static String FIELD__BELEGDRUCK_USTID = "BELEGDRUCK_USTID";
	public static String FIELD__BELEGDRUCK_WWW = "BELEGDRUCK_WWW";
	public static String FIELD__BUCHUNGSPREFIX_ABANGEB = "BUCHUNGSPREFIX_ABANGEB";
	public static String FIELD__BUCHUNGSPREFIX_EKK = "BUCHUNGSPREFIX_EKK";
	public static String FIELD__BUCHUNGSPREFIX_GUT = "BUCHUNGSPREFIX_GUT";
	public static String FIELD__BUCHUNGSPREFIX_LIEFANGEB = "BUCHUNGSPREFIX_LIEFANGEB";
	public static String FIELD__BUCHUNGSPREFIX_RECH = "BUCHUNGSPREFIX_RECH";
	public static String FIELD__BUCHUNGSPREFIX_TPA = "BUCHUNGSPREFIX_TPA";
	public static String FIELD__BUCHUNGSPREFIX_VKK = "BUCHUNGSPREFIX_VKK";
	public static String FIELD__COLOR_BACKTEXT_BLUE = "COLOR_BACKTEXT_BLUE";
	public static String FIELD__COLOR_BACKTEXT_GREEN = "COLOR_BACKTEXT_GREEN";
	public static String FIELD__COLOR_BACKTEXT_RED = "COLOR_BACKTEXT_RED";
	public static String FIELD__COLOR_BLUE = "COLOR_BLUE";
	public static String FIELD__COLOR_DIFF = "COLOR_DIFF";
	public static String FIELD__COLOR_GREEN = "COLOR_GREEN";
	public static String FIELD__COLOR_MASK_HIGHLIGHT_BLUE = "COLOR_MASK_HIGHLIGHT_BLUE";
	public static String FIELD__COLOR_MASK_HIGHLIGHT_GREEN = "COLOR_MASK_HIGHLIGHT_GREEN";
	public static String FIELD__COLOR_MASK_HIGHLIGHT_RED = "COLOR_MASK_HIGHLIGHT_RED";
	public static String FIELD__COLOR_POPUP_TITEL_BLUE = "COLOR_POPUP_TITEL_BLUE";
	public static String FIELD__COLOR_POPUP_TITEL_GREEN = "COLOR_POPUP_TITEL_GREEN";
	public static String FIELD__COLOR_POPUP_TITEL_RED = "COLOR_POPUP_TITEL_RED";
	public static String FIELD__COLOR_RED = "COLOR_RED";
	public static String FIELD__EIGENE_ADRESS_ID = "EIGENE_ADRESS_ID";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EU_STEUER_VERMERK = "EU_STEUER_VERMERK";
	public static String FIELD__FILENAME_INTRASTAT_IN = "FILENAME_INTRASTAT_IN";
	public static String FIELD__FILENAME_INTRASTAT_OUT = "FILENAME_INTRASTAT_OUT";
	public static String FIELD__FIRMENBLOCKRECHTSOBEN = "FIRMENBLOCKRECHTSOBEN";
	public static String FIELD__FIRMENBLOCKSEITENFUSS = "FIRMENBLOCKSEITENFUSS";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GRENZE_MENG_DIFF_ABRECH_PROZ = "GRENZE_MENG_DIFF_ABRECH_PROZ";
	public static String FIELD__HAT_ABZUEGE = "HAT_ABZUEGE";
	public static String FIELD__ID_ARTIKEL_BEZ_DUMMY = "ID_ARTIKEL_BEZ_DUMMY";
	public static String FIELD__ID_LAND = "ID_LAND";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_WAEHRUNG = "ID_WAEHRUNG";
	public static String FIELD__INFO = "INFO";
	public static String FIELD__KARENZ_ZAHLFRIST_AB_HEUTE = "KARENZ_ZAHLFRIST_AB_HEUTE";
	public static String FIELD__KORR_ZAHLDAT_WOCHENENDE = "KORR_ZAHLDAT_WOCHENENDE";
	public static String FIELD__KURZNAME = "KURZNAME";
	public static String FIELD__LANDKURZ = "LANDKURZ";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LOGOGROESSE = "LOGOGROESSE";
	public static String FIELD__LOGONAME = "LOGONAME";
	public static String FIELD__LOGOSCHRIFT = "LOGOSCHRIFT";
	public static String FIELD__LOGOTEXT = "LOGOTEXT";
	public static String FIELD__MAILACCOUNT = "MAILACCOUNT";
	public static String FIELD__MAILPASSWORD = "MAILPASSWORD";
	public static String FIELD__MAILSERVER = "MAILSERVER";
	public static String FIELD__MAILUSERNAME = "MAILUSERNAME";
	public static String FIELD__MG_TOLERANZ_EK_KONTRAKT_POS = "MG_TOLERANZ_EK_KONTRAKT_POS";
	public static String FIELD__MG_TOLERANZ_VK_KONTRAKT_POS = "MG_TOLERANZ_VK_KONTRAKT_POS";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__NUMKREIS_DEBITOR_EU_BIS = "NUMKREIS_DEBITOR_EU_BIS";
	public static String FIELD__NUMKREIS_DEBITOR_EU_VON = "NUMKREIS_DEBITOR_EU_VON";
	public static String FIELD__NUMKREIS_DEBITOR_INLAND_BIS = "NUMKREIS_DEBITOR_INLAND_BIS";
	public static String FIELD__NUMKREIS_DEBITOR_INLAND_VON = "NUMKREIS_DEBITOR_INLAND_VON";
	public static String FIELD__NUMKREIS_DEBITOR_NICHT_EU_BIS = "NUMKREIS_DEBITOR_NICHT_EU_BIS";
	public static String FIELD__NUMKREIS_DEBITOR_NICHT_EU_VON = "NUMKREIS_DEBITOR_NICHT_EU_VON";
	public static String FIELD__NUMKREIS_KREDITOR_EU_BIS = "NUMKREIS_KREDITOR_EU_BIS";
	public static String FIELD__NUMKREIS_KREDITOR_EU_VON = "NUMKREIS_KREDITOR_EU_VON";
	public static String FIELD__NUMKREIS_KREDITOR_INLAND_BIS = "NUMKREIS_KREDITOR_INLAND_BIS";
	public static String FIELD__NUMKREIS_KREDITOR_INLAND_VON = "NUMKREIS_KREDITOR_INLAND_VON";
	public static String FIELD__NUMKREIS_KREDITOR_NICHT_EU_BIS = "NUMKREIS_KREDITOR_NICHT_EU_BIS";
	public static String FIELD__NUMKREIS_KREDITOR_NICHT_EU_VON = "NUMKREIS_KREDITOR_NICHT_EU_VON";
	public static String FIELD__ORT = "ORT";
	public static String FIELD__PLZ = "PLZ";
	public static String FIELD__PREISFIND_ANGEB_NUR_GEDRUCKT = "PREISFIND_ANGEB_NUR_GEDRUCKT";
	public static String FIELD__PREISFIND_KONTR_NUR_GEDRUCKT = "PREISFIND_KONTR_NUR_GEDRUCKT";
	public static String FIELD__PREISFREIGABE_AUTO_FUHRE = "PREISFREIGABE_AUTO_FUHRE";
	public static String FIELD__RECHDAT_IST_LEISTUNGSDATUM = "RECHDAT_IST_LEISTUNGSDATUM";
	public static String FIELD__RUNDEN_ABZUGS_MENGEN = "RUNDEN_ABZUGS_MENGEN";
	public static String FIELD__SCHECKDRUCK_BANKNAME = "SCHECKDRUCK_BANKNAME";
	public static String FIELD__SCHECKDRUCK_BLZ = "SCHECKDRUCK_BLZ";
	public static String FIELD__SCHECKDRUCK_KONTO_NR = "SCHECKDRUCK_KONTO_NR";
	public static String FIELD__SCHECKVERMERK_AUF_GUTSCHRIFT = "SCHECKVERMERK_AUF_GUTSCHRIFT";
	public static String FIELD__STEUERFINDUNG_OHNE_EIGENORTE = "STEUERFINDUNG_OHNE_EIGENORTE";
	public static String FIELD__STICHTAG_START_FIBU = "STICHTAG_START_FIBU";
	public static String FIELD__STRASSE = "STRASSE";
	public static String FIELD__TITELUEBERANSCHRIFT = "TITELUEBERANSCHRIFT";
	public static String FIELD__UNTERSCHEIDUNGSNR_INTRASTAT = "UNTERSCHEIDUNGSNR_INTRASTAT";
	public static String FIELD__VERMERK_STEUERFR_DIENSTLEIST = "VERMERK_STEUERFR_DIENSTLEIST";
	public static String FIELD__VORNAME = "VORNAME";
	public static String FIELD__WASSERZEICHEN_FONTNAME = "WASSERZEICHEN_FONTNAME";
	public static String FIELD__WASSERZEICHEN_FONTSIZE = "WASSERZEICHEN_FONTSIZE";
	public static String FIELD__WASSERZEICHEN_ROTATE = "WASSERZEICHEN_ROTATE";
	public static String FIELD__WASSERZEICHEN_TEXT = "WASSERZEICHEN_TEXT";
	public static String FIELD__ZEIGE_MODUL_FAHRPLANERFASSUNG = "ZEIGE_MODUL_FAHRPLANERFASSUNG";
	public static String FIELD__ZEIGE_MODUL_GLOBAL_REPORTS = "ZEIGE_MODUL_GLOBAL_REPORTS";
	public static String FIELD__ZEIGE_MODUL_KALENDER = "ZEIGE_MODUL_KALENDER";
	public static String FIELD__ZEIGE_MODUL_MESSAGES = "ZEIGE_MODUL_MESSAGES";
	public static String FIELD__ZEIGE_MODUL_TODOS = "ZEIGE_MODUL_TODOS";
	public static String FIELD__ZEIGE_MODUL_WORKFLOW = "ZEIGE_MODUL_WORKFLOW";



	public String get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EDIT_ABZUG_IN_FUHREN_RG");
	}

	public String get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_EDIT_ABZUG_IN_FUHREN_RG");	
	}

	public String get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_EDIT_ABZUG_IN_FUHREN_RG");
	}

	public String get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EDIT_ABZUG_IN_FUHREN_RG",cNotNullValue);
	}

	public String get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_EDIT_ABZUG_IN_FUHREN_RG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", calNewValueFormated);
	}
		public boolean is_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EDIT_PRICE_IN_FUHREN_RG");
	}

	public String get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_EDIT_PRICE_IN_FUHREN_RG");	
	}

	public String get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_EDIT_PRICE_IN_FUHREN_RG");
	}

	public String get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EDIT_PRICE_IN_FUHREN_RG",cNotNullValue);
	}

	public String get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_EDIT_PRICE_IN_FUHREN_RG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", calNewValueFormated);
	}
		public boolean is_ALLOW_EDIT_PRICE_IN_FUHREN_RG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_EDIT_PRICE_IN_FUHREN_RG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1_GLEICHHEIT_FUHRE_STELLEN");
	}

	public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cF() throws myException
	{
		return this.get_FormatedValue("ANR1_GLEICHHEIT_FUHRE_STELLEN");	
	}

	public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1_GLEICHHEIT_FUHRE_STELLEN");
	}

	public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1_GLEICHHEIT_FUHRE_STELLEN",cNotNullValue);
	}

	public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1_GLEICHHEIT_FUHRE_STELLEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", calNewValueFormated);
	}
		public Long get_ANR1_GLEICHHEIT_FUHRE_STELLEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ANR1_GLEICHHEIT_FUHRE_STELLEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ANR1_GLEICHHEIT_FUHRE_STELLEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANR1_GLEICHHEIT_FUHRE_STELLEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANR1_GLEICHHEIT_FUHRE_STELLEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANR1_GLEICHHEIT_FUHRE_STELLEN").getDoubleValue();
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
	public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ANR1_GLEICHHEIT_FUHRE_STELLEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANR1_GLEICHHEIT_FUHRE_STELLEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANR1_GLEICHHEIT_FUHRE_STELLEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANR1_GLEICHHEIT_FUHRE_STELLEN").getBigDecimalValue();
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
	
	
	public String get_ANREDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANREDE");
	}

	public String get_ANREDE_cF() throws myException
	{
		return this.get_FormatedValue("ANREDE");	
	}

	public String get_ANREDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANREDE");
	}

	public String get_ANREDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANREDE",cNotNullValue);
	}

	public String get_ANREDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANREDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANREDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANREDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANREDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANREDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANREDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANREDE", calNewValueFormated);
	}
		public String get_APPEND_ATTACHMENT_PDF_TO_RG_cUF() throws myException
	{
		return this.get_UnFormatedValue("APPEND_ATTACHMENT_PDF_TO_RG");
	}

	public String get_APPEND_ATTACHMENT_PDF_TO_RG_cF() throws myException
	{
		return this.get_FormatedValue("APPEND_ATTACHMENT_PDF_TO_RG");	
	}

	public String get_APPEND_ATTACHMENT_PDF_TO_RG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("APPEND_ATTACHMENT_PDF_TO_RG");
	}

	public String get_APPEND_ATTACHMENT_PDF_TO_RG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("APPEND_ATTACHMENT_PDF_TO_RG",cNotNullValue);
	}

	public String get_APPEND_ATTACHMENT_PDF_TO_RG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("APPEND_ATTACHMENT_PDF_TO_RG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", calNewValueFormated);
	}
		public boolean is_APPEND_ATTACHMENT_PDF_TO_RG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_APPEND_ATTACHMENT_PDF_TO_RG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_APPEND_ATTACHMENT_PDF_TO_RG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_APPEND_ATTACHMENT_PDF_TO_RG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	
	public String get_AUSSEN_STEUER_VERMERK_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSSEN_STEUER_VERMERK");
	}

	
	
	public String get_AUSSEN_STEUER_VERMERK_cF() throws myException
	{
		return this.get_FormatedValue("AUSSEN_STEUER_VERMERK");	
	}

	public String get_AUSSEN_STEUER_VERMERK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSSEN_STEUER_VERMERK");
	}

	public String get_AUSSEN_STEUER_VERMERK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSSEN_STEUER_VERMERK",cNotNullValue);
	}

	public String get_AUSSEN_STEUER_VERMERK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSSEN_STEUER_VERMERK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSSEN_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSSEN_STEUER_VERMERK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", calNewValueFormated);
	}
		public String get_BELEGDRUCK_BANK_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_BANK");
	}

	public String get_BELEGDRUCK_BANK_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_BANK");	
	}

	public String get_BELEGDRUCK_BANK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_BANK");
	}

	public String get_BELEGDRUCK_BANK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_BANK",cNotNullValue);
	}

	public String get_BELEGDRUCK_BANK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_BANK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_BANK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_BANK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_BANK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", calNewValueFormated);
	}
		public String get_BELEGDRUCK_BLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_BLZ");
	}

	public String get_BELEGDRUCK_BLZ_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_BLZ");	
	}

	public String get_BELEGDRUCK_BLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_BLZ");
	}

	public String get_BELEGDRUCK_BLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_BLZ",cNotNullValue);
	}

	public String get_BELEGDRUCK_BLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_BLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_BLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_BLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", calNewValueFormated);
	}
		public String get_BELEGDRUCK_EMAIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_EMAIL");
	}

	public String get_BELEGDRUCK_EMAIL_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_EMAIL");	
	}

	public String get_BELEGDRUCK_EMAIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_EMAIL");
	}

	public String get_BELEGDRUCK_EMAIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_EMAIL",cNotNullValue);
	}

	public String get_BELEGDRUCK_EMAIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_EMAIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_EMAIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_EMAIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_EMAIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", calNewValueFormated);
	}
		public String get_BELEGDRUCK_HANDELSREG_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_HANDELSREG_NR");
	}

	public String get_BELEGDRUCK_HANDELSREG_NR_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_HANDELSREG_NR");	
	}

	public String get_BELEGDRUCK_HANDELSREG_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_HANDELSREG_NR");
	}

	public String get_BELEGDRUCK_HANDELSREG_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_HANDELSREG_NR",cNotNullValue);
	}

	public String get_BELEGDRUCK_HANDELSREG_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_HANDELSREG_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", calNewValueFormated);
	}
		public String get_BELEGDRUCK_KONTO_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_KONTO");
	}

	public String get_BELEGDRUCK_KONTO_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_KONTO");	
	}

	public String get_BELEGDRUCK_KONTO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_KONTO");
	}

	public String get_BELEGDRUCK_KONTO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_KONTO",cNotNullValue);
	}

	public String get_BELEGDRUCK_KONTO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_KONTO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_KONTO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_KONTO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_KONTO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", calNewValueFormated);
	}
		public String get_BELEGDRUCK_REGISTERGERICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_REGISTERGERICHT");
	}

	public String get_BELEGDRUCK_REGISTERGERICHT_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_REGISTERGERICHT");	
	}

	public String get_BELEGDRUCK_REGISTERGERICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_REGISTERGERICHT");
	}

	public String get_BELEGDRUCK_REGISTERGERICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_REGISTERGERICHT",cNotNullValue);
	}

	public String get_BELEGDRUCK_REGISTERGERICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_REGISTERGERICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", calNewValueFormated);
	}
		public String get_BELEGDRUCK_STEUERNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_STEUERNR");
	}

	public String get_BELEGDRUCK_STEUERNR_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_STEUERNR");	
	}

	public String get_BELEGDRUCK_STEUERNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_STEUERNR");
	}

	public String get_BELEGDRUCK_STEUERNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_STEUERNR",cNotNullValue);
	}

	public String get_BELEGDRUCK_STEUERNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_STEUERNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_STEUERNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_STEUERNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", calNewValueFormated);
	}
		public String get_BELEGDRUCK_TELEFAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_TELEFAX");
	}

	public String get_BELEGDRUCK_TELEFAX_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_TELEFAX");	
	}

	public String get_BELEGDRUCK_TELEFAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_TELEFAX");
	}

	public String get_BELEGDRUCK_TELEFAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_TELEFAX",cNotNullValue);
	}

	public String get_BELEGDRUCK_TELEFAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_TELEFAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_TELEFAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_TELEFAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", calNewValueFormated);
	}
		public String get_BELEGDRUCK_TELEFON_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_TELEFON");
	}

	public String get_BELEGDRUCK_TELEFON_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_TELEFON");	
	}

	public String get_BELEGDRUCK_TELEFON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_TELEFON");
	}

	public String get_BELEGDRUCK_TELEFON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_TELEFON",cNotNullValue);
	}

	public String get_BELEGDRUCK_TELEFON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_TELEFON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_TELEFON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_TELEFON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_TELEFON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", calNewValueFormated);
	}
		public String get_BELEGDRUCK_USTID_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_USTID");
	}

	public String get_BELEGDRUCK_USTID_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_USTID");	
	}

	public String get_BELEGDRUCK_USTID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_USTID");
	}

	public String get_BELEGDRUCK_USTID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_USTID",cNotNullValue);
	}

	public String get_BELEGDRUCK_USTID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_USTID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_USTID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_USTID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_USTID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", calNewValueFormated);
	}
		public String get_BELEGDRUCK_WWW_cUF() throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_WWW");
	}

	public String get_BELEGDRUCK_WWW_cF() throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_WWW");	
	}

	public String get_BELEGDRUCK_WWW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BELEGDRUCK_WWW");
	}

	public String get_BELEGDRUCK_WWW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BELEGDRUCK_WWW",cNotNullValue);
	}

	public String get_BELEGDRUCK_WWW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BELEGDRUCK_WWW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BELEGDRUCK_WWW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_WWW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BELEGDRUCK_WWW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_ABANGEB_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_ABANGEB");
	}

	public String get_BUCHUNGSPREFIX_ABANGEB_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_ABANGEB");	
	}

	public String get_BUCHUNGSPREFIX_ABANGEB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_ABANGEB");
	}

	public String get_BUCHUNGSPREFIX_ABANGEB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_ABANGEB",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_ABANGEB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_ABANGEB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_EKK_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_EKK");
	}

	public String get_BUCHUNGSPREFIX_EKK_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_EKK");	
	}

	public String get_BUCHUNGSPREFIX_EKK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_EKK");
	}

	public String get_BUCHUNGSPREFIX_EKK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_EKK",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_EKK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_EKK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_EKK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_EKK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_GUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_GUT");
	}

	public String get_BUCHUNGSPREFIX_GUT_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_GUT");	
	}

	public String get_BUCHUNGSPREFIX_GUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_GUT");
	}

	public String get_BUCHUNGSPREFIX_GUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_GUT",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_GUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_GUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_GUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_GUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_LIEFANGEB_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_LIEFANGEB");
	}

	public String get_BUCHUNGSPREFIX_LIEFANGEB_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_LIEFANGEB");	
	}

	public String get_BUCHUNGSPREFIX_LIEFANGEB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_LIEFANGEB");
	}

	public String get_BUCHUNGSPREFIX_LIEFANGEB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_LIEFANGEB",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_LIEFANGEB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_LIEFANGEB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_RECH_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_RECH");
	}

	public String get_BUCHUNGSPREFIX_RECH_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_RECH");	
	}

	public String get_BUCHUNGSPREFIX_RECH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_RECH");
	}

	public String get_BUCHUNGSPREFIX_RECH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_RECH",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_RECH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_RECH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_RECH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_RECH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_TPA_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_TPA");
	}

	public String get_BUCHUNGSPREFIX_TPA_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_TPA");	
	}

	public String get_BUCHUNGSPREFIX_TPA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_TPA");
	}

	public String get_BUCHUNGSPREFIX_TPA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_TPA",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_TPA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_TPA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_TPA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_TPA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", calNewValueFormated);
	}
		public String get_BUCHUNGSPREFIX_VKK_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_VKK");
	}

	public String get_BUCHUNGSPREFIX_VKK_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_VKK");	
	}

	public String get_BUCHUNGSPREFIX_VKK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSPREFIX_VKK");
	}

	public String get_BUCHUNGSPREFIX_VKK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSPREFIX_VKK",cNotNullValue);
	}

	public String get_BUCHUNGSPREFIX_VKK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSPREFIX_VKK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_VKK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSPREFIX_VKK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", calNewValueFormated);
	}
		public String get_COLOR_BLUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_BLUE");
	}

	public String get_COLOR_BLUE_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_BLUE");	
	}

	public String get_COLOR_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_BLUE");
	}

	public String get_COLOR_BLUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_BLUE",cNotNullValue);
	}

	public String get_COLOR_BLUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_BLUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_BLUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_BLUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_BLUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_BLUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_BLUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_BLUE", calNewValueFormated);
	}
		public Long get_COLOR_BLUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_BLUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_BLUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_BLUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_BLUE").getDoubleValue();
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
	public String get_COLOR_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_BLUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_BLUE").getBigDecimalValue();
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
	
	
	public String get_COLOR_DIFF_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_DIFF");
	}

	public String get_COLOR_DIFF_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_DIFF");	
	}

	public String get_COLOR_DIFF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_DIFF");
	}

	public String get_COLOR_DIFF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_DIFF",cNotNullValue);
	}

	public String get_COLOR_DIFF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_DIFF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_DIFF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_DIFF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_DIFF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_DIFF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_DIFF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_DIFF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_DIFF", calNewValueFormated);
	}
		public Long get_COLOR_DIFF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_DIFF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_DIFF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_DIFF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_DIFF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_DIFF").getDoubleValue();
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
	public String get_COLOR_DIFF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_DIFF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_DIFF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_DIFF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_DIFF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_DIFF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_DIFF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_DIFF").getBigDecimalValue();
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
	
	
	public String get_COLOR_GREEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_GREEN");
	}

	public String get_COLOR_GREEN_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_GREEN");	
	}

	public String get_COLOR_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_GREEN");
	}

	public String get_COLOR_GREEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_GREEN",cNotNullValue);
	}

	public String get_COLOR_GREEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_GREEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_GREEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_GREEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_GREEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_GREEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_GREEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_GREEN", calNewValueFormated);
	}
		public Long get_COLOR_GREEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_GREEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_GREEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_GREEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_GREEN").getDoubleValue();
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
	public String get_COLOR_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_GREEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_GREEN").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_HIGHLIGHT_BLUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_HIGHLIGHT_BLUE");
	}

	public String get_COLOR_MASK_HIGHLIGHT_BLUE_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_HIGHLIGHT_BLUE");	
	}

	public String get_COLOR_MASK_HIGHLIGHT_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_HIGHLIGHT_BLUE");
	}

	public String get_COLOR_MASK_HIGHLIGHT_BLUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_HIGHLIGHT_BLUE",cNotNullValue);
	}

	public String get_COLOR_MASK_HIGHLIGHT_BLUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_HIGHLIGHT_BLUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", calNewValueFormated);
	}
		public Long get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_HIGHLIGHT_BLUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_HIGHLIGHT_BLUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_HIGHLIGHT_BLUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_HIGHLIGHT_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_HIGHLIGHT_BLUE").getDoubleValue();
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
	public String get_COLOR_MASK_HIGHLIGHT_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_HIGHLIGHT_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_MASK_HIGHLIGHT_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_HIGHLIGHT_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_MASK_HIGHLIGHT_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_HIGHLIGHT_BLUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_HIGHLIGHT_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_HIGHLIGHT_BLUE").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_HIGHLIGHT_GREEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_HIGHLIGHT_GREEN");
	}

	public String get_COLOR_MASK_HIGHLIGHT_GREEN_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_HIGHLIGHT_GREEN");	
	}

	public String get_COLOR_MASK_HIGHLIGHT_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_HIGHLIGHT_GREEN");
	}

	public String get_COLOR_MASK_HIGHLIGHT_GREEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_HIGHLIGHT_GREEN",cNotNullValue);
	}

	public String get_COLOR_MASK_HIGHLIGHT_GREEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_HIGHLIGHT_GREEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", calNewValueFormated);
	}
		public Long get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_HIGHLIGHT_GREEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_HIGHLIGHT_GREEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_HIGHLIGHT_GREEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_HIGHLIGHT_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_HIGHLIGHT_GREEN").getDoubleValue();
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
	public String get_COLOR_MASK_HIGHLIGHT_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_HIGHLIGHT_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_MASK_HIGHLIGHT_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_HIGHLIGHT_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_MASK_HIGHLIGHT_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_HIGHLIGHT_GREEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_HIGHLIGHT_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_HIGHLIGHT_GREEN").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_HIGHLIGHT_RED_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_HIGHLIGHT_RED");
	}

	public String get_COLOR_MASK_HIGHLIGHT_RED_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_HIGHLIGHT_RED");	
	}

	public String get_COLOR_MASK_HIGHLIGHT_RED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_HIGHLIGHT_RED");
	}

	public String get_COLOR_MASK_HIGHLIGHT_RED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_HIGHLIGHT_RED",cNotNullValue);
	}

	public String get_COLOR_MASK_HIGHLIGHT_RED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_HIGHLIGHT_RED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", calNewValueFormated);
	}
		public Long get_COLOR_MASK_HIGHLIGHT_RED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_HIGHLIGHT_RED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_HIGHLIGHT_RED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_HIGHLIGHT_RED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_HIGHLIGHT_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_HIGHLIGHT_RED").getDoubleValue();
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
	public String get_COLOR_MASK_HIGHLIGHT_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_HIGHLIGHT_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_MASK_HIGHLIGHT_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_HIGHLIGHT_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_MASK_HIGHLIGHT_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_HIGHLIGHT_RED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_HIGHLIGHT_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_HIGHLIGHT_RED").getBigDecimalValue();
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
	
	
	public String get_COLOR_POPUP_TITEL_BLUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_POPUP_TITEL_BLUE");
	}

	public String get_COLOR_POPUP_TITEL_BLUE_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_POPUP_TITEL_BLUE");	
	}

	public String get_COLOR_POPUP_TITEL_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_POPUP_TITEL_BLUE");
	}

	public String get_COLOR_POPUP_TITEL_BLUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_POPUP_TITEL_BLUE",cNotNullValue);
	}

	public String get_COLOR_POPUP_TITEL_BLUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_POPUP_TITEL_BLUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", calNewValueFormated);
	}
		public Long get_COLOR_POPUP_TITEL_BLUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_POPUP_TITEL_BLUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_POPUP_TITEL_BLUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_POPUP_TITEL_BLUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_POPUP_TITEL_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_POPUP_TITEL_BLUE").getDoubleValue();
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
	public String get_COLOR_POPUP_TITEL_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_POPUP_TITEL_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_POPUP_TITEL_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_POPUP_TITEL_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_POPUP_TITEL_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_POPUP_TITEL_BLUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_POPUP_TITEL_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_POPUP_TITEL_BLUE").getBigDecimalValue();
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
	
	
	public String get_COLOR_POPUP_TITEL_GREEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_POPUP_TITEL_GREEN");
	}

	public String get_COLOR_POPUP_TITEL_GREEN_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_POPUP_TITEL_GREEN");	
	}

	public String get_COLOR_POPUP_TITEL_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_POPUP_TITEL_GREEN");
	}

	public String get_COLOR_POPUP_TITEL_GREEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_POPUP_TITEL_GREEN",cNotNullValue);
	}

	public String get_COLOR_POPUP_TITEL_GREEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_POPUP_TITEL_GREEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", calNewValueFormated);
	}
		public Long get_COLOR_POPUP_TITEL_GREEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_POPUP_TITEL_GREEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_POPUP_TITEL_GREEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_POPUP_TITEL_GREEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_POPUP_TITEL_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_POPUP_TITEL_GREEN").getDoubleValue();
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
	public String get_COLOR_POPUP_TITEL_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_POPUP_TITEL_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_POPUP_TITEL_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_POPUP_TITEL_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_POPUP_TITEL_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_POPUP_TITEL_GREEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_POPUP_TITEL_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_POPUP_TITEL_GREEN").getBigDecimalValue();
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
	
	
	public String get_COLOR_POPUP_TITEL_RED_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_POPUP_TITEL_RED");
	}

	public String get_COLOR_POPUP_TITEL_RED_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_POPUP_TITEL_RED");	
	}

	public String get_COLOR_POPUP_TITEL_RED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_POPUP_TITEL_RED");
	}

	public String get_COLOR_POPUP_TITEL_RED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_POPUP_TITEL_RED",cNotNullValue);
	}

	public String get_COLOR_POPUP_TITEL_RED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_POPUP_TITEL_RED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_POPUP_TITEL_RED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_POPUP_TITEL_RED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", calNewValueFormated);
	}
		public Long get_COLOR_POPUP_TITEL_RED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_POPUP_TITEL_RED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_POPUP_TITEL_RED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_POPUP_TITEL_RED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_POPUP_TITEL_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_POPUP_TITEL_RED").getDoubleValue();
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
	public String get_COLOR_POPUP_TITEL_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_POPUP_TITEL_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_POPUP_TITEL_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_POPUP_TITEL_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_POPUP_TITEL_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_POPUP_TITEL_RED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_POPUP_TITEL_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_POPUP_TITEL_RED").getBigDecimalValue();
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
	
	
	public String get_COLOR_RED_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_RED");
	}

	public String get_COLOR_RED_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_RED");	
	}

	public String get_COLOR_RED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_RED");
	}

	public String get_COLOR_RED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_RED",cNotNullValue);
	}

	public String get_COLOR_RED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_RED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_RED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_RED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_RED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_RED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_RED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_RED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_RED", calNewValueFormated);
	}
		public Long get_COLOR_RED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_RED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_RED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_RED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_RED").getDoubleValue();
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
	public String get_COLOR_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_COLOR_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_COLOR_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_RED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_RED").getBigDecimalValue();
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
	
	
	public String get_EIGENE_ADRESS_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("EIGENE_ADRESS_ID");
	}

	public String get_EIGENE_ADRESS_ID_cF() throws myException
	{
		return this.get_FormatedValue("EIGENE_ADRESS_ID");	
	}

	public String get_EIGENE_ADRESS_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENE_ADRESS_ID");
	}

	public String get_EIGENE_ADRESS_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EIGENE_ADRESS_ID",cNotNullValue);
	}

	public String get_EIGENE_ADRESS_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EIGENE_ADRESS_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EIGENE_ADRESS_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EIGENE_ADRESS_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EIGENE_ADRESS_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", calNewValueFormated);
	}
		public Long get_EIGENE_ADRESS_ID_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("EIGENE_ADRESS_ID").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_EIGENE_ADRESS_ID_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EIGENE_ADRESS_ID").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EIGENE_ADRESS_ID_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EIGENE_ADRESS_ID").getDoubleValue();
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
	public String get_EIGENE_ADRESS_ID_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENE_ADRESS_ID_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EIGENE_ADRESS_ID_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENE_ADRESS_ID_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EIGENE_ADRESS_ID_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EIGENE_ADRESS_ID").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EIGENE_ADRESS_ID_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EIGENE_ADRESS_ID").getBigDecimalValue();
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
		public String get_EU_STEUER_VERMERK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK");
	}

	public String get_EU_STEUER_VERMERK_cF() throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK");	
	}

	public String get_EU_STEUER_VERMERK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_STEUER_VERMERK");
	}

	public String get_EU_STEUER_VERMERK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK",cNotNullValue);
	}

	public String get_EU_STEUER_VERMERK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", calNewValueFormated);
	}
		public String get_FILENAME_INTRASTAT_IN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FILENAME_INTRASTAT_IN");
	}

	public String get_FILENAME_INTRASTAT_IN_cF() throws myException
	{
		return this.get_FormatedValue("FILENAME_INTRASTAT_IN");	
	}

	public String get_FILENAME_INTRASTAT_IN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FILENAME_INTRASTAT_IN");
	}

	public String get_FILENAME_INTRASTAT_IN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FILENAME_INTRASTAT_IN",cNotNullValue);
	}

	public String get_FILENAME_INTRASTAT_IN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FILENAME_INTRASTAT_IN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FILENAME_INTRASTAT_IN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FILENAME_INTRASTAT_IN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", calNewValueFormated);
	}
		public String get_FILENAME_INTRASTAT_OUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FILENAME_INTRASTAT_OUT");
	}

	public String get_FILENAME_INTRASTAT_OUT_cF() throws myException
	{
		return this.get_FormatedValue("FILENAME_INTRASTAT_OUT");	
	}

	public String get_FILENAME_INTRASTAT_OUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FILENAME_INTRASTAT_OUT");
	}

	public String get_FILENAME_INTRASTAT_OUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FILENAME_INTRASTAT_OUT",cNotNullValue);
	}

	public String get_FILENAME_INTRASTAT_OUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FILENAME_INTRASTAT_OUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FILENAME_INTRASTAT_OUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FILENAME_INTRASTAT_OUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", calNewValueFormated);
	}
		public String get_FIRMENBLOCKRECHTSOBEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIRMENBLOCKRECHTSOBEN");
	}

	public String get_FIRMENBLOCKRECHTSOBEN_cF() throws myException
	{
		return this.get_FormatedValue("FIRMENBLOCKRECHTSOBEN");	
	}

	public String get_FIRMENBLOCKRECHTSOBEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIRMENBLOCKRECHTSOBEN");
	}

	public String get_FIRMENBLOCKRECHTSOBEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIRMENBLOCKRECHTSOBEN",cNotNullValue);
	}

	public String get_FIRMENBLOCKRECHTSOBEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIRMENBLOCKRECHTSOBEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", calNewValueFormated);
	}
		public String get_FIRMENBLOCKSEITENFUSS_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIRMENBLOCKSEITENFUSS");
	}

	public String get_FIRMENBLOCKSEITENFUSS_cF() throws myException
	{
		return this.get_FormatedValue("FIRMENBLOCKSEITENFUSS");	
	}

	public String get_FIRMENBLOCKSEITENFUSS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIRMENBLOCKSEITENFUSS");
	}

	public String get_FIRMENBLOCKSEITENFUSS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIRMENBLOCKSEITENFUSS",cNotNullValue);
	}

	public String get_FIRMENBLOCKSEITENFUSS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIRMENBLOCKSEITENFUSS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIRMENBLOCKSEITENFUSS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", calNewValueFormated);
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
		public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("GRENZE_MENG_DIFF_ABRECH_PROZ");
	}

	public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_cF() throws myException
	{
		return this.get_FormatedValue("GRENZE_MENG_DIFF_ABRECH_PROZ");	
	}

	public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GRENZE_MENG_DIFF_ABRECH_PROZ");
	}

	public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GRENZE_MENG_DIFF_ABRECH_PROZ",cNotNullValue);
	}

	public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GRENZE_MENG_DIFF_ABRECH_PROZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", calNewValueFormated);
	}
		public Double get_GRENZE_MENG_DIFF_ABRECH_PROZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GRENZE_MENG_DIFF_ABRECH_PROZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GRENZE_MENG_DIFF_ABRECH_PROZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GRENZE_MENG_DIFF_ABRECH_PROZ").getDoubleValue();
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
	public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GRENZE_MENG_DIFF_ABRECH_PROZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GRENZE_MENG_DIFF_ABRECH_PROZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GRENZE_MENG_DIFF_ABRECH_PROZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GRENZE_MENG_DIFF_ABRECH_PROZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GRENZE_MENG_DIFF_ABRECH_PROZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GRENZE_MENG_DIFF_ABRECH_PROZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GRENZE_MENG_DIFF_ABRECH_PROZ").getBigDecimalValue();
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
	
	
	public String get_HAT_ABZUEGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("HAT_ABZUEGE");
	}

	public String get_HAT_ABZUEGE_cF() throws myException
	{
		return this.get_FormatedValue("HAT_ABZUEGE");	
	}

	public String get_HAT_ABZUEGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAT_ABZUEGE");
	}

	public String get_HAT_ABZUEGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HAT_ABZUEGE",cNotNullValue);
	}

	public String get_HAT_ABZUEGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HAT_ABZUEGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HAT_ABZUEGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HAT_ABZUEGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HAT_ABZUEGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", calNewValueFormated);
	}
		public boolean is_HAT_ABZUEGE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_HAT_ABZUEGE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_HAT_ABZUEGE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_HAT_ABZUEGE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_ARTIKEL_BEZ_DUMMY_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_DUMMY");
	}

	public String get_ID_ARTIKEL_BEZ_DUMMY_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_DUMMY");	
	}

	public String get_ID_ARTIKEL_BEZ_DUMMY_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_BEZ_DUMMY");
	}

	public String get_ID_ARTIKEL_BEZ_DUMMY_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_DUMMY",cNotNullValue);
	}

	public String get_ID_ARTIKEL_BEZ_DUMMY_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_DUMMY",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_BEZ_DUMMY_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_BEZ_DUMMY").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_BEZ_DUMMY_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_DUMMY").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_BEZ_DUMMY_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_DUMMY").getDoubleValue();
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
	public String get_ID_ARTIKEL_BEZ_DUMMY_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_DUMMY_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_BEZ_DUMMY_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_DUMMY_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_BEZ_DUMMY_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_DUMMY").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_BEZ_DUMMY_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_DUMMY").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND");
	}

	public String get_ID_LAND_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND");	
	}

	public String get_ID_LAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND");
	}

	public String get_ID_LAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND",cNotNullValue);
	}

	public String get_ID_LAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", calNewValueFormated);
	}
		public Long get_ID_LAND_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND").getDoubleValue();
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
	public String get_ID_LAND_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAND_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAND_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND").getBigDecimalValue();
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
	
	
	public String get_ID_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG");
	}

	public String get_ID_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG");	
	}

	public String get_ID_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAEHRUNG");
	}

	public String get_ID_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG",cNotNullValue);
	}

	public String get_ID_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", calNewValueFormated);
	}
		public Long get_ID_WAEHRUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAEHRUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG").getDoubleValue();
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
	public String get_ID_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG").getBigDecimalValue();
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
	
	
	public String get_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("INFO");
	}

	public String get_INFO_cF() throws myException
	{
		return this.get_FormatedValue("INFO");	
	}

	public String get_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INFO");
	}

	public String get_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INFO",cNotNullValue);
	}

	public String get_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INFO", calNewValueFormated);
	}
		public String get_KARENZ_ZAHLFRIST_AB_HEUTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KARENZ_ZAHLFRIST_AB_HEUTE");
	}

	public String get_KARENZ_ZAHLFRIST_AB_HEUTE_cF() throws myException
	{
		return this.get_FormatedValue("KARENZ_ZAHLFRIST_AB_HEUTE");	
	}

	public String get_KARENZ_ZAHLFRIST_AB_HEUTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KARENZ_ZAHLFRIST_AB_HEUTE");
	}

	public String get_KARENZ_ZAHLFRIST_AB_HEUTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KARENZ_ZAHLFRIST_AB_HEUTE",cNotNullValue);
	}

	public String get_KARENZ_ZAHLFRIST_AB_HEUTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KARENZ_ZAHLFRIST_AB_HEUTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", calNewValueFormated);
	}
		public Long get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KARENZ_ZAHLFRIST_AB_HEUTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KARENZ_ZAHLFRIST_AB_HEUTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KARENZ_ZAHLFRIST_AB_HEUTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KARENZ_ZAHLFRIST_AB_HEUTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KARENZ_ZAHLFRIST_AB_HEUTE").getDoubleValue();
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
	public String get_KARENZ_ZAHLFRIST_AB_HEUTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KARENZ_ZAHLFRIST_AB_HEUTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KARENZ_ZAHLFRIST_AB_HEUTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KARENZ_ZAHLFRIST_AB_HEUTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KARENZ_ZAHLFRIST_AB_HEUTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KARENZ_ZAHLFRIST_AB_HEUTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KARENZ_ZAHLFRIST_AB_HEUTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KARENZ_ZAHLFRIST_AB_HEUTE").getBigDecimalValue();
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
	
	
	public String get_KORR_ZAHLDAT_WOCHENENDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KORR_ZAHLDAT_WOCHENENDE");
	}

	public String get_KORR_ZAHLDAT_WOCHENENDE_cF() throws myException
	{
		return this.get_FormatedValue("KORR_ZAHLDAT_WOCHENENDE");	
	}

	public String get_KORR_ZAHLDAT_WOCHENENDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KORR_ZAHLDAT_WOCHENENDE");
	}

	public String get_KORR_ZAHLDAT_WOCHENENDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KORR_ZAHLDAT_WOCHENENDE",cNotNullValue);
	}

	public String get_KORR_ZAHLDAT_WOCHENENDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KORR_ZAHLDAT_WOCHENENDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", calNewValueFormated);
	}
		public boolean is_KORR_ZAHLDAT_WOCHENENDE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KORR_ZAHLDAT_WOCHENENDE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KORR_ZAHLDAT_WOCHENENDE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KORR_ZAHLDAT_WOCHENENDE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KURZNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("KURZNAME");
	}

	public String get_KURZNAME_cF() throws myException
	{
		return this.get_FormatedValue("KURZNAME");	
	}

	public String get_KURZNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KURZNAME");
	}

	public String get_KURZNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KURZNAME",cNotNullValue);
	}

	public String get_KURZNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KURZNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KURZNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KURZNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KURZNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KURZNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KURZNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KURZNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KURZNAME", calNewValueFormated);
	}
		public String get_LANDKURZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("LANDKURZ");
	}

	public String get_LANDKURZ_cF() throws myException
	{
		return this.get_FormatedValue("LANDKURZ");	
	}

	public String get_LANDKURZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LANDKURZ");
	}

	public String get_LANDKURZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LANDKURZ",cNotNullValue);
	}

	public String get_LANDKURZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LANDKURZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LANDKURZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LANDKURZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LANDKURZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LANDKURZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LANDKURZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LANDKURZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LANDKURZ", calNewValueFormated);
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
		public String get_LOGOGROESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOGOGROESSE");
	}

	public String get_LOGOGROESSE_cF() throws myException
	{
		return this.get_FormatedValue("LOGOGROESSE");	
	}

	public String get_LOGOGROESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOGOGROESSE");
	}

	public String get_LOGOGROESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOGOGROESSE",cNotNullValue);
	}

	public String get_LOGOGROESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOGOGROESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOGOGROESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOGOGROESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOGOGROESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOGOGROESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOGROESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOGROESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOGROESSE", calNewValueFormated);
	}
		public Long get_LOGOGROESSE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LOGOGROESSE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LOGOGROESSE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LOGOGROESSE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LOGOGROESSE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LOGOGROESSE").getDoubleValue();
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
	public String get_LOGOGROESSE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LOGOGROESSE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LOGOGROESSE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LOGOGROESSE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LOGOGROESSE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LOGOGROESSE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LOGOGROESSE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LOGOGROESSE").getBigDecimalValue();
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
	
	
	public String get_LOGONAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOGONAME");
	}

	public String get_LOGONAME_cF() throws myException
	{
		return this.get_FormatedValue("LOGONAME");	
	}

	public String get_LOGONAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOGONAME");
	}

	public String get_LOGONAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOGONAME",cNotNullValue);
	}

	public String get_LOGONAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOGONAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGONAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOGONAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOGONAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOGONAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGONAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOGONAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGONAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGONAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGONAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGONAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGONAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGONAME", calNewValueFormated);
	}
		public String get_LOGOSCHRIFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOGOSCHRIFT");
	}

	public String get_LOGOSCHRIFT_cF() throws myException
	{
		return this.get_FormatedValue("LOGOSCHRIFT");	
	}

	public String get_LOGOSCHRIFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOGOSCHRIFT");
	}

	public String get_LOGOSCHRIFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOGOSCHRIFT",cNotNullValue);
	}

	public String get_LOGOSCHRIFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOGOSCHRIFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOGOSCHRIFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOGOSCHRIFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOGOSCHRIFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", calNewValueFormated);
	}
		public String get_LOGOTEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOGOTEXT");
	}

	public String get_LOGOTEXT_cF() throws myException
	{
		return this.get_FormatedValue("LOGOTEXT");	
	}

	public String get_LOGOTEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOGOTEXT");
	}

	public String get_LOGOTEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOGOTEXT",cNotNullValue);
	}

	public String get_LOGOTEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOGOTEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOGOTEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOGOTEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOGOTEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOGOTEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOTEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOTEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOGOTEXT", calNewValueFormated);
	}
		public String get_MAILACCOUNT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILACCOUNT");
	}

	public String get_MAILACCOUNT_cF() throws myException
	{
		return this.get_FormatedValue("MAILACCOUNT");	
	}

	public String get_MAILACCOUNT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILACCOUNT");
	}

	public String get_MAILACCOUNT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILACCOUNT",cNotNullValue);
	}

	public String get_MAILACCOUNT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILACCOUNT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILACCOUNT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILACCOUNT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILACCOUNT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILACCOUNT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILACCOUNT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILACCOUNT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILACCOUNT", calNewValueFormated);
	}
		public String get_MAILPASSWORD_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILPASSWORD");
	}

	public String get_MAILPASSWORD_cF() throws myException
	{
		return this.get_FormatedValue("MAILPASSWORD");	
	}

	public String get_MAILPASSWORD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILPASSWORD");
	}

	public String get_MAILPASSWORD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILPASSWORD",cNotNullValue);
	}

	public String get_MAILPASSWORD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILPASSWORD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILPASSWORD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILPASSWORD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILPASSWORD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILPASSWORD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILPASSWORD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILPASSWORD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILPASSWORD", calNewValueFormated);
	}
		public String get_MAILSERVER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILSERVER");
	}

	public String get_MAILSERVER_cF() throws myException
	{
		return this.get_FormatedValue("MAILSERVER");	
	}

	public String get_MAILSERVER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILSERVER");
	}

	public String get_MAILSERVER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILSERVER",cNotNullValue);
	}

	public String get_MAILSERVER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILSERVER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILSERVER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILSERVER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILSERVER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILSERVER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILSERVER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILSERVER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILSERVER", calNewValueFormated);
	}
		public String get_MAILUSERNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILUSERNAME");
	}

	public String get_MAILUSERNAME_cF() throws myException
	{
		return this.get_FormatedValue("MAILUSERNAME");	
	}

	public String get_MAILUSERNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILUSERNAME");
	}

	public String get_MAILUSERNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILUSERNAME",cNotNullValue);
	}

	public String get_MAILUSERNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILUSERNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILUSERNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILUSERNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILUSERNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILUSERNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILUSERNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILUSERNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILUSERNAME", calNewValueFormated);
	}
		public String get_MG_TOLERANZ_EK_KONTRAKT_POS_cUF() throws myException
	{
		return this.get_UnFormatedValue("MG_TOLERANZ_EK_KONTRAKT_POS");
	}

	public String get_MG_TOLERANZ_EK_KONTRAKT_POS_cF() throws myException
	{
		return this.get_FormatedValue("MG_TOLERANZ_EK_KONTRAKT_POS");	
	}

	public String get_MG_TOLERANZ_EK_KONTRAKT_POS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MG_TOLERANZ_EK_KONTRAKT_POS");
	}

	public String get_MG_TOLERANZ_EK_KONTRAKT_POS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MG_TOLERANZ_EK_KONTRAKT_POS",cNotNullValue);
	}

	public String get_MG_TOLERANZ_EK_KONTRAKT_POS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MG_TOLERANZ_EK_KONTRAKT_POS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", calNewValueFormated);
	}
		public Long get_MG_TOLERANZ_EK_KONTRAKT_POS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MG_TOLERANZ_EK_KONTRAKT_POS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MG_TOLERANZ_EK_KONTRAKT_POS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MG_TOLERANZ_EK_KONTRAKT_POS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MG_TOLERANZ_EK_KONTRAKT_POS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MG_TOLERANZ_EK_KONTRAKT_POS").getDoubleValue();
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
	public String get_MG_TOLERANZ_EK_KONTRAKT_POS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MG_TOLERANZ_EK_KONTRAKT_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MG_TOLERANZ_EK_KONTRAKT_POS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MG_TOLERANZ_EK_KONTRAKT_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MG_TOLERANZ_EK_KONTRAKT_POS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MG_TOLERANZ_EK_KONTRAKT_POS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MG_TOLERANZ_EK_KONTRAKT_POS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MG_TOLERANZ_EK_KONTRAKT_POS").getBigDecimalValue();
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
	
	
	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_cUF() throws myException
	{
		return this.get_UnFormatedValue("MG_TOLERANZ_VK_KONTRAKT_POS");
	}

	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_cF() throws myException
	{
		return this.get_FormatedValue("MG_TOLERANZ_VK_KONTRAKT_POS");	
	}

	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MG_TOLERANZ_VK_KONTRAKT_POS");
	}

	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MG_TOLERANZ_VK_KONTRAKT_POS",cNotNullValue);
	}

	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MG_TOLERANZ_VK_KONTRAKT_POS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", calNewValueFormated);
	}
		public Long get_MG_TOLERANZ_VK_KONTRAKT_POS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MG_TOLERANZ_VK_KONTRAKT_POS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MG_TOLERANZ_VK_KONTRAKT_POS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MG_TOLERANZ_VK_KONTRAKT_POS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MG_TOLERANZ_VK_KONTRAKT_POS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MG_TOLERANZ_VK_KONTRAKT_POS").getDoubleValue();
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
	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MG_TOLERANZ_VK_KONTRAKT_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MG_TOLERANZ_VK_KONTRAKT_POS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MG_TOLERANZ_VK_KONTRAKT_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MG_TOLERANZ_VK_KONTRAKT_POS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MG_TOLERANZ_VK_KONTRAKT_POS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MG_TOLERANZ_VK_KONTRAKT_POS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MG_TOLERANZ_VK_KONTRAKT_POS").getBigDecimalValue();
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
	
	
	public String get_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME1");
	}

	public String get_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("NAME1");	
	}

	public String get_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME1");
	}

	public String get_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME1",cNotNullValue);
	}

	public String get_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public String get_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME2");
	}

	public String get_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("NAME2");	
	}

	public String get_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME2");
	}

	public String get_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME2",cNotNullValue);
	}

	public String get_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public String get_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME3");
	}

	public String get_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("NAME3");	
	}

	public String get_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME3");
	}

	public String get_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME3",cNotNullValue);
	}

	public String get_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public String get_NUMKREIS_DEBITOR_EU_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_EU_BIS");
	}

	public String get_NUMKREIS_DEBITOR_EU_BIS_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_EU_BIS");	
	}

	public String get_NUMKREIS_DEBITOR_EU_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_DEBITOR_EU_BIS");
	}

	public String get_NUMKREIS_DEBITOR_EU_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_EU_BIS",cNotNullValue);
	}

	public String get_NUMKREIS_DEBITOR_EU_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_EU_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", calNewValueFormated);
	}
		public Long get_NUMKREIS_DEBITOR_EU_BIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_DEBITOR_EU_BIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_DEBITOR_EU_BIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_EU_BIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_DEBITOR_EU_BIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_EU_BIS").getDoubleValue();
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
	public String get_NUMKREIS_DEBITOR_EU_BIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_DEBITOR_EU_BIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_DEBITOR_EU_BIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_EU_BIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_DEBITOR_EU_BIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_EU_BIS").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_DEBITOR_EU_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_EU_VON");
	}

	public String get_NUMKREIS_DEBITOR_EU_VON_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_EU_VON");	
	}

	public String get_NUMKREIS_DEBITOR_EU_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_DEBITOR_EU_VON");
	}

	public String get_NUMKREIS_DEBITOR_EU_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_EU_VON",cNotNullValue);
	}

	public String get_NUMKREIS_DEBITOR_EU_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_EU_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", calNewValueFormated);
	}
		public Long get_NUMKREIS_DEBITOR_EU_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_DEBITOR_EU_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_DEBITOR_EU_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_EU_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_DEBITOR_EU_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_EU_VON").getDoubleValue();
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
	public String get_NUMKREIS_DEBITOR_EU_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_DEBITOR_EU_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_DEBITOR_EU_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_EU_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_DEBITOR_EU_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_EU_VON").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_DEBITOR_INLAND_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_INLAND_BIS");
	}

	public String get_NUMKREIS_DEBITOR_INLAND_BIS_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_INLAND_BIS");	
	}

	public String get_NUMKREIS_DEBITOR_INLAND_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_DEBITOR_INLAND_BIS");
	}

	public String get_NUMKREIS_DEBITOR_INLAND_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_INLAND_BIS",cNotNullValue);
	}

	public String get_NUMKREIS_DEBITOR_INLAND_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_INLAND_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", calNewValueFormated);
	}
		public Long get_NUMKREIS_DEBITOR_INLAND_BIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_DEBITOR_INLAND_BIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_DEBITOR_INLAND_BIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_INLAND_BIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_DEBITOR_INLAND_BIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_INLAND_BIS").getDoubleValue();
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
	public String get_NUMKREIS_DEBITOR_INLAND_BIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_INLAND_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_DEBITOR_INLAND_BIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_INLAND_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_DEBITOR_INLAND_BIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_INLAND_BIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_DEBITOR_INLAND_BIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_INLAND_BIS").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_DEBITOR_INLAND_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_INLAND_VON");
	}

	public String get_NUMKREIS_DEBITOR_INLAND_VON_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_INLAND_VON");	
	}

	public String get_NUMKREIS_DEBITOR_INLAND_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_DEBITOR_INLAND_VON");
	}

	public String get_NUMKREIS_DEBITOR_INLAND_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_INLAND_VON",cNotNullValue);
	}

	public String get_NUMKREIS_DEBITOR_INLAND_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_INLAND_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", calNewValueFormated);
	}
		public Long get_NUMKREIS_DEBITOR_INLAND_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_DEBITOR_INLAND_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_DEBITOR_INLAND_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_INLAND_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_DEBITOR_INLAND_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_INLAND_VON").getDoubleValue();
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
	public String get_NUMKREIS_DEBITOR_INLAND_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_INLAND_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_DEBITOR_INLAND_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_INLAND_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_DEBITOR_INLAND_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_INLAND_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_DEBITOR_INLAND_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_INLAND_VON").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_NICHT_EU_BIS");
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_NICHT_EU_BIS");	
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_DEBITOR_NICHT_EU_BIS");
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_NICHT_EU_BIS",cNotNullValue);
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_NICHT_EU_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", calNewValueFormated);
	}
		public Long get_NUMKREIS_DEBITOR_NICHT_EU_BIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_BIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_DEBITOR_NICHT_EU_BIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_BIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_DEBITOR_NICHT_EU_BIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_BIS").getDoubleValue();
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
	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_DEBITOR_NICHT_EU_BIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_BIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_DEBITOR_NICHT_EU_BIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_BIS").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_NICHT_EU_VON");
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_NICHT_EU_VON");	
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_DEBITOR_NICHT_EU_VON");
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_DEBITOR_NICHT_EU_VON",cNotNullValue);
	}

	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_DEBITOR_NICHT_EU_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", calNewValueFormated);
	}
		public Long get_NUMKREIS_DEBITOR_NICHT_EU_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_DEBITOR_NICHT_EU_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_DEBITOR_NICHT_EU_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_VON").getDoubleValue();
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
	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_NICHT_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_DEBITOR_NICHT_EU_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_DEBITOR_NICHT_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_DEBITOR_NICHT_EU_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_DEBITOR_NICHT_EU_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_DEBITOR_NICHT_EU_VON").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_KREDITOR_EU_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_EU_BIS");
	}

	public String get_NUMKREIS_KREDITOR_EU_BIS_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_EU_BIS");	
	}

	public String get_NUMKREIS_KREDITOR_EU_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_KREDITOR_EU_BIS");
	}

	public String get_NUMKREIS_KREDITOR_EU_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_EU_BIS",cNotNullValue);
	}

	public String get_NUMKREIS_KREDITOR_EU_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_EU_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", calNewValueFormated);
	}
		public Long get_NUMKREIS_KREDITOR_EU_BIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_KREDITOR_EU_BIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_KREDITOR_EU_BIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_EU_BIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_KREDITOR_EU_BIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_EU_BIS").getDoubleValue();
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
	public String get_NUMKREIS_KREDITOR_EU_BIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_KREDITOR_EU_BIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_KREDITOR_EU_BIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_EU_BIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_KREDITOR_EU_BIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_EU_BIS").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_KREDITOR_EU_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_EU_VON");
	}

	public String get_NUMKREIS_KREDITOR_EU_VON_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_EU_VON");	
	}

	public String get_NUMKREIS_KREDITOR_EU_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_KREDITOR_EU_VON");
	}

	public String get_NUMKREIS_KREDITOR_EU_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_EU_VON",cNotNullValue);
	}

	public String get_NUMKREIS_KREDITOR_EU_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_EU_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", calNewValueFormated);
	}
		public Long get_NUMKREIS_KREDITOR_EU_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_KREDITOR_EU_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_KREDITOR_EU_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_EU_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_KREDITOR_EU_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_EU_VON").getDoubleValue();
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
	public String get_NUMKREIS_KREDITOR_EU_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_KREDITOR_EU_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_KREDITOR_EU_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_EU_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_KREDITOR_EU_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_EU_VON").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_KREDITOR_INLAND_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_INLAND_BIS");
	}

	public String get_NUMKREIS_KREDITOR_INLAND_BIS_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_INLAND_BIS");	
	}

	public String get_NUMKREIS_KREDITOR_INLAND_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_KREDITOR_INLAND_BIS");
	}

	public String get_NUMKREIS_KREDITOR_INLAND_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_INLAND_BIS",cNotNullValue);
	}

	public String get_NUMKREIS_KREDITOR_INLAND_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_INLAND_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", calNewValueFormated);
	}
		public Long get_NUMKREIS_KREDITOR_INLAND_BIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_KREDITOR_INLAND_BIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_KREDITOR_INLAND_BIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_INLAND_BIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_KREDITOR_INLAND_BIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_INLAND_BIS").getDoubleValue();
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
	public String get_NUMKREIS_KREDITOR_INLAND_BIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_INLAND_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_KREDITOR_INLAND_BIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_INLAND_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_KREDITOR_INLAND_BIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_INLAND_BIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_KREDITOR_INLAND_BIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_INLAND_BIS").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_KREDITOR_INLAND_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_INLAND_VON");
	}

	public String get_NUMKREIS_KREDITOR_INLAND_VON_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_INLAND_VON");	
	}

	public String get_NUMKREIS_KREDITOR_INLAND_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_KREDITOR_INLAND_VON");
	}

	public String get_NUMKREIS_KREDITOR_INLAND_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_INLAND_VON",cNotNullValue);
	}

	public String get_NUMKREIS_KREDITOR_INLAND_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_INLAND_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", calNewValueFormated);
	}
		public Long get_NUMKREIS_KREDITOR_INLAND_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_KREDITOR_INLAND_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_KREDITOR_INLAND_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_INLAND_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_KREDITOR_INLAND_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_INLAND_VON").getDoubleValue();
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
	public String get_NUMKREIS_KREDITOR_INLAND_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_INLAND_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_KREDITOR_INLAND_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_INLAND_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_KREDITOR_INLAND_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_INLAND_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_KREDITOR_INLAND_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_INLAND_VON").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_NICHT_EU_BIS");
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_NICHT_EU_BIS");	
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_KREDITOR_NICHT_EU_BIS");
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_NICHT_EU_BIS",cNotNullValue);
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_NICHT_EU_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", calNewValueFormated);
	}
		public Long get_NUMKREIS_KREDITOR_NICHT_EU_BIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_BIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_KREDITOR_NICHT_EU_BIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_BIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_KREDITOR_NICHT_EU_BIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_BIS").getDoubleValue();
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
	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_KREDITOR_NICHT_EU_BIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_BIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_KREDITOR_NICHT_EU_BIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_BIS").getBigDecimalValue();
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
	
	
	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_NICHT_EU_VON");
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_cF() throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_NICHT_EU_VON");	
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMKREIS_KREDITOR_NICHT_EU_VON");
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMKREIS_KREDITOR_NICHT_EU_VON",cNotNullValue);
	}

	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMKREIS_KREDITOR_NICHT_EU_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", calNewValueFormated);
	}
		public Long get_NUMKREIS_KREDITOR_NICHT_EU_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMKREIS_KREDITOR_NICHT_EU_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMKREIS_KREDITOR_NICHT_EU_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_VON").getDoubleValue();
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
	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_NICHT_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMKREIS_KREDITOR_NICHT_EU_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMKREIS_KREDITOR_NICHT_EU_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMKREIS_KREDITOR_NICHT_EU_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMKREIS_KREDITOR_NICHT_EU_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMKREIS_KREDITOR_NICHT_EU_VON").getBigDecimalValue();
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
	
	
	public String get_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORT");
	}

	public String get_ORT_cF() throws myException
	{
		return this.get_FormatedValue("ORT");	
	}

	public String get_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORT");
	}

	public String get_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORT",cNotNullValue);
	}

	public String get_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public String get_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLZ");
	}

	public String get_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("PLZ");	
	}

	public String get_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLZ");
	}

	public String get_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLZ",cNotNullValue);
	}

	public String get_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public String get_PREISFIND_ANGEB_NUR_GEDRUCKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREISFIND_ANGEB_NUR_GEDRUCKT");
	}

	public String get_PREISFIND_ANGEB_NUR_GEDRUCKT_cF() throws myException
	{
		return this.get_FormatedValue("PREISFIND_ANGEB_NUR_GEDRUCKT");	
	}

	public String get_PREISFIND_ANGEB_NUR_GEDRUCKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISFIND_ANGEB_NUR_GEDRUCKT");
	}

	public String get_PREISFIND_ANGEB_NUR_GEDRUCKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREISFIND_ANGEB_NUR_GEDRUCKT",cNotNullValue);
	}

	public String get_PREISFIND_ANGEB_NUR_GEDRUCKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREISFIND_ANGEB_NUR_GEDRUCKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", calNewValueFormated);
	}
		public boolean is_PREISFIND_ANGEB_NUR_GEDRUCKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISFIND_ANGEB_NUR_GEDRUCKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREISFIND_ANGEB_NUR_GEDRUCKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISFIND_ANGEB_NUR_GEDRUCKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREISFIND_KONTR_NUR_GEDRUCKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREISFIND_KONTR_NUR_GEDRUCKT");
	}

	public String get_PREISFIND_KONTR_NUR_GEDRUCKT_cF() throws myException
	{
		return this.get_FormatedValue("PREISFIND_KONTR_NUR_GEDRUCKT");	
	}

	public String get_PREISFIND_KONTR_NUR_GEDRUCKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISFIND_KONTR_NUR_GEDRUCKT");
	}

	public String get_PREISFIND_KONTR_NUR_GEDRUCKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREISFIND_KONTR_NUR_GEDRUCKT",cNotNullValue);
	}

	public String get_PREISFIND_KONTR_NUR_GEDRUCKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREISFIND_KONTR_NUR_GEDRUCKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", calNewValueFormated);
	}
		public boolean is_PREISFIND_KONTR_NUR_GEDRUCKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISFIND_KONTR_NUR_GEDRUCKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREISFIND_KONTR_NUR_GEDRUCKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISFIND_KONTR_NUR_GEDRUCKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREISFREIGABE_AUTO_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREISFREIGABE_AUTO_FUHRE");
	}

	public String get_PREISFREIGABE_AUTO_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("PREISFREIGABE_AUTO_FUHRE");	
	}

	public String get_PREISFREIGABE_AUTO_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISFREIGABE_AUTO_FUHRE");
	}

	public String get_PREISFREIGABE_AUTO_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREISFREIGABE_AUTO_FUHRE",cNotNullValue);
	}

	public String get_PREISFREIGABE_AUTO_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREISFREIGABE_AUTO_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", calNewValueFormated);
	}
		public boolean is_PREISFREIGABE_AUTO_FUHRE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISFREIGABE_AUTO_FUHRE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREISFREIGABE_AUTO_FUHRE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISFREIGABE_AUTO_FUHRE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RECHDAT_IST_LEISTUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECHDAT_IST_LEISTUNGSDATUM");
	}

	public String get_RECHDAT_IST_LEISTUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("RECHDAT_IST_LEISTUNGSDATUM");	
	}

	public String get_RECHDAT_IST_LEISTUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECHDAT_IST_LEISTUNGSDATUM");
	}

	public String get_RECHDAT_IST_LEISTUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECHDAT_IST_LEISTUNGSDATUM",cNotNullValue);
	}

	public String get_RECHDAT_IST_LEISTUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECHDAT_IST_LEISTUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", calNewValueFormated);
	}
		public boolean is_RECHDAT_IST_LEISTUNGSDATUM_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECHDAT_IST_LEISTUNGSDATUM_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RECHDAT_IST_LEISTUNGSDATUM_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECHDAT_IST_LEISTUNGSDATUM_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RUNDEN_ABZUGS_MENGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("RUNDEN_ABZUGS_MENGEN");
	}

	public String get_RUNDEN_ABZUGS_MENGEN_cF() throws myException
	{
		return this.get_FormatedValue("RUNDEN_ABZUGS_MENGEN");	
	}

	public String get_RUNDEN_ABZUGS_MENGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RUNDEN_ABZUGS_MENGEN");
	}

	public String get_RUNDEN_ABZUGS_MENGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RUNDEN_ABZUGS_MENGEN",cNotNullValue);
	}

	public String get_RUNDEN_ABZUGS_MENGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RUNDEN_ABZUGS_MENGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", calNewValueFormated);
	}
		public boolean is_RUNDEN_ABZUGS_MENGEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RUNDEN_ABZUGS_MENGEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RUNDEN_ABZUGS_MENGEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RUNDEN_ABZUGS_MENGEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SCHECKDRUCK_BANKNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_BANKNAME");
	}

	public String get_SCHECKDRUCK_BANKNAME_cF() throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_BANKNAME");	
	}

	public String get_SCHECKDRUCK_BANKNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECKDRUCK_BANKNAME");
	}

	public String get_SCHECKDRUCK_BANKNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_BANKNAME",cNotNullValue);
	}

	public String get_SCHECKDRUCK_BANKNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_BANKNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_BANKNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECKDRUCK_BANKNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", calNewValueFormated);
	}
		public String get_SCHECKDRUCK_BLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_BLZ");
	}

	public String get_SCHECKDRUCK_BLZ_cF() throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_BLZ");	
	}

	public String get_SCHECKDRUCK_BLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECKDRUCK_BLZ");
	}

	public String get_SCHECKDRUCK_BLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_BLZ",cNotNullValue);
	}

	public String get_SCHECKDRUCK_BLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_BLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECKDRUCK_BLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECKDRUCK_BLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", calNewValueFormated);
	}
		public String get_SCHECKDRUCK_KONTO_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_KONTO_NR");
	}

	public String get_SCHECKDRUCK_KONTO_NR_cF() throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_KONTO_NR");	
	}

	public String get_SCHECKDRUCK_KONTO_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECKDRUCK_KONTO_NR");
	}

	public String get_SCHECKDRUCK_KONTO_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_KONTO_NR",cNotNullValue);
	}

	public String get_SCHECKDRUCK_KONTO_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_KONTO_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_KONTO_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", calNewValueFormated);
	}
		public String get_SCHECKVERMERK_AUF_GUTSCHRIFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECKVERMERK_AUF_GUTSCHRIFT");
	}

	public String get_SCHECKVERMERK_AUF_GUTSCHRIFT_cF() throws myException
	{
		return this.get_FormatedValue("SCHECKVERMERK_AUF_GUTSCHRIFT");	
	}

	public String get_SCHECKVERMERK_AUF_GUTSCHRIFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECKVERMERK_AUF_GUTSCHRIFT");
	}

	public String get_SCHECKVERMERK_AUF_GUTSCHRIFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECKVERMERK_AUF_GUTSCHRIFT",cNotNullValue);
	}

	public String get_SCHECKVERMERK_AUF_GUTSCHRIFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECKVERMERK_AUF_GUTSCHRIFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", calNewValueFormated);
	}
		public String get_STEUERFINDUNG_OHNE_EIGENORTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERFINDUNG_OHNE_EIGENORTE");
	}

	public String get_STEUERFINDUNG_OHNE_EIGENORTE_cF() throws myException
	{
		return this.get_FormatedValue("STEUERFINDUNG_OHNE_EIGENORTE");	
	}

	public String get_STEUERFINDUNG_OHNE_EIGENORTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERFINDUNG_OHNE_EIGENORTE");
	}

	public String get_STEUERFINDUNG_OHNE_EIGENORTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERFINDUNG_OHNE_EIGENORTE",cNotNullValue);
	}

	public String get_STEUERFINDUNG_OHNE_EIGENORTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERFINDUNG_OHNE_EIGENORTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", calNewValueFormated);
	}
		public boolean is_STEUERFINDUNG_OHNE_EIGENORTE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STEUERFINDUNG_OHNE_EIGENORTE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STEUERFINDUNG_OHNE_EIGENORTE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STEUERFINDUNG_OHNE_EIGENORTE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STICHTAG_START_FIBU_cUF() throws myException
	{
		return this.get_UnFormatedValue("STICHTAG_START_FIBU");
	}

	public String get_STICHTAG_START_FIBU_cF() throws myException
	{
		return this.get_FormatedValue("STICHTAG_START_FIBU");	
	}

	public String get_STICHTAG_START_FIBU_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STICHTAG_START_FIBU");
	}

	public String get_STICHTAG_START_FIBU_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STICHTAG_START_FIBU",cNotNullValue);
	}

	public String get_STICHTAG_START_FIBU_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STICHTAG_START_FIBU",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STICHTAG_START_FIBU", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STICHTAG_START_FIBU(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STICHTAG_START_FIBU", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", calNewValueFormated);
	}
		public String get_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("STRASSE");
	}

	public String get_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("STRASSE");	
	}

	public String get_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STRASSE");
	}

	public String get_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STRASSE",cNotNullValue);
	}

	public String get_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
	}
		public String get_TITELUEBERANSCHRIFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("TITELUEBERANSCHRIFT");
	}

	public String get_TITELUEBERANSCHRIFT_cF() throws myException
	{
		return this.get_FormatedValue("TITELUEBERANSCHRIFT");	
	}

	public String get_TITELUEBERANSCHRIFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TITELUEBERANSCHRIFT");
	}

	public String get_TITELUEBERANSCHRIFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TITELUEBERANSCHRIFT",cNotNullValue);
	}

	public String get_TITELUEBERANSCHRIFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TITELUEBERANSCHRIFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TITELUEBERANSCHRIFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TITELUEBERANSCHRIFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TITELUEBERANSCHRIFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", calNewValueFormated);
	}
		public String get_UNTERSCHEIDUNGSNR_INTRASTAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("UNTERSCHEIDUNGSNR_INTRASTAT");
	}

	public String get_UNTERSCHEIDUNGSNR_INTRASTAT_cF() throws myException
	{
		return this.get_FormatedValue("UNTERSCHEIDUNGSNR_INTRASTAT");	
	}

	public String get_UNTERSCHEIDUNGSNR_INTRASTAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UNTERSCHEIDUNGSNR_INTRASTAT");
	}

	public String get_UNTERSCHEIDUNGSNR_INTRASTAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UNTERSCHEIDUNGSNR_INTRASTAT",cNotNullValue);
	}

	public String get_UNTERSCHEIDUNGSNR_INTRASTAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UNTERSCHEIDUNGSNR_INTRASTAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", calNewValueFormated);
	}
		public Long get_UNTERSCHEIDUNGSNR_INTRASTAT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("UNTERSCHEIDUNGSNR_INTRASTAT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_UNTERSCHEIDUNGSNR_INTRASTAT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("UNTERSCHEIDUNGSNR_INTRASTAT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_UNTERSCHEIDUNGSNR_INTRASTAT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("UNTERSCHEIDUNGSNR_INTRASTAT").getDoubleValue();
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
	public String get_UNTERSCHEIDUNGSNR_INTRASTAT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_UNTERSCHEIDUNGSNR_INTRASTAT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_UNTERSCHEIDUNGSNR_INTRASTAT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_UNTERSCHEIDUNGSNR_INTRASTAT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_UNTERSCHEIDUNGSNR_INTRASTAT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("UNTERSCHEIDUNGSNR_INTRASTAT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_UNTERSCHEIDUNGSNR_INTRASTAT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("UNTERSCHEIDUNGSNR_INTRASTAT").getBigDecimalValue();
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
	
	
	public String get_VERMERK_STEUERFR_DIENSTLEIST_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERMERK_STEUERFR_DIENSTLEIST");
	}

	public String get_VERMERK_STEUERFR_DIENSTLEIST_cF() throws myException
	{
		return this.get_FormatedValue("VERMERK_STEUERFR_DIENSTLEIST");	
	}

	public String get_VERMERK_STEUERFR_DIENSTLEIST_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERMERK_STEUERFR_DIENSTLEIST");
	}

	public String get_VERMERK_STEUERFR_DIENSTLEIST_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERMERK_STEUERFR_DIENSTLEIST",cNotNullValue);
	}

	public String get_VERMERK_STEUERFR_DIENSTLEIST_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERMERK_STEUERFR_DIENSTLEIST",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", calNewValueFormated);
	}
		public String get_VORNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORNAME");
	}

	public String get_VORNAME_cF() throws myException
	{
		return this.get_FormatedValue("VORNAME");	
	}

	public String get_VORNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORNAME");
	}

	public String get_VORNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORNAME",cNotNullValue);
	}

	public String get_VORNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
		public String get_WASSERZEICHEN_FONTNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_FONTNAME");
	}

	public String get_WASSERZEICHEN_FONTNAME_cF() throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_FONTNAME");	
	}

	public String get_WASSERZEICHEN_FONTNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WASSERZEICHEN_FONTNAME");
	}

	public String get_WASSERZEICHEN_FONTNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_FONTNAME",cNotNullValue);
	}

	public String get_WASSERZEICHEN_FONTNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_FONTNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_FONTNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WASSERZEICHEN_FONTNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", calNewValueFormated);
	}
		public String get_WASSERZEICHEN_FONTSIZE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_FONTSIZE");
	}

	public String get_WASSERZEICHEN_FONTSIZE_cF() throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_FONTSIZE");	
	}

	public String get_WASSERZEICHEN_FONTSIZE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WASSERZEICHEN_FONTSIZE");
	}

	public String get_WASSERZEICHEN_FONTSIZE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_FONTSIZE",cNotNullValue);
	}

	public String get_WASSERZEICHEN_FONTSIZE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_FONTSIZE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_FONTSIZE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", calNewValueFormated);
	}
		public Long get_WASSERZEICHEN_FONTSIZE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("WASSERZEICHEN_FONTSIZE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_WASSERZEICHEN_FONTSIZE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WASSERZEICHEN_FONTSIZE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WASSERZEICHEN_FONTSIZE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WASSERZEICHEN_FONTSIZE").getDoubleValue();
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
	public String get_WASSERZEICHEN_FONTSIZE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WASSERZEICHEN_FONTSIZE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WASSERZEICHEN_FONTSIZE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WASSERZEICHEN_FONTSIZE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WASSERZEICHEN_FONTSIZE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WASSERZEICHEN_FONTSIZE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WASSERZEICHEN_FONTSIZE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WASSERZEICHEN_FONTSIZE").getBigDecimalValue();
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
	
	
	public String get_WASSERZEICHEN_ROTATE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_ROTATE");
	}

	public String get_WASSERZEICHEN_ROTATE_cF() throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_ROTATE");	
	}

	public String get_WASSERZEICHEN_ROTATE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WASSERZEICHEN_ROTATE");
	}

	public String get_WASSERZEICHEN_ROTATE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_ROTATE",cNotNullValue);
	}

	public String get_WASSERZEICHEN_ROTATE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_ROTATE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_ROTATE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WASSERZEICHEN_ROTATE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", calNewValueFormated);
	}
		public Long get_WASSERZEICHEN_ROTATE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("WASSERZEICHEN_ROTATE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_WASSERZEICHEN_ROTATE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WASSERZEICHEN_ROTATE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WASSERZEICHEN_ROTATE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WASSERZEICHEN_ROTATE").getDoubleValue();
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
	public String get_WASSERZEICHEN_ROTATE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WASSERZEICHEN_ROTATE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WASSERZEICHEN_ROTATE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WASSERZEICHEN_ROTATE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WASSERZEICHEN_ROTATE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WASSERZEICHEN_ROTATE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WASSERZEICHEN_ROTATE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WASSERZEICHEN_ROTATE").getBigDecimalValue();
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
	
	
	public String get_WASSERZEICHEN_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_TEXT");
	}

	public String get_WASSERZEICHEN_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_TEXT");	
	}

	public String get_WASSERZEICHEN_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WASSERZEICHEN_TEXT");
	}

	public String get_WASSERZEICHEN_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WASSERZEICHEN_TEXT",cNotNullValue);
	}

	public String get_WASSERZEICHEN_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WASSERZEICHEN_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WASSERZEICHEN_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WASSERZEICHEN_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", calNewValueFormated);
	}
		public String get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_FAHRPLANERFASSUNG");
	}

	public String get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cF() throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_FAHRPLANERFASSUNG");	
	}

	public String get_ZEIGE_MODUL_FAHRPLANERFASSUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIGE_MODUL_FAHRPLANERFASSUNG");
	}

	public String get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_FAHRPLANERFASSUNG",cNotNullValue);
	}

	public String get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_FAHRPLANERFASSUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", calNewValueFormated);
	}
		public boolean is_ZEIGE_MODUL_FAHRPLANERFASSUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZEIGE_MODUL_FAHRPLANERFASSUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZEIGE_MODUL_GLOBAL_REPORTS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_GLOBAL_REPORTS");
	}

	public String get_ZEIGE_MODUL_GLOBAL_REPORTS_cF() throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_GLOBAL_REPORTS");	
	}

	public String get_ZEIGE_MODUL_GLOBAL_REPORTS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIGE_MODUL_GLOBAL_REPORTS");
	}

	public String get_ZEIGE_MODUL_GLOBAL_REPORTS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_GLOBAL_REPORTS",cNotNullValue);
	}

	public String get_ZEIGE_MODUL_GLOBAL_REPORTS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_GLOBAL_REPORTS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", calNewValueFormated);
	}
		public boolean is_ZEIGE_MODUL_GLOBAL_REPORTS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_GLOBAL_REPORTS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZEIGE_MODUL_GLOBAL_REPORTS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_GLOBAL_REPORTS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZEIGE_MODUL_KALENDER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_KALENDER");
	}

	public String get_ZEIGE_MODUL_KALENDER_cF() throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_KALENDER");	
	}

	public String get_ZEIGE_MODUL_KALENDER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIGE_MODUL_KALENDER");
	}

	public String get_ZEIGE_MODUL_KALENDER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_KALENDER",cNotNullValue);
	}

	public String get_ZEIGE_MODUL_KALENDER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_KALENDER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_KALENDER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIGE_MODUL_KALENDER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", calNewValueFormated);
	}
		public boolean is_ZEIGE_MODUL_KALENDER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_KALENDER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZEIGE_MODUL_KALENDER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_KALENDER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZEIGE_MODUL_MESSAGES_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_MESSAGES");
	}

	public String get_ZEIGE_MODUL_MESSAGES_cF() throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_MESSAGES");	
	}

	public String get_ZEIGE_MODUL_MESSAGES_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIGE_MODUL_MESSAGES");
	}

	public String get_ZEIGE_MODUL_MESSAGES_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_MESSAGES",cNotNullValue);
	}

	public String get_ZEIGE_MODUL_MESSAGES_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_MESSAGES",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_MESSAGES(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", calNewValueFormated);
	}
		public boolean is_ZEIGE_MODUL_MESSAGES_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_MESSAGES_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZEIGE_MODUL_MESSAGES_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_MESSAGES_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZEIGE_MODUL_TODOS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_TODOS");
	}

	public String get_ZEIGE_MODUL_TODOS_cF() throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_TODOS");	
	}

	public String get_ZEIGE_MODUL_TODOS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIGE_MODUL_TODOS");
	}

	public String get_ZEIGE_MODUL_TODOS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_TODOS",cNotNullValue);
	}

	public String get_ZEIGE_MODUL_TODOS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_TODOS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_TODOS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIGE_MODUL_TODOS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", calNewValueFormated);
	}
		public boolean is_ZEIGE_MODUL_TODOS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_TODOS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZEIGE_MODUL_TODOS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_TODOS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZEIGE_MODUL_WORKFLOW_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_WORKFLOW");
	}

	public String get_ZEIGE_MODUL_WORKFLOW_cF() throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_WORKFLOW");	
	}

	public String get_ZEIGE_MODUL_WORKFLOW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIGE_MODUL_WORKFLOW");
	}

	public String get_ZEIGE_MODUL_WORKFLOW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIGE_MODUL_WORKFLOW",cNotNullValue);
	}

	public String get_ZEIGE_MODUL_WORKFLOW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIGE_MODUL_WORKFLOW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", calNewValueFormated);
	}
		public boolean is_ZEIGE_MODUL_WORKFLOW_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_WORKFLOW_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZEIGE_MODUL_WORKFLOW_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZEIGE_MODUL_WORKFLOW_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ALLOW_EDIT_ABZUG_IN_FUHREN_RG",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_EDIT_PRICE_IN_FUHREN_RG",MyRECORD.DATATYPES.YESNO);
	put("ANR1_GLEICHHEIT_FUHRE_STELLEN",MyRECORD.DATATYPES.NUMBER);
	put("ANREDE",MyRECORD.DATATYPES.TEXT);
	put("APPEND_ATTACHMENT_PDF_TO_RG",MyRECORD.DATATYPES.YESNO);
	put("AUSSEN_STEUER_VERMERK",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_BANK",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_BLZ",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_EMAIL",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_HANDELSREG_NR",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_KONTO",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_REGISTERGERICHT",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_STEUERNR",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_TELEFAX",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_TELEFON",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_USTID",MyRECORD.DATATYPES.TEXT);
	put("BELEGDRUCK_WWW",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_ABANGEB",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_EKK",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_GUT",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_LIEFANGEB",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_RECH",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_TPA",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSPREFIX_VKK",MyRECORD.DATATYPES.TEXT);
	put("COLOR_BACKTEXT_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_BACKTEXT_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_BACKTEXT_RED",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_DIFF",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_HIGHLIGHT_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_HIGHLIGHT_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_HIGHLIGHT_RED",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_POPUP_TITEL_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_POPUP_TITEL_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_POPUP_TITEL_RED",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_RED",MyRECORD.DATATYPES.NUMBER);
	put("EIGENE_ADRESS_ID",MyRECORD.DATATYPES.NUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EU_STEUER_VERMERK",MyRECORD.DATATYPES.TEXT);
	put("FILENAME_INTRASTAT_IN",MyRECORD.DATATYPES.TEXT);
	put("FILENAME_INTRASTAT_OUT",MyRECORD.DATATYPES.TEXT);
	put("FIRMENBLOCKRECHTSOBEN",MyRECORD.DATATYPES.TEXT);
	put("FIRMENBLOCKSEITENFUSS",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GRENZE_MENG_DIFF_ABRECH_PROZ",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("HAT_ABZUEGE",MyRECORD.DATATYPES.YESNO);
	put("ID_ARTIKEL_BEZ_DUMMY",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAEHRUNG",MyRECORD.DATATYPES.NUMBER);
	put("INFO",MyRECORD.DATATYPES.TEXT);
	put("KARENZ_ZAHLFRIST_AB_HEUTE",MyRECORD.DATATYPES.NUMBER);
	put("KORR_ZAHLDAT_WOCHENENDE",MyRECORD.DATATYPES.YESNO);
	put("KURZNAME",MyRECORD.DATATYPES.TEXT);
	put("LANDKURZ",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LOGOGROESSE",MyRECORD.DATATYPES.NUMBER);
	put("LOGONAME",MyRECORD.DATATYPES.TEXT);
	put("LOGOSCHRIFT",MyRECORD.DATATYPES.TEXT);
	put("LOGOTEXT",MyRECORD.DATATYPES.TEXT);
	put("MAILACCOUNT",MyRECORD.DATATYPES.TEXT);
	put("MAILPASSWORD",MyRECORD.DATATYPES.TEXT);
	put("MAILSERVER",MyRECORD.DATATYPES.TEXT);
	put("MAILUSERNAME",MyRECORD.DATATYPES.TEXT);
	put("MG_TOLERANZ_EK_KONTRAKT_POS",MyRECORD.DATATYPES.NUMBER);
	put("MG_TOLERANZ_VK_KONTRAKT_POS",MyRECORD.DATATYPES.NUMBER);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("NUMKREIS_DEBITOR_EU_BIS",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_DEBITOR_EU_VON",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_DEBITOR_INLAND_BIS",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_DEBITOR_INLAND_VON",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_DEBITOR_NICHT_EU_BIS",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_DEBITOR_NICHT_EU_VON",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_KREDITOR_EU_BIS",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_KREDITOR_EU_VON",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_KREDITOR_INLAND_BIS",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_KREDITOR_INLAND_VON",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_KREDITOR_NICHT_EU_BIS",MyRECORD.DATATYPES.NUMBER);
	put("NUMKREIS_KREDITOR_NICHT_EU_VON",MyRECORD.DATATYPES.NUMBER);
	put("ORT",MyRECORD.DATATYPES.TEXT);
	put("PLZ",MyRECORD.DATATYPES.TEXT);
	put("PREISFIND_ANGEB_NUR_GEDRUCKT",MyRECORD.DATATYPES.YESNO);
	put("PREISFIND_KONTR_NUR_GEDRUCKT",MyRECORD.DATATYPES.YESNO);
	put("PREISFREIGABE_AUTO_FUHRE",MyRECORD.DATATYPES.YESNO);
	put("RECHDAT_IST_LEISTUNGSDATUM",MyRECORD.DATATYPES.YESNO);
	put("RUNDEN_ABZUGS_MENGEN",MyRECORD.DATATYPES.YESNO);
	put("SCHECKDRUCK_BANKNAME",MyRECORD.DATATYPES.TEXT);
	put("SCHECKDRUCK_BLZ",MyRECORD.DATATYPES.TEXT);
	put("SCHECKDRUCK_KONTO_NR",MyRECORD.DATATYPES.TEXT);
	put("SCHECKVERMERK_AUF_GUTSCHRIFT",MyRECORD.DATATYPES.TEXT);
	put("STEUERFINDUNG_OHNE_EIGENORTE",MyRECORD.DATATYPES.YESNO);
	put("STICHTAG_START_FIBU",MyRECORD.DATATYPES.DATE);
	put("STRASSE",MyRECORD.DATATYPES.TEXT);
	put("TITELUEBERANSCHRIFT",MyRECORD.DATATYPES.TEXT);
	put("UNTERSCHEIDUNGSNR_INTRASTAT",MyRECORD.DATATYPES.NUMBER);
	put("VERMERK_STEUERFR_DIENSTLEIST",MyRECORD.DATATYPES.TEXT);
	put("VORNAME",MyRECORD.DATATYPES.TEXT);
	put("WASSERZEICHEN_FONTNAME",MyRECORD.DATATYPES.TEXT);
	put("WASSERZEICHEN_FONTSIZE",MyRECORD.DATATYPES.NUMBER);
	put("WASSERZEICHEN_ROTATE",MyRECORD.DATATYPES.NUMBER);
	put("WASSERZEICHEN_TEXT",MyRECORD.DATATYPES.TEXT);
	put("ZEIGE_MODUL_FAHRPLANERFASSUNG",MyRECORD.DATATYPES.YESNO);
	put("ZEIGE_MODUL_GLOBAL_REPORTS",MyRECORD.DATATYPES.YESNO);
	put("ZEIGE_MODUL_KALENDER",MyRECORD.DATATYPES.YESNO);
	put("ZEIGE_MODUL_MESSAGES",MyRECORD.DATATYPES.YESNO);
	put("ZEIGE_MODUL_TODOS",MyRECORD.DATATYPES.YESNO);
	put("ZEIGE_MODUL_WORKFLOW",MyRECORD.DATATYPES.YESNO);
	}};



@Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return BASIC_RECORD_MANDANT.HM_FIELDS;	
	}


	@Override
	public MyRECORD_NEW get_RECORD_NEW() throws myException {
		return null;
	}

	
	
	public String get_COLOR_BACKTEXT_BLUE_cUF() throws myException
{
	return this.get_UnFormatedValue("COLOR_BACKTEXT_BLUE");
}

public String get_COLOR_BACKTEXT_BLUE_cF() throws myException
{
	return this.get_FormatedValue("COLOR_BACKTEXT_BLUE");	
}

public String get_COLOR_BACKTEXT_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
{
	return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_BACKTEXT_BLUE");
}

public String get_COLOR_BACKTEXT_BLUE_cUF_NN(String cNotNullValue) throws myException
{
	return this.get_UnFormatedValue("COLOR_BACKTEXT_BLUE",cNotNullValue);
}

public String get_COLOR_BACKTEXT_BLUE_cF_NN(String cNotNullValue) throws myException
{
	return this.get_FormatedValue("COLOR_BACKTEXT_BLUE",cNotNullValue);	
}

public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE(String cNewValueFormated) throws myException
{
	return this.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", cNewValueFormated);
}


//2013-09-18: new check_ Methode, die nichts schreibt
public MyE2_MessageVector check_NEW_VALUE_COLOR_BACKTEXT_BLUE(String cNewValueFormated) throws myException
{
	return super.check_NewValueForDatabase("COLOR_BACKTEXT_BLUE", cNewValueFormated);
}



//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE_(long lNewValueFormated) throws myException	{
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", lNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE_(double dNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", dNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE_(BigDecimal bdNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", bdNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", calNewValueFormated);
}
	public Long get_COLOR_BACKTEXT_BLUE_lValue(Long lValueWhenNULL) throws myException
{
	Long lRueck = this.get("COLOR_BACKTEXT_BLUE").getLongValue();
	if (lRueck==null) 
	{ 
		return lValueWhenNULL; 
	} 
	else 
	{ 
		return lRueck;
	} 
}
public Double get_COLOR_BACKTEXT_BLUE_dValue(Double dValueWhenNULL) throws myException
{
	Double dRueck = this.get("COLOR_BACKTEXT_BLUE").getDoubleValue();
	if (dRueck==null) 
	{ 
		return dValueWhenNULL; 
	} 
	else 
	{ 
		return dRueck;
	} 
}



public Double get_COLOR_BACKTEXT_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
{
	Double dRueck = this.get("COLOR_BACKTEXT_BLUE").getDoubleValue();
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
public String get_COLOR_BACKTEXT_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
{
	String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	
	DecimalFormat df = new DecimalFormat(cFormat);

	Double dHelp = this.get_COLOR_BACKTEXT_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
public String get_COLOR_BACKTEXT_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
{
	String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	
	DecimalFormat df = new DecimalFormat(cFormat);

	Double dHelp = this.get_COLOR_BACKTEXT_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public BigDecimal get_COLOR_BACKTEXT_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
{
	BigDecimal bdRueck = this.get("COLOR_BACKTEXT_BLUE").getBigDecimalValue();
	if (bdRueck==null) 
	{ 
		return bdValueWhenNULL; 
	} 
	else 
	{ 
		return bdRueck;
	} 
}



public BigDecimal get_COLOR_BACKTEXT_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
{
	BigDecimal bdRueck = this.get("COLOR_BACKTEXT_BLUE").getBigDecimalValue();
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


public String get_COLOR_BACKTEXT_GREEN_cUF() throws myException
{
	return this.get_UnFormatedValue("COLOR_BACKTEXT_GREEN");
}

public String get_COLOR_BACKTEXT_GREEN_cF() throws myException
{
	return this.get_FormatedValue("COLOR_BACKTEXT_GREEN");	
}

public String get_COLOR_BACKTEXT_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
{
	return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_BACKTEXT_GREEN");
}

public String get_COLOR_BACKTEXT_GREEN_cUF_NN(String cNotNullValue) throws myException
{
	return this.get_UnFormatedValue("COLOR_BACKTEXT_GREEN",cNotNullValue);
}

public String get_COLOR_BACKTEXT_GREEN_cF_NN(String cNotNullValue) throws myException
{
	return this.get_FormatedValue("COLOR_BACKTEXT_GREEN",cNotNullValue);	
}

public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN(String cNewValueFormated) throws myException
{
	return this.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", cNewValueFormated);
}


//2013-09-18: new check_ Methode, die nichts schreibt
public MyE2_MessageVector check_NEW_VALUE_COLOR_BACKTEXT_GREEN(String cNewValueFormated) throws myException
{
	return super.check_NewValueForDatabase("COLOR_BACKTEXT_GREEN", cNewValueFormated);
}



//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN_(long lNewValueFormated) throws myException	{
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", lNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN_(double dNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", dNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN_(BigDecimal bdNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", bdNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", calNewValueFormated);
}
	public Long get_COLOR_BACKTEXT_GREEN_lValue(Long lValueWhenNULL) throws myException
{
	Long lRueck = this.get("COLOR_BACKTEXT_GREEN").getLongValue();
	if (lRueck==null) 
	{ 
		return lValueWhenNULL; 
	} 
	else 
	{ 
		return lRueck;
	} 
}
public Double get_COLOR_BACKTEXT_GREEN_dValue(Double dValueWhenNULL) throws myException
{
	Double dRueck = this.get("COLOR_BACKTEXT_GREEN").getDoubleValue();
	if (dRueck==null) 
	{ 
		return dValueWhenNULL; 
	} 
	else 
	{ 
		return dRueck;
	} 
}



public Double get_COLOR_BACKTEXT_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
{
	Double dRueck = this.get("COLOR_BACKTEXT_GREEN").getDoubleValue();
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
public String get_COLOR_BACKTEXT_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
{
	String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	
	DecimalFormat df = new DecimalFormat(cFormat);

	Double dHelp = this.get_COLOR_BACKTEXT_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
public String get_COLOR_BACKTEXT_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
{
	String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	
	DecimalFormat df = new DecimalFormat(cFormat);

	Double dHelp = this.get_COLOR_BACKTEXT_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public BigDecimal get_COLOR_BACKTEXT_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
{
	BigDecimal bdRueck = this.get("COLOR_BACKTEXT_GREEN").getBigDecimalValue();
	if (bdRueck==null) 
	{ 
		return bdValueWhenNULL; 
	} 
	else 
	{ 
		return bdRueck;
	} 
}



public BigDecimal get_COLOR_BACKTEXT_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
{
	BigDecimal bdRueck = this.get("COLOR_BACKTEXT_GREEN").getBigDecimalValue();
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


public String get_COLOR_BACKTEXT_RED_cUF() throws myException
{
	return this.get_UnFormatedValue("COLOR_BACKTEXT_RED");
}

public String get_COLOR_BACKTEXT_RED_cF() throws myException
{
	return this.get_FormatedValue("COLOR_BACKTEXT_RED");	
}

public String get_COLOR_BACKTEXT_RED_VALUE_FOR_SQLSTATEMENT() throws myException
{
	return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_BACKTEXT_RED");
}

public String get_COLOR_BACKTEXT_RED_cUF_NN(String cNotNullValue) throws myException
{
	return this.get_UnFormatedValue("COLOR_BACKTEXT_RED",cNotNullValue);
}

public String get_COLOR_BACKTEXT_RED_cF_NN(String cNotNullValue) throws myException
{
	return this.get_FormatedValue("COLOR_BACKTEXT_RED",cNotNullValue);	
}

public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED(String cNewValueFormated) throws myException
{
	return this.set_NewValueForDatabase("COLOR_BACKTEXT_RED", cNewValueFormated);
}


//2013-09-18: new check_ Methode, die nichts schreibt
public MyE2_MessageVector check_NEW_VALUE_COLOR_BACKTEXT_RED(String cNewValueFormated) throws myException
{
	return super.check_NewValueForDatabase("COLOR_BACKTEXT_RED", cNewValueFormated);
}



//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED_(long lNewValueFormated) throws myException	{
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", lNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED_(double dNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", dNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED_(BigDecimal bdNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", bdNewValueFormated);
}

//2013-07-17: new wetting-methods, like in recordnew-object
public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED_(GregorianCalendar calNewValueFormated) throws myException {
	 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", calNewValueFormated);
}
	public Long get_COLOR_BACKTEXT_RED_lValue(Long lValueWhenNULL) throws myException
{
	Long lRueck = this.get("COLOR_BACKTEXT_RED").getLongValue();
	if (lRueck==null) 
	{ 
		return lValueWhenNULL; 
	} 
	else 
	{ 
		return lRueck;
	} 
}
public Double get_COLOR_BACKTEXT_RED_dValue(Double dValueWhenNULL) throws myException
{
	Double dRueck = this.get("COLOR_BACKTEXT_RED").getDoubleValue();
	if (dRueck==null) 
	{ 
		return dValueWhenNULL; 
	} 
	else 
	{ 
		return dRueck;
	} 
}



public Double get_COLOR_BACKTEXT_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
{
	Double dRueck = this.get("COLOR_BACKTEXT_RED").getDoubleValue();
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
public String get_COLOR_BACKTEXT_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
{
	String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	
	DecimalFormat df = new DecimalFormat(cFormat);

	Double dHelp = this.get_COLOR_BACKTEXT_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
public String get_COLOR_BACKTEXT_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
{
	String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
	
	DecimalFormat df = new DecimalFormat(cFormat);

	Double dHelp = this.get_COLOR_BACKTEXT_RED_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public BigDecimal get_COLOR_BACKTEXT_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
{
	BigDecimal bdRueck = this.get("COLOR_BACKTEXT_RED").getBigDecimalValue();
	if (bdRueck==null) 
	{ 
		return bdValueWhenNULL; 
	} 
	else 
	{ 
		return bdRueck;
	} 
}



public BigDecimal get_COLOR_BACKTEXT_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
{
	BigDecimal bdRueck = this.get("COLOR_BACKTEXT_RED").getBigDecimalValue();
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


	/**
	 * Ermittelt die im Mandant angegebene email-Adresse des Profils
	 * @param profil
	 * @return
	 * @throws myException
	 */
	public String get_EmailAddressOfType(MANDANT_CONST.MAILPROFILE profil) throws myException{
		String sRet = null;
		RECLIST_EMAIL_SEND_SCHABLONE rl = get_DOWN_RECORD_LIST_email_send_schablone( _DB.EMAIL_SEND_SCHABLONE$KENNUNG_MAILVERSAND + " = '" + profil.toString() + "'", "" ,false);
		if (rl.size() > 0){
			sRet = rl.get(0).get_ABSENDER_cUF();
		}
		return sRet;
	}
	
	
	public String get_ALLOWED_DATE_DIFF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOWED_DATE_DIFF");
	}

	public String get_ALLOWED_DATE_DIFF_cF() throws myException
	{
		return this.get_FormatedValue("ALLOWED_DATE_DIFF");	
	}

	public String get_ALLOWED_DATE_DIFF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOWED_DATE_DIFF");
	}

	public String get_ALLOWED_DATE_DIFF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOWED_DATE_DIFF",cNotNullValue);
	}

	public String get_ALLOWED_DATE_DIFF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOWED_DATE_DIFF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOWED_DATE_DIFF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOWED_DATE_DIFF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOWED_DATE_DIFF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", calNewValueFormated);
	}
		public Long get_ALLOWED_DATE_DIFF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ALLOWED_DATE_DIFF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ALLOWED_DATE_DIFF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ALLOWED_DATE_DIFF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ALLOWED_DATE_DIFF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ALLOWED_DATE_DIFF").getDoubleValue();
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
	public String get_ALLOWED_DATE_DIFF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ALLOWED_DATE_DIFF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ALLOWED_DATE_DIFF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ALLOWED_DATE_DIFF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ALLOWED_DATE_DIFF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ALLOWED_DATE_DIFF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ALLOWED_DATE_DIFF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ALLOWED_DATE_DIFF").getBigDecimalValue();
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
	
	
	
}
