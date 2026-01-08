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

public class RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ZZ_ROHSTOFF_ALT_LEB321K";
    private _TAB  tab = _TAB.zz_rohstoff_alt_leb321k;  


	public RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K() throws myException 
	{
		super("JT_ZZ_ROHSTOFF_ALT_LEB321K");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ZZ_ROHSTOFF_ALT_LEB321K", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}
	
	
	
	public RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ZZ_ROHSTOFF_ALT_LEB321K");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}

	
	
	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K do_WRITE_NEW_ZZ_ROHSTOFF_ALT_LEB321K(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ZZ_ROHSTOFF_ALT_LEB321K");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ZZ_ROHSTOFF_ALT_LEB321K ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZUG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZUG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZUG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZUG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZUG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKKENNZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKKENNZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKKENNZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKKENNZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKKENNZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKKENNZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKKENNZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERFASSER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERFASSER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERFASSER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTPARTNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKTPARTNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKT_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKT_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKT_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKT_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKT_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKT_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKT_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LFRBED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LFRBED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LFRBED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LFRBED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFRBED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFRBED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LFRBED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LFRBED_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFRBED_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISSTELLUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISSTELLUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISSTELLUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISSTELLUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISSTELLUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISSTELLUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISSTELLUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECNUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECNUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECNUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECNUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECNUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECNUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECNUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECNUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECNUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECNUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECNUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECNUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUSKENNZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUSKENNZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUSKENNZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUSKENNZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUSKENNZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUSKENNZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUSKENNZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXTBAUSTEINNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXTBAUSTEINNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKAEUFER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERKAEUFER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERKAEUFER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKAEUFER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKAEUFER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKAEUFER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKAEUFER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZLGBED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZLGBED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZLGBED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZLGBED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZLGBED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZLGBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZLGBED_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", calNewValueFormated);
	}
	}
