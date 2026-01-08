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

public class RECORDNEW_SANKTION_PRUEFUNG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_SANKTION_PRUEFUNG";
    private _TAB  tab = _TAB.sanktion_pruefung;  


	public RECORDNEW_SANKTION_PRUEFUNG() throws myException 
	{
		super("JT_SANKTION_PRUEFUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SANKTION_PRUEFUNG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SANKTION_PRUEFUNG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_SANKTION_PRUEFUNG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SANKTION_PRUEFUNG.TABLENAME);
	}
	
	
	
	public RECORDNEW_SANKTION_PRUEFUNG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_SANKTION_PRUEFUNG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SANKTION_PRUEFUNG.TABLENAME);
	}

	
	
	public RECORD_SANKTION_PRUEFUNG do_WRITE_NEW_SANKTION_PRUEFUNG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SANKTION_PRUEFUNG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SANKTION_PRUEFUNG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SANKTION_PRUEFUNG ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_FREIGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FREIGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FREIGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FREIGABE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_BEMERKUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FREIGABE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FREIGABE_BEMERKUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_BEMERKUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_BEMERKUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_BEMERKUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_BEMERKUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_BEMERKUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_BEMERKUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_BEMERKUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_BEMERKUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FREIGABE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FREIGABE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FREIGABE_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FREIGABE_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FREIGABE_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FREIGABE_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FREIGABE_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREIGABE_USER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEPRUEFT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRUEFT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEPRUEFT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEPRUEFT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEPRUEFT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRUEFT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEPRUEFT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRUEFT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEPRUEFT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRUEFT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEPRUEFT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRUEFT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HASHWERT_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HASHWERT_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HASHWERT_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HASHWERT_SANKTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_SANKTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HASHWERT_SANKTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HASHWERT_SANKTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_SANKTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_SANKTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_SANKTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_SANKTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_SANKTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_SANKTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HASHWERT_SANKTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HASHWERT_SANKTION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SANKTION_PRUEFUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SANKTION_PRUEFUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SANKTION_WEGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SANKTION_WEGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEGEN", calNewValueFormated);
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
