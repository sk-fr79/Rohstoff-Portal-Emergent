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

public class RECORDNEW_KREDITVERS_POS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_KREDITVERS_POS";
    private _TAB  tab = _TAB.kreditvers_pos;  


	public RECORDNEW_KREDITVERS_POS() throws myException 
	{
		super("JT_KREDITVERS_POS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KREDITVERS_POS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_KREDITVERS_POS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_KREDITVERS_POS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KREDITVERS_POS.TABLENAME);
	}
	
	
	
	public RECORDNEW_KREDITVERS_POS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_KREDITVERS_POS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KREDITVERS_POS.TABLENAME);
	}

	
	
	public RECORD_KREDITVERS_POS do_WRITE_NEW_KREDITVERS_POS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_KREDITVERS_POS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_KREDITVERS_POS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table KREDITVERS_POS ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BETRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BETRAG_ANFRAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_ANFRAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETRAG_ANFRAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETRAG_ANFRAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETRAG_ANFRAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_ANFRAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRAG_ANFRAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_ANFRAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRAG_ANFRAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_ANFRAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETRAG_ANFRAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_ANFRAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ANFRAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANFRAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANFRAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ANFRAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANFRAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANFRAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANFRAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANFRAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANFRAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANFRAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANFRAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANFRAGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GUELTIG_AB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_AB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUELTIG_AB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUELTIG_AB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_AB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_AB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_AB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_AB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_AB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_AB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_AB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_AB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUELTIG_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUELTIG_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_KOPF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_KOPF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITVERS_KOPF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITVERS_KOPF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_KOPF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_KOPF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_KOPF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_KOPF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_KOPF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_KOPF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_KOPF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_KOPF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITVERS_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITVERS_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITVERS_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITVERS_POS", calNewValueFormated);
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
