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

public class RECORDNEW_TAX extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TAX";
    private _TAB  tab = _TAB.tax;  


	public RECORDNEW_TAX() throws myException 
	{
		super("JT_TAX");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TAX.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TAX(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TAX", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TAX.TABLENAME);
	}
	
	
	
	public RECORDNEW_TAX(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TAX");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TAX.TABLENAME);
	}

	
	
	public RECORD_TAX do_WRITE_NEW_TAX(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TAX");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TAX(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TAX ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DROPDOWN_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DROPDOWN_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_HISTORISCH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HISTORISCH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HISTORISCH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HISTORISCH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HISTORISCH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HISTORISCH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HISTORISCH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HISTORISCH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTO_GS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU_KONTO_GS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTO_RE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU_KONTO_RE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_GROUP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_GROUP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INFO_TEXT_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INFO_TEXT_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEERVERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEERVERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEERVERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEERVERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEERVERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEERVERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEERVERMERK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_NEU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_NEU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TAXTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAXTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TAXTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TAXTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TAXTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAXTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAXTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAXTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAXTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAXTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TAXTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAXTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WECHSELDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WECHSELDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WECHSELDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WECHSELDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WECHSELDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WECHSELDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WECHSELDATUM", calNewValueFormated);
	}
	}
