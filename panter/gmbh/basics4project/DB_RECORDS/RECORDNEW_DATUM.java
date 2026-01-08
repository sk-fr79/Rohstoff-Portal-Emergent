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

public class RECORDNEW_DATUM extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_DATUM";
    private _TAB  tab = _TAB.datum;  


	public RECORDNEW_DATUM() throws myException 
	{
		super("JD_DATUM");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DATUM.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_DATUM(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_DATUM", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DATUM.TABLENAME);
	}
	
	
	
	public RECORDNEW_DATUM(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_DATUM");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DATUM.TABLENAME);
	}

	
	
	public RECORD_DATUM do_WRITE_NEW_DATUM(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_DATUM");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_DATUM(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table DATUM ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DATE_GERMAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_GERMAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATE_GERMAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATE_GERMAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATE_GERMAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_GERMAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_GERMAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_GERMAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_GERMAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_GERMAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATE_GERMAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_GERMAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATE_ISO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ISO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATE_ISO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATE_ISO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATE_ISO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ISO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_ISO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ISO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_ISO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ISO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATE_ISO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ISO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATE_ORI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ORI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATE_ORI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATE_ORI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATE_ORI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ORI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_ORI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ORI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_ORI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ORI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATE_ORI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_ORI", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAYS_IN_MONTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_IN_MONTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAYS_IN_MONTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAYS_IN_MONTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAYS_IN_MONTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_IN_MONTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAYS_IN_MONTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_IN_MONTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAYS_IN_MONTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_IN_MONTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAYS_IN_MONTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_IN_MONTH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAYS_LEFT_IN_MONTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_LEFT_IN_MONTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAYS_LEFT_IN_MONTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAYS_LEFT_IN_MONTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAYS_LEFT_IN_MONTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_LEFT_IN_MONTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAYS_LEFT_IN_MONTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_LEFT_IN_MONTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAYS_LEFT_IN_MONTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_LEFT_IN_MONTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAYS_LEFT_IN_MONTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAYS_LEFT_IN_MONTH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAY_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAY_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAY_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAY_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAY_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAY_OF_MONTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_MONTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAY_OF_MONTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAY_OF_MONTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_MONTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_MONTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_MONTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_MONTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_MONTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_MONTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_MONTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_MONTH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAY_OF_WEEK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_WEEK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAY_OF_WEEK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAY_OF_WEEK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_WEEK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_WEEK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_WEEK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_WEEK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_WEEK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_WEEK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_WEEK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_WEEK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAY_OF_YEAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_YEAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAY_OF_YEAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAY_OF_YEAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_YEAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_YEAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_YEAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_YEAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_YEAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_YEAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAY_OF_YEAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAY_OF_YEAR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_JULIAN_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JULIAN_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JULIAN_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JULIAN_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JULIAN_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JULIAN_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JULIAN_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JULIAN_MONTHS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_MONTHS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JULIAN_MONTHS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JULIAN_MONTHS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JULIAN_MONTHS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_MONTHS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JULIAN_MONTHS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_MONTHS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JULIAN_MONTHS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_MONTHS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JULIAN_MONTHS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JULIAN_MONTHS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MONTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MONTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MONTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MONTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MONTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MONTH_ISO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_ISO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MONTH_ISO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MONTH_ISO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MONTH_ISO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_ISO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONTH_ISO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_ISO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONTH_ISO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_ISO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MONTH_ISO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_ISO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MONTH_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MONTH_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MONTH_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MONTH_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONTH_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONTH_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MONTH_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONTH_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_QUARTER_OF_YEAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUARTER_OF_YEAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_QUARTER_OF_YEAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("QUARTER_OF_YEAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_QUARTER_OF_YEAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUARTER_OF_YEAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUARTER_OF_YEAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUARTER_OF_YEAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUARTER_OF_YEAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUARTER_OF_YEAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_QUARTER_OF_YEAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUARTER_OF_YEAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ROMAN_MONTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROMAN_MONTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ROMAN_MONTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ROMAN_MONTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ROMAN_MONTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROMAN_MONTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROMAN_MONTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROMAN_MONTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROMAN_MONTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROMAN_MONTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ROMAN_MONTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROMAN_MONTH", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_MONTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_MONTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WEEK_OF_MONTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WEEK_OF_MONTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_MONTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_MONTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_MONTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_MONTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_MONTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_MONTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_MONTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_MONTH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WEEK_OF_YEAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WEEK_OF_YEAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR_ISO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR_ISO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WEEK_OF_YEAR_ISO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WEEK_OF_YEAR_ISO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR_ISO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR_ISO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR_ISO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR_ISO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR_ISO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR_ISO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WEEK_OF_YEAR_ISO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEEK_OF_YEAR_ISO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_YEAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YEAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_YEAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("YEAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_YEAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YEAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_YEAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YEAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_YEAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YEAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_YEAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YEAR", calNewValueFormated);
	}
	}
