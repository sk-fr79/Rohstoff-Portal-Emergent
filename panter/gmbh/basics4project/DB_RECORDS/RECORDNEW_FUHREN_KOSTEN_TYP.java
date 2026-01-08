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

public class RECORDNEW_FUHREN_KOSTEN_TYP extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FUHREN_KOSTEN_TYP";
    private _TAB  tab = _TAB.fuhren_kosten_typ;  


	public RECORDNEW_FUHREN_KOSTEN_TYP() throws myException 
	{
		super("JT_FUHREN_KOSTEN_TYP");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_KOSTEN_TYP.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FUHREN_KOSTEN_TYP(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FUHREN_KOSTEN_TYP", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_KOSTEN_TYP.TABLENAME);
	}
	
	
	
	public RECORDNEW_FUHREN_KOSTEN_TYP(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FUHREN_KOSTEN_TYP");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_KOSTEN_TYP.TABLENAME);
	}

	
	
	public RECORD_FUHREN_KOSTEN_TYP do_WRITE_NEW_FUHREN_KOSTEN_TYP(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FUHREN_KOSTEN_TYP");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FUHREN_KOSTEN_TYP(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FUHREN_KOSTEN_TYP ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BETRIFFT_ZOLL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIFFT_ZOLL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETRIFFT_ZOLL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETRIFFT_ZOLL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETRIFFT_ZOLL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIFFT_ZOLL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRIFFT_ZOLL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIFFT_ZOLL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRIFFT_ZOLL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIFFT_ZOLL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETRIFFT_ZOLL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIFFT_ZOLL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KURZTEXT_UEBERSICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT_UEBERSICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KURZTEXT_UEBERSICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KURZTEXT_UEBERSICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT_UEBERSICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT_UEBERSICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT_UEBERSICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT_UEBERSICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT_UEBERSICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT_UEBERSICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT_UEBERSICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT_UEBERSICHT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NEUTRAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NEUTRAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NEUTRAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NEUTRAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NEUTRAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NEUTRAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NEUTRAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NEUTRAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NEUTRAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NEUTRAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NEUTRAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NEUTRAL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SPEDITIONSRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEDITIONSRECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SPEDITIONSRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SPEDITIONSRECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SPEDITIONSRECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEDITIONSRECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPEDITIONSRECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEDITIONSRECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPEDITIONSRECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEDITIONSRECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SPEDITIONSRECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEDITIONSRECHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT4BENUTZER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4BENUTZER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT4BENUTZER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT4BENUTZER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4BENUTZER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4BENUTZER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4BENUTZER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4BENUTZER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4BENUTZER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4BENUTZER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4BENUTZER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4BENUTZER", calNewValueFormated);
	}
	}
