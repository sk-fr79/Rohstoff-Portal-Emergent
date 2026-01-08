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

public class RECORDNEW_REPORTING_QUERY_PARAM extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORTING_QUERY_PARAM";
    private _TAB  tab = _TAB.reporting_query_param;  


	public RECORDNEW_REPORTING_QUERY_PARAM() throws myException 
	{
		super("JT_REPORTING_QUERY_PARAM");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY_PARAM.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORTING_QUERY_PARAM(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORTING_QUERY_PARAM", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY_PARAM.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORTING_QUERY_PARAM(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORTING_QUERY_PARAM");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY_PARAM.TABLENAME);
	}

	
	
	public RECORD_REPORTING_QUERY_PARAM do_WRITE_NEW_REPORTING_QUERY_PARAM(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORTING_QUERY_PARAM");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORTING_QUERY_PARAM(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORTING_QUERY_PARAM ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_PARAM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_PARAM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORTING_QUERY_PARAM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORTING_QUERY_PARAM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_PARAM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_PARAM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_PARAM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_PARAM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_PARAM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_PARAM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_PARAM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_PARAM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PARAMDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMKEY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMKEY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMKEY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMKEY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMKEY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMKEY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMKEY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMKEY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMKEY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMKEY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMKEY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMKEY", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMNAME_4_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMNAME_4_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMNAME_4_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMNAME_4_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMNAME_4_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMNAME_4_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMNAME_4_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMNAME_4_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMNAME_4_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMNAME_4_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMNAME_4_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMNAME_4_USER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", calNewValueFormated);
	}
	}
