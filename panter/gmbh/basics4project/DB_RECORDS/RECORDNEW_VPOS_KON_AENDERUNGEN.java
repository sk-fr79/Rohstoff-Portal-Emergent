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

public class RECORDNEW_VPOS_KON_AENDERUNGEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_KON_AENDERUNGEN";
    private _TAB  tab = _TAB.vpos_kon_aenderungen;  


	public RECORDNEW_VPOS_KON_AENDERUNGEN() throws myException 
	{
		super("JT_VPOS_KON_AENDERUNGEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_KON_AENDERUNGEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_KON_AENDERUNGEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_KON_AENDERUNGEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_KON_AENDERUNGEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_KON_AENDERUNGEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_KON_AENDERUNGEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_KON_AENDERUNGEN.TABLENAME);
	}

	
	
	public RECORD_VPOS_KON_AENDERUNGEN do_WRITE_NEW_VPOS_KON_AENDERUNGEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_KON_AENDERUNGEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_KON_AENDERUNGEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_KON_AENDERUNGEN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZAHL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZAHL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_AENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AENDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_AENDERUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_AENDERUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_AENDERUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON_AENDERUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_AENDERUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_AENDERUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_AENDERUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_AENDERUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_AENDERUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_AENDERUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_AENDERUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_AENDERUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KUERZEL_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL_AENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KUERZEL_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KUERZEL_AENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_AENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL_AENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_AENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL_AENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_AENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL_AENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_AENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL_AENDERUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UHRZEIT_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UHRZEIT_AENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UHRZEIT_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UHRZEIT_AENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UHRZEIT_AENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UHRZEIT_AENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UHRZEIT_AENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UHRZEIT_AENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UHRZEIT_AENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UHRZEIT_AENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UHRZEIT_AENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UHRZEIT_AENDERUNG", calNewValueFormated);
	}
	}
