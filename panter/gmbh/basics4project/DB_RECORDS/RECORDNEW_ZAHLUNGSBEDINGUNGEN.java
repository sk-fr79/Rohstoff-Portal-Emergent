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

public class RECORDNEW_ZAHLUNGSBEDINGUNGEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ZAHLUNGSBEDINGUNGEN";
    private _TAB  tab = _TAB.zahlungsbedingungen;  


	public RECORDNEW_ZAHLUNGSBEDINGUNGEN() throws myException 
	{
		super("JT_ZAHLUNGSBEDINGUNGEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ZAHLUNGSBEDINGUNGEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ZAHLUNGSBEDINGUNGEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_ZAHLUNGSBEDINGUNGEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ZAHLUNGSBEDINGUNGEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}

	
	
	public RECORD_ZAHLUNGSBEDINGUNGEN do_WRITE_NEW_ZAHLUNGSBEDINGUNGEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ZAHLUNGSBEDINGUNGEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ZAHLUNGSBEDINGUNGEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ZAHLUNGSBEDINGUNGEN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_STANDARD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_STANDARD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STANDARD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KURZBEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KURZBEZEICHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", calNewValueFormated);
	}
	}
