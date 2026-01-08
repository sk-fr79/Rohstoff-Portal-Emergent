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

public class RECORDNEW_BEWEGUNG_ATOM_KOSTEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BEWEGUNG_ATOM_KOSTEN";
    private _TAB  tab = _TAB.bewegung_atom_kosten;  


	public RECORDNEW_BEWEGUNG_ATOM_KOSTEN() throws myException 
	{
		super("JT_BEWEGUNG_ATOM_KOSTEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_ATOM_KOSTEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BEWEGUNG_ATOM_KOSTEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BEWEGUNG_ATOM_KOSTEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_ATOM_KOSTEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_BEWEGUNG_ATOM_KOSTEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BEWEGUNG_ATOM_KOSTEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_ATOM_KOSTEN.TABLENAME);
	}

	
	
	public RECORD_BEWEGUNG_ATOM_KOSTEN do_WRITE_NEW_BEWEGUNG_ATOM_KOSTEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BEWEGUNG_ATOM_KOSTEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BEWEGUNG_ATOM_KOSTEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BEWEGUNG_ATOM_KOSTEN ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_KOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM_KOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_KOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_KOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_KOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_KOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_KOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_KOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_KOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_KOSTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_ARTBEZ_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_ARTBEZ_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KOSTEN_ARTBEZ_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KOSTEN_ARTBEZ_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_ARTBEZ_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_ARTBEZ_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_ARTBEZ_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_ARTBEZ_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_ARTBEZ_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_ARTBEZ_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_ARTBEZ_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_ARTBEZ_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_LIEFERBED_ADR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_LIEFERBED_ADR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KOSTEN_LIEFERBED_ADR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KOSTEN_LIEFERBED_ADR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_LIEFERBED_ADR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_LIEFERBED_ADR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_LIEFERBED_ADR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_LIEFERBED_ADR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_LIEFERBED_ADR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_LIEFERBED_ADR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOSTEN_LIEFERBED_ADR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOSTEN_LIEFERBED_ADR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IST_WARENEINGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_WARENEINGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_WARENEINGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_WARENEINGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_WARENEINGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_WARENEINGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_WARENEINGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_WARENEINGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_WARENEINGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_WARENEINGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_WARENEINGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_WARENEINGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTENTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTENTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTENTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTENTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTENTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTENTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_EINZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_EINZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_EINZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_EINZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_EINZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_EINZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_EINZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_EINZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_EINZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_EINZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_EINZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_EINZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_GESAMT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PAUSCHAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAUSCHAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PAUSCHAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PAUSCHAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PAUSCHAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAUSCHAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PAUSCHAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAUSCHAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PAUSCHAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAUSCHAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PAUSCHAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAUSCHAL", calNewValueFormated);
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
