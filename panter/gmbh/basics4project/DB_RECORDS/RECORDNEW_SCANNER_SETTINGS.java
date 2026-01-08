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

public class RECORDNEW_SCANNER_SETTINGS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_SCANNER_SETTINGS";
    private _TAB  tab = _TAB.scanner_settings;  


	public RECORDNEW_SCANNER_SETTINGS() throws myException 
	{
		super("JT_SCANNER_SETTINGS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SCANNER_SETTINGS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SCANNER_SETTINGS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_SCANNER_SETTINGS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SCANNER_SETTINGS.TABLENAME);
	}
	
	
	
	public RECORDNEW_SCANNER_SETTINGS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_SCANNER_SETTINGS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SCANNER_SETTINGS.TABLENAME);
	}

	
	
	public RECORD_SCANNER_SETTINGS do_WRITE_NEW_SCANNER_SETTINGS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SCANNER_SETTINGS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SCANNER_SETTINGS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SCANNER_SETTINGS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DPI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DPI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DPI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DPI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DPI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DPI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DPI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DPI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DPI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DPI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DPI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DPI", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FILETYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILETYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FILETYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FILETYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FILETYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILETYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FILETYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILETYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FILETYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILETYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FILETYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILETYPE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SCANNER_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SCANNER_SETTINGS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_SCAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LOOP_SCAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LOOP_SCAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_SCAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_SCAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_SCAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_SCAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LOOP_TIMEOUT_SECONDS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCANNER_AUFRUF1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCANNER_AUFRUF2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCANNER_AUFRUF3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCANNER_AUFRUF4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCANNER_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCANNER_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCANNER_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STANDORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STANDORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STANDORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STANDORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STANDORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STANDORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STANDORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STANDORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STANDORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STANDORT", calNewValueFormated);
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
	}
