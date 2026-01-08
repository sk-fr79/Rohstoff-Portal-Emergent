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

public class RECORDNEW_NULLPREIS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_NULLPREIS";
    private _TAB  tab = _TAB.nullpreis;  


	public RECORDNEW_NULLPREIS() throws myException 
	{
		super("JT_NULLPREIS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NULLPREIS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_NULLPREIS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_NULLPREIS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NULLPREIS.TABLENAME);
	}
	
	
	
	public RECORDNEW_NULLPREIS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_NULLPREIS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NULLPREIS.TABLENAME);
	}

	
	
	public RECORD_NULLPREIS do_WRITE_NEW_NULLPREIS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_NULLPREIS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_NULLPREIS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table NULLPREIS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERTVON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEAENDERTVON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEAENDERTVON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERTVON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERTVON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERTVON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERTVON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NULLPREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_NULLPREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_NULLPREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NULLPREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NULLPREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NULLPREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NULLPREIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LETZTEAENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LETZTEAENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_BEZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_BEZUG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_BEZUG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_BEZUG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_CA_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_CA_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_CA_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_CA_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_CA_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_CA_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_CA_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_LFRBED_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_LIEFERADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_LIEFERORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_LIEFERORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NP_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NP_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NP_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NP_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_BEZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_BEZUG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_BEZUG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_BEZUG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_CA_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_CA_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_LFRBED_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_LIEFERADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_LIEFERORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OLD_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OLD_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OLD_PREIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZINFO1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZINFO2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZINFO3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZINFO4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO5", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO5(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZINFO5", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO5", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO5", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO5", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFO5", calNewValueFormated);
	}
	}
