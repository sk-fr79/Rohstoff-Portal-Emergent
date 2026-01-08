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

public class RECORDNEW_ADRESSE_AQUISE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ADRESSE_AQUISE";
    private _TAB  tab = _TAB.adresse_aquise;  


	public RECORDNEW_ADRESSE_AQUISE() throws myException 
	{
		super("JT_ADRESSE_AQUISE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_AQUISE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ADRESSE_AQUISE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ADRESSE_AQUISE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_AQUISE.TABLENAME);
	}
	
	
	
	public RECORDNEW_ADRESSE_AQUISE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ADRESSE_AQUISE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_AQUISE.TABLENAME);
	}

	
	
	public RECORD_ADRESSE_AQUISE do_WRITE_NEW_ADRESSE_AQUISE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ADRESSE_AQUISE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ADRESSE_AQUISE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ADRESSE_AQUISE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABNEHMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABNEHMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABNEHMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABNEHMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABNEHMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABNEHMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABNEHMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABNEHMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABNEHMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABNEHMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AQUISE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AQUISE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_AQUISE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_AQUISE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AQUISE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AQUISE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AQUISE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AQUISE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AQUISE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AQUISE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AQUISE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AQUISE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERANT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WETTBEWERBER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WETTBEWERBER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WETTBEWERBER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WETTBEWERBER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WETTBEWERBER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WETTBEWERBER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WETTBEWERBER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WETTBEWERBER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WETTBEWERBER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WETTBEWERBER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WETTBEWERBER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WETTBEWERBER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WURDE_UEBERNOMMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WURDE_UEBERNOMMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WURDE_UEBERNOMMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WURDE_UEBERNOMMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WURDE_UEBERNOMMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WURDE_UEBERNOMMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WURDE_UEBERNOMMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WURDE_UEBERNOMMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WURDE_UEBERNOMMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WURDE_UEBERNOMMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WURDE_UEBERNOMMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WURDE_UEBERNOMMEN", calNewValueFormated);
	}
	}
