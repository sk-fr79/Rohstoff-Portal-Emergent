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

public class RECORDNEW_LISTEN_ZUSATZFELDER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_LISTEN_ZUSATZFELDER";
    private _TAB  tab = _TAB.listen_zusatzfelder;  


	public RECORDNEW_LISTEN_ZUSATZFELDER() throws myException 
	{
		super("JT_LISTEN_ZUSATZFELDER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LISTEN_ZUSATZFELDER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LISTEN_ZUSATZFELDER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_LISTEN_ZUSATZFELDER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LISTEN_ZUSATZFELDER.TABLENAME);
	}
	
	
	
	public RECORDNEW_LISTEN_ZUSATZFELDER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_LISTEN_ZUSATZFELDER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LISTEN_ZUSATZFELDER.TABLENAME);
	}

	
	
	public RECORD_LISTEN_ZUSATZFELDER do_WRITE_NEW_LISTEN_ZUSATZFELDER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LISTEN_ZUSATZFELDER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LISTEN_ZUSATZFELDER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LISTEN_ZUSATZFELDER ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_LISTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_LISTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHRIFTUNG_LISTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHRIFTUNG_LISTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_LISTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_LISTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_LISTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_LISTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_LISTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_LISTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_LISTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_LISTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_SELEKTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_SELEKTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHRIFTUNG_SELEKTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHRIFTUNG_SELEKTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_SELEKTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_SELEKTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_SELEKTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_SELEKTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_SELEKTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_SELEKTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHRIFTUNG_SELEKTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHRIFTUNG_SELEKTOR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LISTEN_ZUSATZFELDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LISTEN_ZUSATZFELDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LISTEN_ZUSATZFELDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LISTEN_ZUSATZFELDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LISTEN_ZUSATZFELDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LISTEN_ZUSATZFELDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LISTEN_ZUSATZFELDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LISTEN_ZUSATZFELDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LISTEN_ZUSATZFELDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LISTEN_ZUSATZFELDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LISTEN_ZUSATZFELDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LISTEN_ZUSATZFELDER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IST_NUMERISCH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_NUMERISCH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_NUMERISCH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_NUMERISCH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_NUMERISCH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_NUMERISCH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_NUMERISCH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_NUMERISCH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_NUMERISCH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_NUMERISCH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_NUMERISCH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_NUMERISCH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KENNER_LISTENMODUL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_LISTENMODUL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KENNER_LISTENMODUL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KENNER_LISTENMODUL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KENNER_LISTENMODUL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_LISTENMODUL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_LISTENMODUL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_LISTENMODUL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_LISTENMODUL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_LISTENMODUL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KENNER_LISTENMODUL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_LISTENMODUL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SPALTENPOSITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPALTENPOSITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SPALTENPOSITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SPALTENPOSITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SPALTENPOSITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPALTENPOSITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPALTENPOSITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPALTENPOSITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPALTENPOSITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPALTENPOSITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SPALTENPOSITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPALTENPOSITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUBQUERY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBQUERY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUBQUERY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUBQUERY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUBQUERY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBQUERY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUBQUERY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBQUERY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUBQUERY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBQUERY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUBQUERY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUBQUERY", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TOOLTIP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TOOLTIP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TOOLTIP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TOOLTIP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TOOLTIP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIP", calNewValueFormated);
	}
	}
