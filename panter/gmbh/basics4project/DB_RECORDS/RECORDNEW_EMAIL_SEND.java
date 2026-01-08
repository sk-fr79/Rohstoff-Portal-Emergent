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

public class RECORDNEW_EMAIL_SEND extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_EMAIL_SEND";
    private _TAB  tab = _TAB.email_send;  


	public RECORDNEW_EMAIL_SEND() throws myException 
	{
		super("JT_EMAIL_SEND");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_SEND.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_EMAIL_SEND(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_EMAIL_SEND", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_SEND.TABLENAME);
	}
	
	
	
	public RECORDNEW_EMAIL_SEND(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_EMAIL_SEND");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_SEND.TABLENAME);
	}

	
	
	public RECORD_EMAIL_SEND do_WRITE_NEW_EMAIL_SEND(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_EMAIL_SEND");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_EMAIL_SEND(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table EMAIL_SEND ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BETREFF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETREFF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETREFF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BETREFF_2_SEND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_2_SEND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETREFF_2_SEND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETREFF_2_SEND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_2_SEND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_2_SEND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF_2_SEND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_2_SEND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF_2_SEND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_2_SEND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_2_SEND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_2_SEND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_VERIFICATION_KEY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_VERIFICATION_KEY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_VERIFICATION_KEY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_VERIFICATION_KEY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_VERIFICATION_KEY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_VERIFICATION_KEY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_VERIFICATION_KEY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_VERIFICATION_KEY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_VERIFICATION_KEY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_VERIFICATION_KEY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_VERIFICATION_KEY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_VERIFICATION_KEY", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_SEND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EMAIL_SEND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SENDER_ADRESS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDER_ADRESS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SENDER_ADRESS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SENDER_ADRESS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SENDER_ADRESS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDER_ADRESS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SENDER_ADRESS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDER_ADRESS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SENDER_ADRESS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDER_ADRESS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SENDER_ADRESS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDER_ADRESS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SEND_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SEND_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SEND_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SEND_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SEND_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_TYPE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_BASE_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_BASE_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT_2_SEND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_2_SEND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT_2_SEND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT_2_SEND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_2_SEND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_2_SEND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_2_SEND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_2_SEND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_2_SEND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_2_SEND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_2_SEND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_2_SEND", calNewValueFormated);
	}
	}
