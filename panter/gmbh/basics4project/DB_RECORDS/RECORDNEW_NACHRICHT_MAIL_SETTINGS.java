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

public class RECORDNEW_NACHRICHT_MAIL_SETTINGS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_NACHRICHT_MAIL_SETTINGS";
    private _TAB  tab = _TAB.nachricht_mail_settings;  


	public RECORDNEW_NACHRICHT_MAIL_SETTINGS() throws myException 
	{
		super("JT_NACHRICHT_MAIL_SETTINGS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NACHRICHT_MAIL_SETTINGS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_NACHRICHT_MAIL_SETTINGS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_NACHRICHT_MAIL_SETTINGS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NACHRICHT_MAIL_SETTINGS.TABLENAME);
	}
	
	
	
	public RECORDNEW_NACHRICHT_MAIL_SETTINGS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_NACHRICHT_MAIL_SETTINGS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NACHRICHT_MAIL_SETTINGS.TABLENAME);
	}

	
	
	public RECORD_NACHRICHT_MAIL_SETTINGS do_WRITE_NEW_NACHRICHT_MAIL_SETTINGS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_NACHRICHT_MAIL_SETTINGS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_NACHRICHT_MAIL_SETTINGS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table NACHRICHT_MAIL_SETTINGS ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_NACH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_NACH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_CC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_CC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_NACH_CC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_NACH_CC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_CC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_CC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_CC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_CC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_CC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_CC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_CC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_CC", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_REPEAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_REPEAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_NACH_REPEAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_NACH_REPEAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_REPEAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_REPEAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_REPEAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_REPEAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_REPEAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_REPEAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_NACH_REPEAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_NACH_REPEAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_VOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_VOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR_CC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR_CC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_VOR_CC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_VOR_CC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR_CC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR_CC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR_CC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR_CC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR_CC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR_CC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_VOR_CC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_VOR_CC", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_MAIL_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_MAIL_SETTINGS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_NACHRICHT_MAIL_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_NACHRICHT_MAIL_SETTINGS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_MAIL_SETTINGS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_MAIL_SETTINGS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_MAIL_SETTINGS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_MAIL_SETTINGS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_MAIL_SETTINGS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_MAIL_SETTINGS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_MAIL_SETTINGS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_MAIL_SETTINGS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_KENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SEND_CC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_CC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SEND_CC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SEND_CC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SEND_CC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_CC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_CC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_CC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_CC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_CC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SEND_CC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_CC", calNewValueFormated);
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
	}
