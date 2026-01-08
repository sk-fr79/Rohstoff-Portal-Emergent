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

public class RECORDNEW_LAND extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_LAND";
    private _TAB  tab = _TAB.land;  


	public RECORDNEW_LAND() throws myException 
	{
		super("JD_LAND");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAND.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LAND(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_LAND", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAND.TABLENAME);
	}
	
	
	
	public RECORDNEW_LAND(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_LAND");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAND.TABLENAME);
	}

	
	
	public RECORD_LAND do_WRITE_NEW_LAND(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LAND");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LAND(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LAND ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZEIGEREIHENFOLGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZEIGEREIHENFOLGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GENERELLE_EINFUHR_NOTI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_POSTCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HAT_POSTCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HAT_POSTCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_POSTCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_POSTCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_POSTCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_POSTCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_JN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_JN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTRASTAT_JN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_JN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_JN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_JN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_JN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ISO_COUNTRY_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ISO_COUNTRY_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISTSTANDARD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ISTSTANDARD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ISTSTANDARD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISTSTANDARD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISTSTANDARD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISTSTANDARD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISTSTANDARD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERVORWAHL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERVORWAHL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_POST_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POST_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POST_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POST_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POST_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POST_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POST_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POST_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POST_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POST_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POST_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POST_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_PRAEFIX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UST_PRAEFIX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UST_PRAEFIX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_PRAEFIX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_PRAEFIX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_PRAEFIX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_PRAEFIX", calNewValueFormated);
	}
	}
