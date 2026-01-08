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

public class RECORDNEW_TODO extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TODO";
    private _TAB  tab = _TAB.todo;  


	public RECORDNEW_TODO() throws myException 
	{
		super("JT_TODO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TODO.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TODO(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TODO", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TODO.TABLENAME);
	}
	
	
	
	public RECORDNEW_TODO(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TODO");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TODO.TABLENAME);
	}

	
	
	public RECORD_TODO do_WRITE_NEW_TODO(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TODO");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TODO(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TODO ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABLAUFDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUFDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLAUFDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLAUFDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLAUFDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUFDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLAUFDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUFDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLAUFDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUFDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLAUFDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUFDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABSCHLUSSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSCHLUSSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABSCHLUSSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABSCHLUSSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABSCHLUSSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSCHLUSSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSCHLUSSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSCHLUSSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSCHLUSSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSCHLUSSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABSCHLUSSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSCHLUSSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTWORTKURZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTKURZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTWORTKURZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTWORTKURZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTWORTKURZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTKURZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTWORTKURZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTKURZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTWORTKURZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTKURZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTWORTKURZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTKURZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTWORTTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTWORTTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTWORTTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTWORTTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTWORTTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTWORTTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTWORTTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTWORTTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUFGABEKURZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABEKURZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUFGABEKURZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUFGABEKURZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUFGABEKURZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABEKURZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABEKURZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABEKURZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABEKURZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABEKURZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUFGABEKURZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABEKURZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUFGABENTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABENTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUFGABENTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUFGABENTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUFGABENTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABENTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABENTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABENTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABENTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABENTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUFGABENTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABENTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLEDIGT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERLEDIGT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERLEDIGT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLEDIGT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLEDIGT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLEDIGT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLEDIGT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GENERIERUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERIERUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GENERIERUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GENERIERUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GENERIERUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERIERUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GENERIERUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERIERUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GENERIERUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERIERUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GENERIERUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENERIERUNGSDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TODO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TODO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TODO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TODO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TODO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TODO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TODO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TODO_WICHTIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO_WICHTIGKEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TODO_WICHTIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TODO_WICHTIGKEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TODO_WICHTIGKEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO_WICHTIGKEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TODO_WICHTIGKEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO_WICHTIGKEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TODO_WICHTIGKEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO_WICHTIGKEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TODO_WICHTIGKEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TODO_WICHTIGKEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", calNewValueFormated);
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
	}
