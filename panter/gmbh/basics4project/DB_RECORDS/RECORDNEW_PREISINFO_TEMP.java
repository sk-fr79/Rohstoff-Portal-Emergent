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

public class RECORDNEW_PREISINFO_TEMP extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_PREISINFO_TEMP";
    private _TAB  tab = _TAB.preisinfo_temp;  


	public RECORDNEW_PREISINFO_TEMP() throws myException 
	{
		super("JT_PREISINFO_TEMP");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PREISINFO_TEMP.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_PREISINFO_TEMP(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_PREISINFO_TEMP", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PREISINFO_TEMP.TABLENAME);
	}
	
	
	
	public RECORDNEW_PREISINFO_TEMP(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_PREISINFO_TEMP");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PREISINFO_TEMP.TABLENAME);
	}

	
	
	public RECORD_PREISINFO_TEMP do_WRITE_NEW_PREISINFO_TEMP(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_PREISINFO_TEMP");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_PREISINFO_TEMP(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table PREISINFO_TEMP ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANSPRECHPARTNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PREISINFO_TEMP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PREISINFO_TEMP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAHR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAHR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAHR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAHR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAHR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAHR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAHR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAHR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAHR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAHR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAHR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAHR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOPIERKENNZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOPIERKENNZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOPIERKENNZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOPIERKENNZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOPIERKENNZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOPIERKENNZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOPIERKENNZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MONAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MONAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MONAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MONAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MONAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MONAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MONAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
