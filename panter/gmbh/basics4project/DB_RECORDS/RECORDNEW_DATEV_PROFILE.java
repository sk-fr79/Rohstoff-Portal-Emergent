package panter.gmbh.basics4project.DB_RECORDS;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;

public class RECORDNEW_DATEV_PROFILE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_DATEV_PROFILE";
    private _TAB  tab = _TAB.datev_profile;  


	public RECORDNEW_DATEV_PROFILE() throws myException 
	{
		super("JT_DATEV_PROFILE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DATEV_PROFILE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_DATEV_PROFILE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_DATEV_PROFILE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DATEV_PROFILE.TABLENAME);
	}
	
	
	
	public RECORDNEW_DATEV_PROFILE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_DATEV_PROFILE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DATEV_PROFILE.TABLENAME);
	}

	
	
	public RECORD_DATEV_PROFILE do_WRITE_NEW_DATEV_PROFILE(MyE2_MessageVector oMV) throws myException
	{
	
		boolean bThrowExceptionWhenAlarm = false;
	
		if (oMV==null)
		{
			oMV=new MyE2_MessageVector();
			bThrowExceptionWhenAlarm = true;
		}
		
		//zuerst die NotNull-felder pruefen (ausser den automatismen)
		Vector<String> vExcludeFields = new Vector<String>();
		vExcludeFields.addAll(this.get_vSonderFelder());
		vExcludeFields.add("ID_DATEV_PROFILE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_DATEV_PROFILE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table DATEV_PROFILE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CORRECT_DATES", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CORRECT_DATES(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CORRECT_DATES", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CORRECT_DATES", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CORRECT_DATES", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CORRECT_DATES", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CORRECT_DATES", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATEV_BERATERNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATEV_BERATERNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATEV_MANDANTNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATEV_MANDANTNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EXPORTS_STARTING_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EXPORTS_STARTING_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EXPORTS_STARTING_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EXPORTS_STARTING_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_DIR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EXPORT_DIR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EXPORT_DIR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_DIR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_DIR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_DIR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_DIR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLUSH_TABLES", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FLUSH_TABLES(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FLUSH_TABLES", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLUSH_TABLES", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLUSH_TABLES", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLUSH_TABLES", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLUSH_TABLES", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DRUCKER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DRUCKER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRINT_PROTOCOLS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRINT_PROTOCOLS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_UUID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYS_TRIGGER_UUID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_VERSION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYS_TRIGGER_VERSION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", calNewValueFormated);
	}
	}
