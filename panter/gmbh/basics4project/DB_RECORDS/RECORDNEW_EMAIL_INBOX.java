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

public class RECORDNEW_EMAIL_INBOX extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_EMAIL_INBOX";
    private _TAB  tab = _TAB.email_inbox;  


	public RECORDNEW_EMAIL_INBOX() throws myException 
	{
		super("JT_EMAIL_INBOX");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_INBOX.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_EMAIL_INBOX(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_EMAIL_INBOX", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_INBOX.TABLENAME);
	}
	
	
	
	public RECORDNEW_EMAIL_INBOX(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_EMAIL_INBOX");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_INBOX.TABLENAME);
	}

	
	
	public RECORD_EMAIL_INBOX do_WRITE_NEW_EMAIL_INBOX(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_EMAIL_INBOX");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_EMAIL_INBOX(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table EMAIL_INBOX ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_RECEIVED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATE_RECEIVED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATE_RECEIVED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_RECEIVED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_RECEIVED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_RECEIVED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_RECEIVED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATE_SEND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_SEND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATE_SEND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATE_SEND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_SEND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_SEND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_SEND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATE_SEND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELESEN_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GELESEN_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GELESEN_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELESEN_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELESEN_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELESEN_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELESEN_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_INBOX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EMAIL_INBOX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_GELESEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_GELESEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL_CC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_CC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_CC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_CC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_CC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_CC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_CC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_CC", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FROM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_FROM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_FROM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FROM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FROM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FROM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_FROM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL_TO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_TO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_TO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_UID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_UID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_UID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_UID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_UID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_UID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_UID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_UID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MESSAGE_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MESSAGE_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MESSAGE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MESSAGE_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUBJECT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBJECT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUBJECT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUBJECT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUBJECT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBJECT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUBJECT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBJECT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUBJECT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBJECT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUBJECT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBJECT", calNewValueFormated);
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
