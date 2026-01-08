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

public class RECORDNEW_LAGERPLATZ extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_LAGERPLATZ";
    private _TAB  tab = _TAB.lagerplatz;  


	public RECORDNEW_LAGERPLATZ() throws myException 
	{
		super("JT_LAGERPLATZ");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGERPLATZ.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LAGERPLATZ(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_LAGERPLATZ", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGERPLATZ.TABLENAME);
	}
	
	
	
	public RECORDNEW_LAGERPLATZ(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_LAGERPLATZ");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGERPLATZ.TABLENAME);
	}

	
	
	public RECORD_LAGERPLATZ do_WRITE_NEW_LAGERPLATZ(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LAGERPLATZ");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LAGERPLATZ(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LAGERPLATZ ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PARENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PARENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_PARENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ_PARENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PARENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PARENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PARENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PARENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PARENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PARENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PARENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PARENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IST_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LAGERPLATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_LAGERPLATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERPLATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LAGERPLATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERPLATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LAGERPLATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERPLATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LAGERPLATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERPLATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LAGERPLATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_SCHUETTGUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SCHUETTGUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_SCHUETTGUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_SCHUETTGUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_SCHUETTGUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SCHUETTGUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_SCHUETTGUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SCHUETTGUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_SCHUETTGUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SCHUETTGUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_SCHUETTGUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SCHUETTGUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IS_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IS_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IS_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IS_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IS_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LATITUDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LATITUDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LATITUDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LONGITUDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LONGITUDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", calNewValueFormated);
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
