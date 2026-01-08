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

public class RECORDNEW_STATUS_LAGER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_STATUS_LAGER";
    private _TAB  tab = _TAB.status_lager;  


	public RECORDNEW_STATUS_LAGER() throws myException 
	{
		super("JT_STATUS_LAGER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_STATUS_LAGER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_STATUS_LAGER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_STATUS_LAGER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_STATUS_LAGER.TABLENAME);
	}
	
	
	
	public RECORDNEW_STATUS_LAGER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_STATUS_LAGER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_STATUS_LAGER.TABLENAME);
	}

	
	
	public RECORD_STATUS_LAGER do_WRITE_NEW_STATUS_LAGER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_STATUS_LAGER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_STATUS_LAGER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table STATUS_LAGER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVG_WERT_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVG_WERT_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_GESAMT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_MIT_NULL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_MIT_NULL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVG_WERT_MIT_NULL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVG_WERT_MIT_NULL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_MIT_NULL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_MIT_NULL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_MIT_NULL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_MIT_NULL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_MIT_NULL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_MIT_NULL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_MIT_NULL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_MIT_NULL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_NICHT_NULL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_NICHT_NULL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVG_WERT_NICHT_NULL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVG_WERT_NICHT_NULL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_NICHT_NULL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_NICHT_NULL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_NICHT_NULL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_NICHT_NULL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_NICHT_NULL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_NICHT_NULL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVG_WERT_NICHT_NULL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVG_WERT_NICHT_NULL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SORTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SORTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SORTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SORTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SORTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SORTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SORTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SORTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SORTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SORTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SORTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SORTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_STATUS_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_STATUS_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_LAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_GESAMT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_LEER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_LEER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_PREISE_LEER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_PREISE_LEER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_LEER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_LEER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_LEER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_LEER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_LEER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_LEER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_LEER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_LEER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NICHT_NULL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NICHT_NULL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_PREISE_NICHT_NULL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_PREISE_NICHT_NULL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NICHT_NULL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NICHT_NULL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NICHT_NULL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NICHT_NULL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NICHT_NULL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NICHT_NULL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NICHT_NULL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NICHT_NULL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NULL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NULL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_PREISE_NULL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_PREISE_NULL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NULL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NULL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NULL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NULL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NULL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NULL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_PREISE_NULL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_PREISE_NULL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_RESTWERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RESTWERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_RESTWERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_RESTWERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_RESTWERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RESTWERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_RESTWERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RESTWERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_RESTWERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RESTWERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_RESTWERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RESTWERT", calNewValueFormated);
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
