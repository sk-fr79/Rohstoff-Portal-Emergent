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

public class RECORDNEW_MANDANT_STEUERVERMERK extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_MANDANT_STEUERVERMERK";
    private _TAB  tab = _TAB.mandant_steuervermerk;  


	public RECORDNEW_MANDANT_STEUERVERMERK() throws myException 
	{
		super("JD_MANDANT_STEUERVERMERK");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT_STEUERVERMERK.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MANDANT_STEUERVERMERK(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_MANDANT_STEUERVERMERK", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT_STEUERVERMERK.TABLENAME);
	}
	
	
	
	public RECORDNEW_MANDANT_STEUERVERMERK(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_MANDANT_STEUERVERMERK");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT_STEUERVERMERK.TABLENAME);
	}

	
	
	public RECORD_MANDANT_STEUERVERMERK do_WRITE_NEW_MANDANT_STEUERVERMERK(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MANDANT_STEUERVERMERK");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MANDANT_STEUERVERMERK(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MANDANT_STEUERVERMERK ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DROPDOWN_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DROPDOWN_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_INFO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_STEUERVERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_STEUERVERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT_STEUERVERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MANDANT_STEUERVERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_STEUERVERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_STEUERVERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_STEUERVERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_STEUERVERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_STEUERVERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_STEUERVERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_STEUERVERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_STEUERVERMERK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REVERSE_CHARGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REVERSE_CHARGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REVERSE_CHARGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REVERSE_CHARGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_RECHNUNG", calNewValueFormated);
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
