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

public class RECORDNEW_TERMIN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TERMIN";
    private _TAB  tab = _TAB.termin;  


	public RECORDNEW_TERMIN() throws myException 
	{
		super("JT_TERMIN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TERMIN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TERMIN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TERMIN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TERMIN.TABLENAME);
	}
	
	
	
	public RECORDNEW_TERMIN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TERMIN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TERMIN.TABLENAME);
	}

	
	
	public RECORD_TERMIN do_WRITE_NEW_TERMIN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TERMIN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TERMIN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TERMIN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TERMIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TERMIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TERMIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TERMIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TERMIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TERMIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TERMIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TERMIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TERMIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TERMIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TERMIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TERMIN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KURZTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KURZTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LANGTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LANGTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZEIT_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIT_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIT_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT_VON", calNewValueFormated);
	}
	}
