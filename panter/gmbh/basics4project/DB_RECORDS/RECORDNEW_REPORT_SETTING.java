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

public class RECORDNEW_REPORT_SETTING extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORT_SETTING";
    private _TAB  tab = _TAB.report_setting;  


	public RECORDNEW_REPORT_SETTING() throws myException 
	{
		super("JT_REPORT_SETTING");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_SETTING.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORT_SETTING(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORT_SETTING", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_SETTING.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORT_SETTING(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORT_SETTING");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_SETTING.TABLENAME);
	}

	
	
	public RECORD_REPORT_SETTING do_WRITE_NEW_REPORT_SETTING(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORT_SETTING");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORT_SETTING(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORT_SETTING ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_COPY_FOLLOW_REPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_FOLLOW_REPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COPY_FOLLOW_REPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COPY_FOLLOW_REPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COPY_FOLLOW_REPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_FOLLOW_REPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_FOLLOW_REPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_FOLLOW_REPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_FOLLOW_REPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_FOLLOW_REPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COPY_FOLLOW_REPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_FOLLOW_REPORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_MAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_MAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COPY_NUMBER_MAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COPY_NUMBER_MAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_MAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_MAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_MAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_MAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_MAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_MAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_MAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_MAIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_PRINT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_PRINT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COPY_NUMBER_PRINT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COPY_NUMBER_PRINT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_PRINT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_PRINT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_PRINT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_PRINT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_PRINT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_PRINT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COPY_NUMBER_PRINT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_NUMBER_PRINT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COPY_TEXT_LIST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_TEXT_LIST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COPY_TEXT_LIST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COPY_TEXT_LIST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COPY_TEXT_LIST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_TEXT_LIST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_TEXT_LIST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_TEXT_LIST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COPY_TEXT_LIST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_TEXT_LIST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COPY_TEXT_LIST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COPY_TEXT_LIST", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_SETTING(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_SETTING", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_SETTING(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORT_SETTING", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_SETTING(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_SETTING", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_SETTING(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_SETTING", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_SETTING(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_SETTING", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_SETTING(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_SETTING", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_REPORTFILENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REPORTFILENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REPORTFILENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTFILENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTFILENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILENAME", calNewValueFormated);
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
