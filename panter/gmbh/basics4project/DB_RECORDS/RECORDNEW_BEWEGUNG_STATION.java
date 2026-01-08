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

public class RECORDNEW_BEWEGUNG_STATION extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BEWEGUNG_STATION";
    private _TAB  tab = _TAB.bewegung_station;  


	public RECORDNEW_BEWEGUNG_STATION() throws myException 
	{
		super("JT_BEWEGUNG_STATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_STATION.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BEWEGUNG_STATION(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BEWEGUNG_STATION", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_STATION.TABLENAME);
	}
	
	
	
	public RECORDNEW_BEWEGUNG_STATION(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BEWEGUNG_STATION");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_STATION.TABLENAME);
	}

	
	
	public RECORD_BEWEGUNG_STATION do_WRITE_NEW_BEWEGUNG_STATION(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BEWEGUNG_STATION");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BEWEGUNG_STATION(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BEWEGUNG_STATION ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BESITZER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_BESITZER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_STATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_STATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_STATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_STATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_STATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_STATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_STATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_STATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_STATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_STATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_STATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_STATION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTROLLMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTROLLMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTROLLMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTROLLMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTROLLMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTROLLMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTROLLMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENVORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENVORZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TELEFON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", calNewValueFormated);
	}
	}
