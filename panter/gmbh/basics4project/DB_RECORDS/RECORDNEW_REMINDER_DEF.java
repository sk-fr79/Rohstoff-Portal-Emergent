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

public class RECORDNEW_REMINDER_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REMINDER_DEF";
    private _TAB  tab = _TAB.reminder_def;  


	public RECORDNEW_REMINDER_DEF() throws myException 
	{
		super("JT_REMINDER_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REMINDER_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REMINDER_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REMINDER_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REMINDER_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_REMINDER_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REMINDER_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REMINDER_DEF.TABLENAME);
	}

	
	
	public RECORD_REMINDER_DEF do_WRITE_NEW_REMINDER_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REMINDER_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REMINDER_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REMINDER_DEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_AB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_AB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERINNERUNG_AB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERINNERUNG_AB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_AB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_AB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_AB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_AB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_AB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_AB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERINNERUNG_AB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERINNERUNG_AB", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REMINDER_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REMINDER_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REMINDER_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REMINDER_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REMINDER_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REMINDER_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REMINDER_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REMINDER_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REMINDER_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REMINDER_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REMINDER_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REMINDER_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_ABGESCHLOSSEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANGELEGT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANGELEGT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ANGELEGT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_ANGELEGT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANGELEGT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANGELEGT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANGELEGT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANGELEGT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANGELEGT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANGELEGT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANGELEGT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANGELEGT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTERVALL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTERVALL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTERVALL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTERVALL_PARAM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_PARAM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTERVALL_PARAM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTERVALL_PARAM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_PARAM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_PARAM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_PARAM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_PARAM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_PARAM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_PARAM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_PARAM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_PARAM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTERVALL_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTERVALL_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTERVALL_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERVALL_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODUL_CONNECT_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODUL_CONNECT_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODUL_CONNECT_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODUL_CONNECT_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODUL_CONNECT_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_CONNECT_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REMINDER_HEADING(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_HEADING", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REMINDER_HEADING(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REMINDER_HEADING", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REMINDER_HEADING(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_HEADING", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REMINDER_HEADING(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_HEADING", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REMINDER_HEADING(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_HEADING", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REMINDER_HEADING(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_HEADING", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REMINDER_KENNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_KENNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REMINDER_KENNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REMINDER_KENNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REMINDER_KENNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_KENNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REMINDER_KENNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_KENNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REMINDER_KENNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_KENNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REMINDER_KENNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_KENNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REMINDER_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REMINDER_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REMINDER_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REMINDER_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REMINDER_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REMINDER_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REMINDER_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REMINDER_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SEND_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SEND_NACHRICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", calNewValueFormated);
	}
	}
