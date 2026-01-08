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

public class RECORDNEW_CONTAINER_STATION extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_CONTAINER_STATION";
    private _TAB  tab = _TAB.container_station;  


	public RECORDNEW_CONTAINER_STATION() throws myException 
	{
		super("JT_CONTAINER_STATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINER_STATION.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_CONTAINER_STATION(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_CONTAINER_STATION", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINER_STATION.TABLENAME);
	}
	
	
	
	public RECORDNEW_CONTAINER_STATION(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_CONTAINER_STATION");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINER_STATION.TABLENAME);
	}

	
	
	public RECORD_CONTAINER_STATION do_WRITE_NEW_CONTAINER_STATION(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_CONTAINER_STATION");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_CONTAINER_STATION(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table CONTAINER_STATION ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_PLANUNG_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_PLANUNG_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_PLANUNG_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_PLANUNG_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ZEIT_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_STATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINER_STATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_ZYKLUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINER_ZYKLUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASCHINEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUFTRAGGEBER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_AUFTRAGGEBER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUFTRAGNEHMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BUCHUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KFZKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KFZKENNZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TAETIGKEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT_PLANUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TAETIGKEIT_PLANUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", calNewValueFormated);
	}
	}
