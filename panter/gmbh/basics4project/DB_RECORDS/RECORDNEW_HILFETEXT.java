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

public class RECORDNEW_HILFETEXT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_HILFETEXT";
    private _TAB  tab = _TAB.hilfetext;  


	public RECORDNEW_HILFETEXT() throws myException 
	{
		super("JT_HILFETEXT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_HILFETEXT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_HILFETEXT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_HILFETEXT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_HILFETEXT.TABLENAME);
	}
	
	
	
	public RECORDNEW_HILFETEXT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_HILFETEXT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_HILFETEXT.TABLENAME);
	}

	
	
	public RECORD_HILFETEXT do_WRITE_NEW_HILFETEXT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_HILFETEXT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_HILFETEXT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table HILFETEXT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_HILFETEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HILFETEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HILFETEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HILFETEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HILFETEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HILFETEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HILFETEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HILFETEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HILFETEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HILFETEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HILFETEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HILFETEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_HILFETEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HILFETEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_HILFETEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_HILFETEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_HILFETEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HILFETEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HILFETEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HILFETEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HILFETEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HILFETEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_HILFETEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HILFETEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_URSPRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_URSPRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_URSPRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_URSPRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_URSPRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_URSPRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_URSPRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_URSPRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_URSPRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_URSPRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_URSPRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_URSPRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VERSION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERSION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VERSION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VERSION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERSION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERSION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERSION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERSION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERSION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERSION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERSION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERSION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INFO_DEVELOPER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_DEVELOPER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INFO_DEVELOPER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INFO_DEVELOPER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INFO_DEVELOPER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_DEVELOPER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_DEVELOPER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_DEVELOPER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_DEVELOPER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_DEVELOPER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INFO_DEVELOPER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_DEVELOPER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODULKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODULKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TICKETNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TICKETNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TICKETNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TICKETNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TICKETNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TICKETNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TICKETNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TICKETNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TICKETNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TICKETNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TICKETNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TICKETNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", calNewValueFormated);
	}
	}
