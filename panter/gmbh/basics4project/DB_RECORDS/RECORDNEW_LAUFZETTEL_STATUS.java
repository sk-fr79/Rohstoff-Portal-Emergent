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

public class RECORDNEW_LAUFZETTEL_STATUS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_LAUFZETTEL_STATUS";
    private _TAB  tab = _TAB.laufzettel_status;  


	public RECORDNEW_LAUFZETTEL_STATUS() throws myException 
	{
		super("JT_LAUFZETTEL_STATUS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAUFZETTEL_STATUS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LAUFZETTEL_STATUS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_LAUFZETTEL_STATUS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAUFZETTEL_STATUS.TABLENAME);
	}
	
	
	
	public RECORDNEW_LAUFZETTEL_STATUS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_LAUFZETTEL_STATUS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAUFZETTEL_STATUS.TABLENAME);
	}

	
	
	public RECORD_LAUFZETTEL_STATUS do_WRITE_NEW_LAUFZETTEL_STATUS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LAUFZETTEL_STATUS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LAUFZETTEL_STATUS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LAUFZETTEL_STATUS ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAUFZETTEL_STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ISDEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISDEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ISDEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ISDEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ISDEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISDEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISDEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISDEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISDEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISDEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ISDEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISDEFAULT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUS_SORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_SORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS_SORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS_SORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_SORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_SORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_SORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_SORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_SORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_SORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_SORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_SORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TRIGGER_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_ABSCHLUSS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRIGGER_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRIGGER_ABSCHLUSS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_ABSCHLUSS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_ABSCHLUSS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_ABSCHLUSS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_ABSCHLUSS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_ABSCHLUSS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_ABSCHLUSS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_ABSCHLUSS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_ABSCHLUSS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRIGGER_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_MELDUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRIGGER_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRIGGER_MELDUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_MELDUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_MELDUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_MELDUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_MELDUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_MELDUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_MELDUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRIGGER_MELDUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRIGGER_MELDUNG", calNewValueFormated);
	}
	}
