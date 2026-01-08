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

public class RECORDNEW_ADRESSKLASSE_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ADRESSKLASSE_DEF";
    private _TAB  tab = _TAB.adressklasse_def;  


	public RECORDNEW_ADRESSKLASSE_DEF() throws myException 
	{
		super("JT_ADRESSKLASSE_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSKLASSE_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ADRESSKLASSE_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ADRESSKLASSE_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSKLASSE_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_ADRESSKLASSE_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ADRESSKLASSE_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSKLASSE_DEF.TABLENAME);
	}

	
	
	public RECORD_ADRESSKLASSE_DEF do_WRITE_NEW_ADRESSKLASSE_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ADRESSKLASSE_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ADRESSKLASSE_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ADRESSKLASSE_DEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_COLORSORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORSORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLORSORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLORSORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLORSORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORSORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORSORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORSORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORSORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORSORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLORSORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORSORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSKLASSE_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSKLASSE_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSKLASSE_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSKLASSE_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSKLASSE_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSKLASSE_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSKLASSE_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSKLASSE_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSKLASSE_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSKLASSE_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSKLASSE_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSKLASSE_DEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IST_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KUNDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_KUNDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_KUNDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KUNDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_KUNDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KUNDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_KUNDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KUNDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_KUNDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KUNDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_STANDARD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_STANDARD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KURZBEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KURZBEZEICHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", calNewValueFormated);
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
	}
