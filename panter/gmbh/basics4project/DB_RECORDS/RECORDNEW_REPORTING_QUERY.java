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

public class RECORDNEW_REPORTING_QUERY extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORTING_QUERY";
    private _TAB  tab = _TAB.reporting_query;  


	public RECORDNEW_REPORTING_QUERY() throws myException 
	{
		super("JT_REPORTING_QUERY");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORTING_QUERY(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORTING_QUERY", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORTING_QUERY(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORTING_QUERY");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY.TABLENAME);
	}

	
	
	public RECORD_REPORTING_QUERY do_WRITE_NEW_REPORTING_QUERY(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORTING_QUERY");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORTING_QUERY(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORTING_QUERY ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORTING_QUERY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORTING_QUERY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LANGTEXTINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXTINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LANGTEXTINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LANGTEXTINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LANGTEXTINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXTINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANGTEXTINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXTINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANGTEXTINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXTINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LANGTEXTINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXTINFO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_QUERY1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_QUERY1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("QUERY1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_QUERY1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_QUERY1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_QUERY2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_QUERY2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("QUERY2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_QUERY2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_QUERY2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_QUERY3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_QUERY3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("QUERY3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_QUERY3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_QUERY3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_QUERY4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_QUERY4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("QUERY4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_QUERY4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUERY4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_QUERY4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUERY4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REALNAME_TEMPTABLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REALNAME_TEMPTABLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REALNAME_TEMPTABLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REALNAME_TEMPTABLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REALNAME_TEMPTABLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REALNAME_TEMPTABLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REALNAME_TEMPTABLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REALNAME_TEMPTABLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REALNAME_TEMPTABLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REALNAME_TEMPTABLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REALNAME_TEMPTABLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REALNAME_TEMPTABLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_BASENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_BASENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TITEL_4_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_4_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITEL_4_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITEL_4_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITEL_4_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_4_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL_4_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_4_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL_4_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_4_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITEL_4_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_4_USER", calNewValueFormated);
	}
	}
