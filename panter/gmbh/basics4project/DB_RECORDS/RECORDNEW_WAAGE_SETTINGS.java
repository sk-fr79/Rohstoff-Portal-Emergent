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

public class RECORDNEW_WAAGE_SETTINGS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_WAAGE_SETTINGS";
    private _TAB  tab = _TAB.waage_settings;  


	public RECORDNEW_WAAGE_SETTINGS() throws myException 
	{
		super("JT_WAAGE_SETTINGS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WAAGE_SETTINGS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_WAAGE_SETTINGS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_WAAGE_SETTINGS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WAAGE_SETTINGS.TABLENAME);
	}
	
	
	
	public RECORDNEW_WAAGE_SETTINGS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_WAAGE_SETTINGS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WAAGE_SETTINGS.TABLENAME);
	}

	
	
	public RECORD_WAAGE_SETTINGS do_WRITE_NEW_WAAGE_SETTINGS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_WAAGE_SETTINGS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_WAAGE_SETTINGS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table WAAGE_SETTINGS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAAGE_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAAGE_SETTINGS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAAGE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAAGE_STANDORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IP_ADR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IP_ADR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IP_ADR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IP_ADR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IP_ADR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IP_ADR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IP_ADR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IP_ADR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IP_ADR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IP_ADR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IP_ADR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IP_ADR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MINDESTLAST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MINDESTLAST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MINDESTLAST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MINDESTLAST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MINDESTLAST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MINDESTLAST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MINDESTLAST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MINDESTLAST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MINDESTLAST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MINDESTLAST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MINDESTLAST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MINDESTLAST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM5(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM5(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM5", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TYP_WAAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WAAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP_WAAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP_WAAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP_WAAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WAAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_WAAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WAAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_WAAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WAAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP_WAAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WAAGE", calNewValueFormated);
	}
	}
