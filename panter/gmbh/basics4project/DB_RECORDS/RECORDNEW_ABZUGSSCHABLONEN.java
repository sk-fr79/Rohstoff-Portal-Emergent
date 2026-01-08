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

public class RECORDNEW_ABZUGSSCHABLONEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ABZUGSSCHABLONEN";
    private _TAB  tab = _TAB.abzugsschablonen;  


	public RECORDNEW_ABZUGSSCHABLONEN() throws myException 
	{
		super("JT_ABZUGSSCHABLONEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ABZUGSSCHABLONEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ABZUGSSCHABLONEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ABZUGSSCHABLONEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ABZUGSSCHABLONEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_ABZUGSSCHABLONEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ABZUGSSCHABLONEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ABZUGSSCHABLONEN.TABLENAME);
	}

	
	
	public RECORD_ABZUGSSCHABLONEN do_WRITE_NEW_ABZUGSSCHABLONEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ABZUGSSCHABLONEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ABZUGSSCHABLONEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ABZUGSSCHABLONEN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_DROP_DOWN_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_DROP_DOWN_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_DROP_DOWN_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_DROP_DOWN_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_DROP_DOWN_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_DROP_DOWN_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSSCHABLONEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSSCHABLONEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ABZUGSSCHABLONEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ABZUGSSCHABLONEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSSCHABLONEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSSCHABLONEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSSCHABLONEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSSCHABLONEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSSCHABLONEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSSCHABLONEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSSCHABLONEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSSCHABLONEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SICHTBAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHTBAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SICHTBAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SICHTBAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SICHTBAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHTBAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SICHTBAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHTBAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SICHTBAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHTBAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SICHTBAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHTBAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTIERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTIERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", calNewValueFormated);
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
