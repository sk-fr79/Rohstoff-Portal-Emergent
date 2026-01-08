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

public class RECORDNEW_VPOS_TPA_FUHRE_SONDER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE_SONDER";
    private _TAB  tab = _TAB.vpos_tpa_fuhre_sonder;  


	public RECORDNEW_VPOS_TPA_FUHRE_SONDER() throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_SONDER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_TPA_FUHRE_SONDER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_SONDER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_TPA_FUHRE_SONDER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_TPA_FUHRE_SONDER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}

	
	
	public RECORD_VPOS_TPA_FUHRE_SONDER do_WRITE_NEW_VPOS_TPA_FUHRE_SONDER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_TPA_FUHRE_SONDER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_TPA_FUHRE_SONDER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_TPA_FUHRE_SONDER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSNAHME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSNAHME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSNAHME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSNAHME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSNAHME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSNAHME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSNAHME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", calNewValueFormated);
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
