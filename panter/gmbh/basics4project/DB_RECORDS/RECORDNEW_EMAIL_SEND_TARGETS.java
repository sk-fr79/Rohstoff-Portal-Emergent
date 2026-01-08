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

public class RECORDNEW_EMAIL_SEND_TARGETS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_EMAIL_SEND_TARGETS";
    private _TAB  tab = _TAB.email_send_targets;  


	public RECORDNEW_EMAIL_SEND_TARGETS() throws myException 
	{
		super("JT_EMAIL_SEND_TARGETS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_SEND_TARGETS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_EMAIL_SEND_TARGETS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_EMAIL_SEND_TARGETS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_SEND_TARGETS.TABLENAME);
	}
	
	
	
	public RECORDNEW_EMAIL_SEND_TARGETS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_EMAIL_SEND_TARGETS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EMAIL_SEND_TARGETS.TABLENAME);
	}

	
	
	public RECORD_EMAIL_SEND_TARGETS do_WRITE_NEW_EMAIL_SEND_TARGETS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_EMAIL_SEND_TARGETS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_EMAIL_SEND_TARGETS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table EMAIL_SEND_TARGETS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND_TARGETS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND_TARGETS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_SEND_TARGETS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EMAIL_SEND_TARGETS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND_TARGETS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND_TARGETS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND_TARGETS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND_TARGETS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND_TARGETS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND_TARGETS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_SEND_TARGETS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EMAIL_SEND_TARGETS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SEND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SEND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SEND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SEND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SEND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SEND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SEND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SEND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SEND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SEND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SEND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SEND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SENDING_TIME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDING_TIME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SENDING_TIME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SENDING_TIME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SENDING_TIME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDING_TIME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SENDING_TIME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDING_TIME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SENDING_TIME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDING_TIME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SENDING_TIME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SENDING_TIME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SEND_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SEND_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SEND_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SEND_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SEND_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_OK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TARGET_ADRESS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARGET_ADRESS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TARGET_ADRESS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TARGET_ADRESS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TARGET_ADRESS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARGET_ADRESS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARGET_ADRESS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARGET_ADRESS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARGET_ADRESS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARGET_ADRESS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TARGET_ADRESS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARGET_ADRESS", calNewValueFormated);
	}
	}
