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

public class RECORDNEW_FIBU extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FIBU";
    private _TAB  tab = _TAB.fibu;  


	public RECORDNEW_FIBU() throws myException 
	{
		super("JT_FIBU");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FIBU(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FIBU", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU.TABLENAME);
	}
	
	
	
	public RECORDNEW_FIBU(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FIBU");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU.TABLENAME);
	}

	
	
	public RECORD_FIBU do_WRITE_NEW_FIBU(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FIBU");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FIBU(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FIBU ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_SCHECK_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEARBEITERKUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEARBEITERKUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSBLOCK_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSBLOCK_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNG_GESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUMVERAENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUMVERAENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FREMDBELEGNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FREMDBELEGNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERN_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTERN_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTERN_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERN_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERN_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERN_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTERN_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KORREKTURBUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KORREKTURBUCHUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECKNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECKNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOGRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNOGRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNOGRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOGRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOGRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOGRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOGRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOKUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNOKUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNOKUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOKUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOKUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOKUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNOKUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORLAEUFIG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORLAEUFIG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORLAEUFIG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORLAEUFIG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORLAEUFIG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORLAEUFIG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORLAEUFIG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNG_FREMD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", calNewValueFormated);
	}
	}
