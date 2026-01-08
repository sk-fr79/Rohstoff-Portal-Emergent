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

public class RECORDNEW_ARTIKEL extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ARTIKEL";
    private _TAB  tab = _TAB.artikel;  


	public RECORDNEW_ARTIKEL() throws myException 
	{
		super("JT_ARTIKEL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKEL.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ARTIKEL(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ARTIKEL", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKEL.TABLENAME);
	}
	
	
	
	public RECORDNEW_ARTIKEL(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ARTIKEL");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKEL.TABLENAME);
	}

	
	
	public RECORD_ARTIKEL do_WRITE_NEW_ARTIKEL(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ARTIKEL");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ARTIKEL(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ARTIKEL ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3A_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHANG7_3A_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3A_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHANG7_3A_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3B_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHANG7_3B_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3B_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHANG7_3B_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DIENSTLEISTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_OF_WASTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_END_OF_WASTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("END_OF_WASTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_OF_WASTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_OF_WASTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_OF_WASTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("END_OF_WASTE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAHRGUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEFAHRGUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEFAHRGUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAHRGUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAHRGUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAHRGUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEFAHRGUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GENAUIGKEIT_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GENAUIGKEIT_MENGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_GRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LEERGUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_LEERGUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_LEERGUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LEERGUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LEERGUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LEERGUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LEERGUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZOLLTARIFNOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", calNewValueFormated);
	}
	}
