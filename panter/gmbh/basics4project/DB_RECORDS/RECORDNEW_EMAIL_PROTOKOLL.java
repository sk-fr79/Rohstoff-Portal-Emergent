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

public class RECORDNEW_EMAIL_PROTOKOLL extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_EMAIL_PROTOKOLL";
    private _TAB  tab = _TAB.email_protokoll;  


	public RECORDNEW_EMAIL_PROTOKOLL() throws myException 
	{
		super("JT_EMAIL_PROTOKOLL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_PROTOKOLL.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_EMAIL_PROTOKOLL(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_EMAIL_PROTOKOLL", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_PROTOKOLL.TABLENAME);
	}
	
	
	
	public RECORDNEW_EMAIL_PROTOKOLL(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_EMAIL_PROTOKOLL");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_PROTOKOLL.TABLENAME);
	}

	
	
	public RECORD_EMAIL_PROTOKOLL do_WRITE_NEW_EMAIL_PROTOKOLL(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_EMAIL_PROTOKOLL");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_EMAIL_PROTOKOLL(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table EMAIL_PROTOKOLL ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABSENDER_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABSENDER_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHANG_ARCHIV_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHANG_ARCHIV_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_BETREFF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_BETREFF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMPFAENGER_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMPFAENGER_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_PROTOKOLL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EMAIL_PROTOKOLL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MIME_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MIME_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MIME_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MIME_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MIME_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MIME_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MIME_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROGRAMM_MODUL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROGRAMM_MODUL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_USER_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("USER_KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_KUERZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERWALTUNGSINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERWALTUNGSINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", calNewValueFormated);
	}
	}
