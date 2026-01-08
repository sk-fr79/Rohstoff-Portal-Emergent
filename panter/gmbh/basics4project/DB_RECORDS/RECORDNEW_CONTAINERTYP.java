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

public class RECORDNEW_CONTAINERTYP extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_CONTAINERTYP";
    private _TAB  tab = _TAB.containertyp;  


	public RECORDNEW_CONTAINERTYP() throws myException 
	{
		super("JT_CONTAINERTYP");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINERTYP.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_CONTAINERTYP(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_CONTAINERTYP", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINERTYP.TABLENAME);
	}
	
	
	
	public RECORDNEW_CONTAINERTYP(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_CONTAINERTYP");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINERTYP.TABLENAME);
	}

	
	
	public RECORD_CONTAINERTYP do_WRITE_NEW_CONTAINERTYP(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_CONTAINERTYP");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_CONTAINERTYP(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table CONTAINERTYP ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABLAUF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLAUF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLAUF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLAUF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLAUF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLAUF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLAUF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLAUF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABROLL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABROLL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABROLL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABROLL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABROLL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABROLL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABROLL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABROLL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABROLL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABROLL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABROLL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABROLL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABSETZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSETZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABSETZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABSETZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABSETZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSETZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSETZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSETZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSETZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSETZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABSETZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABSETZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_CONTAINERINHALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINERINHALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTAINERINHALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTAINERINHALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINERINHALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINERINHALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINERINHALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINERINHALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINERINHALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINERINHALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINERINHALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINERINHALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DECKEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECKEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DECKEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DECKEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DECKEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECKEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DECKEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECKEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DECKEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECKEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DECKEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECKEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DICHT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINERTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINERTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PLANE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLANE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLANE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLANE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLANE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLANE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLANE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYMMETRISCH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYMMETRISCH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYMMETRISCH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYMMETRISCH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYMMETRISCH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYMMETRISCH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYMMETRISCH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYMMETRISCH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYMMETRISCH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYMMETRISCH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYMMETRISCH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYMMETRISCH", calNewValueFormated);
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
