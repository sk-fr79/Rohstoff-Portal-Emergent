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

public class RECORDNEW_ZOLLTARIFNUMMER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ZOLLTARIFNUMMER";
    private _TAB  tab = _TAB.zolltarifnummer;  


	public RECORDNEW_ZOLLTARIFNUMMER() throws myException 
	{
		super("JT_ZOLLTARIFNUMMER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZOLLTARIFNUMMER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ZOLLTARIFNUMMER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ZOLLTARIFNUMMER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZOLLTARIFNUMMER.TABLENAME);
	}
	
	
	
	public RECORDNEW_ZOLLTARIFNUMMER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ZOLLTARIFNUMMER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZOLLTARIFNUMMER.TABLENAME);
	}

	
	
	public RECORD_ZOLLTARIFNUMMER do_WRITE_NEW_ZOLLTARIFNUMMER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ZOLLTARIFNUMMER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ZOLLTARIFNUMMER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ZOLLTARIFNUMMER ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BM_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BM_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BM_NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BM_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BM_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BM_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BM_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BM_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BM_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BM_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BM_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BM_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BM_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BM_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BM_TEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LETZTER_IMPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_IMPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LETZTER_IMPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LETZTER_IMPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_IMPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_IMPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTER_IMPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_IMPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTER_IMPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_IMPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_IMPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_IMPORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REVERSE_CHARGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REVERSE_CHARGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT3", calNewValueFormated);
	}
	}
