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

public class RECORDNEW_ARCHIVMEDIEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ARCHIVMEDIEN";
    private _TAB  tab = _TAB.archivmedien;  


	public RECORDNEW_ARCHIVMEDIEN() throws myException 
	{
		super("JT_ARCHIVMEDIEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARCHIVMEDIEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ARCHIVMEDIEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ARCHIVMEDIEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARCHIVMEDIEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_ARCHIVMEDIEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ARCHIVMEDIEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARCHIVMEDIEN.TABLENAME);
	}

	
	
	public RECORD_ARCHIVMEDIEN do_WRITE_NEW_ARCHIVMEDIEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ARCHIVMEDIEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ARCHIVMEDIEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ARCHIVMEDIEN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AENDERUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AENDERUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIONSPATTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIONSPATTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATEIBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATEIBESCHREIBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATEINAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEINAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATEINAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATEINAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATEINAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEINAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEINAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEINAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEINAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEINAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATEINAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATEINAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARCHIVMEDIEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARCHIVMEDIEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MEDIENTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MEDIENTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_ORIGINAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_ORIGINAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILERFOLGREICH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILERFOLGREICH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MEDIENKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MEDIENKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MEDIENKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MEDIENKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MEDIENKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MEDIENKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MEDIENKENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PFAD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PFAD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PFAD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PFAD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PFAD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PFAD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PFAD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PFAD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PFAD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PFAD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PFAD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PFAD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SONDERFELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SONDERFELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SONDERFELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERFELD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", calNewValueFormated);
	}
	}
