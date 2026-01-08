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

public class RECORDNEW_MASCHINEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_MASCHINEN";
    private _TAB  tab = _TAB.maschinen;  


	public RECORDNEW_MASCHINEN() throws myException 
	{
		super("JT_MASCHINEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASCHINEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MASCHINEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_MASCHINEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASCHINEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_MASCHINEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_MASCHINEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASCHINEN.TABLENAME);
	}

	
	
	public RECORD_MASCHINEN do_WRITE_NEW_MASCHINEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MASCHINEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MASCHINEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MASCHINEN ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRIEFNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BRIEFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BRIEFNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRIEFNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRIEFNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRIEFNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRIEFNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ANSCHAFFUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRGESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRGESTELLNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEKAUFT_AB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEKAUFT_AB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEKAUFT_AB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEKAUFT_AB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEKAUFT_AB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEKAUFT_AB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEKAUFT_AB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWAEHRLEISTUNG_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HERSTELLER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HERSTELLER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HERSTELLER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HERSTELLER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HERSTELLER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HERSTELLER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HERSTELLER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HERSTELLER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_STANDORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINENTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASCHINENTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEDIENER1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BEDIENER1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEDIENER2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BEDIENER2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTENSTELLE1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTENSTELLE1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTENSTELLE2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTENSTELLE2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEASING_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEASING_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEASING_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEASING_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEASING_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEASING_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEASING_BIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYPENBEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYPENBEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYPENBEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYPENBEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYPENBEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYPENBEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYPENBEZ", calNewValueFormated);
	}
	}
