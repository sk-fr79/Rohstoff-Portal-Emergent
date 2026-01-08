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

public class RECORDNEW_BORDERCROSSING extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BORDERCROSSING";
    private _TAB  tab = _TAB.bordercrossing;  


	public RECORDNEW_BORDERCROSSING() throws myException 
	{
		super("JT_BORDERCROSSING");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BORDERCROSSING.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BORDERCROSSING(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BORDERCROSSING", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BORDERCROSSING.TABLENAME);
	}
	
	
	
	public RECORDNEW_BORDERCROSSING(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BORDERCROSSING");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BORDERCROSSING.TABLENAME);
	}

	
	
	public RECORD_BORDERCROSSING do_WRITE_NEW_BORDERCROSSING(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BORDERCROSSING");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BORDERCROSSING(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BORDERCROSSING ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_BEI_ANLAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_BEI_ANLAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_BEI_ANLAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_BEI_ANLAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_BEI_ANLAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_BEI_ANLAGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BORDERCROSSING(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BORDERCROSSING", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BORDERCROSSING(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BORDERCROSSING", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BORDERCROSSING(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BORDERCROSSING", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BORDERCROSSING(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BORDERCROSSING", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BORDERCROSSING(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BORDERCROSSING", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BORDERCROSSING(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BORDERCROSSING", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTERVALL_TAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTERVALL_TAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TAGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_OFFSET_BEFORE_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OFFSET_BEFORE_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OFFSET_BEFORE_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OFFSET_BEFORE_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OFFSET_BEFORE_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OFFSET_BEFORE_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OFFSET_BEFORE_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OFFSET_BEFORE_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OFFSET_BEFORE_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OFFSET_BEFORE_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OFFSET_BEFORE_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OFFSET_BEFORE_START", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TITLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITLE", calNewValueFormated);
	}
	}
