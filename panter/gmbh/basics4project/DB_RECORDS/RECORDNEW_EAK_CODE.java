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

public class RECORDNEW_EAK_CODE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_EAK_CODE";
    private _TAB  tab = _TAB.eak_code;  


	public RECORDNEW_EAK_CODE() throws myException 
	{
		super("JT_EAK_CODE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EAK_CODE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_EAK_CODE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_EAK_CODE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EAK_CODE.TABLENAME);
	}
	
	
	
	public RECORDNEW_EAK_CODE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_EAK_CODE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_EAK_CODE.TABLENAME);
	}

	
	
	public RECORD_EAK_CODE do_WRITE_NEW_EAK_CODE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_EAK_CODE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_EAK_CODE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table EAK_CODE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CODE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAEHRLICH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEFAEHRLICH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAEHRLICH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAEHRLICH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAEHRLICH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAEHRLICH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_GRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KEY_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEY_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEY_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEY_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEY_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEY_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEY_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEY_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGERUNG_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAGERUNG_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAGERUNG_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGERUNG_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGERUNG_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGERUNG_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGERUNG_OK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORT_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORT_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORT_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORT_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORT_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORT_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORT_OK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERARBEITUNG_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERARBEITUNG_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", calNewValueFormated);
	}
	}
