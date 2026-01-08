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

public class RECORDNEW_EMAIL_INBOX_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_EMAIL_INBOX_DEF";
    private _TAB  tab = _TAB.email_inbox_def;  


	public RECORDNEW_EMAIL_INBOX_DEF() throws myException 
	{
		super("JT_EMAIL_INBOX_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_INBOX_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_EMAIL_INBOX_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_EMAIL_INBOX_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_INBOX_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_EMAIL_INBOX_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_EMAIL_INBOX_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_INBOX_DEF.TABLENAME);
	}

	
	
	public RECORD_EMAIL_INBOX_DEF do_WRITE_NEW_EMAIL_INBOX_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_EMAIL_INBOX_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_EMAIL_INBOX_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table EMAIL_INBOX_DEF ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_INBOX_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EMAIL_INBOX_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX_DEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_ACCOUNT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_ACCOUNT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_PW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT_PW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_ACCOUNT_PW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_ACCOUNT_PW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_PW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT_PW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_PW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT_PW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_PW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT_PW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_PW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT_PW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FOLDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_FOLDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_FOLDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FOLDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FOLDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FOLDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FOLDER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_HOST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_HOST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_HOST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_HOST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_HOST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_HOST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_HOST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_PORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_PORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_PORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_PORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_PORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_PORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_PORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_PROTOCOL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PROTOCOL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_PROTOCOL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_PROTOCOL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_PROTOCOL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PROTOCOL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_PROTOCOL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PROTOCOL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_PROTOCOL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PROTOCOL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_PROTOCOL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_PROTOCOL", calNewValueFormated);
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
