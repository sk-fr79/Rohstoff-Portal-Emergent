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

public class RECORDNEW_REPORT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORT";
    private _TAB  tab = _TAB.report;  


	public RECORDNEW_REPORT() throws myException 
	{
		super("JT_REPORT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT.TABLENAME);
	}

	
	
	public RECORD_REPORT do_WRITE_NEW_REPORT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EMAIL_FREE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_EMAIL_FREE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_HTML(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_HTML", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_PDF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_PDF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_TXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_TXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_TXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_TXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_TXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_TXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_TXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_XLS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_XLS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_XML(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_XML", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUTTONTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUTTONTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUTTON_AUTH_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUTTON_AUTH_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_OF_REPORTFILE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_OF_REPORTFILE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PASSWORD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PASSWORD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PASSWORD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PASSWORD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PASSWORD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PASS_MULTI_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PASS_MULTI_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_NO_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PASS_NO_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PASS_NO_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_NO_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_NO_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_NO_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_NO_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PASS_SINGLE_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PASS_SINGLE_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_HTML", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREFER_HTML(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREFER_HTML", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_HTML", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_HTML", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_HTML", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_HTML", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_PDF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREFER_PDF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREFER_PDF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_PDF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_PDF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_PDF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_PDF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_TXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREFER_TXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREFER_TXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_TXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_TXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_TXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_TXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XLS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREFER_XLS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREFER_XLS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XLS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XLS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XLS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XLS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREFER_XML(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XML", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREFER_XML(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREFER_XML", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XML", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XML", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XML", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREFER_XML", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATIC_MAIL_ADRESSES(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATIC_MAIL_ADRESSES", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUPERVISOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUPERVISOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUPERVISOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUPERVISOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUPERVISOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUPERVISOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUPERVISOR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", calNewValueFormated);
	}
	}
