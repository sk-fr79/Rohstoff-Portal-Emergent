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

public class RECORDNEW_BATCH_LOG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_BATCH_LOG";
    private _TAB  tab = _TAB.batch_log;  


	public RECORDNEW_BATCH_LOG() throws myException 
	{
		super("JD_BATCH_LOG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BATCH_LOG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BATCH_LOG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_BATCH_LOG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BATCH_LOG.TABLENAME);
	}
	
	
	
	public RECORDNEW_BATCH_LOG(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_BATCH_LOG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BATCH_LOG.TABLENAME);
	}

	
	
	public RECORD_BATCH_LOG do_WRITE_NEW_BATCH_LOG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BATCH_LOG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BATCH_LOG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BATCH_LOG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BEGIN_TIME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGIN_TIME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEGIN_TIME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEGIN_TIME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEGIN_TIME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGIN_TIME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEGIN_TIME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGIN_TIME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEGIN_TIME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGIN_TIME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEGIN_TIME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGIN_TIME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_END_TIME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_TIME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_END_TIME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("END_TIME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_END_TIME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_TIME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_END_TIME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_TIME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_END_TIME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_TIME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_END_TIME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_TIME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BATCH_LOG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BATCH_LOG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BATCH_LOG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BATCH_LOG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BATCH_LOG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BATCH_LOG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BATCH_LOG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BATCH_LOG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BATCH_LOG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BATCH_LOG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BATCH_LOG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BATCH_LOG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MESSAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MESSAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MESSAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETERLIST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERLIST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETERLIST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETERLIST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERLIST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERLIST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERLIST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERLIST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERLIST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERLIST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERLIST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERLIST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUS_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_OK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TASKNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TASKNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TASKNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TASKNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TASKNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TASKNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TASKNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TASKNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TASKNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TASKNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TASKNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TASKNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_USERNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_USERNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("USERNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_USERNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USERNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USERNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_USERNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERNAME", calNewValueFormated);
	}
	}
