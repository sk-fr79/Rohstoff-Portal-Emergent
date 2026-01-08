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

public class RECORDNEW_VKOPF_STD_DRUCK extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VKOPF_STD_DRUCK";
    private _TAB  tab = _TAB.vkopf_std_druck;  


	public RECORDNEW_VKOPF_STD_DRUCK() throws myException 
	{
		super("JT_VKOPF_STD_DRUCK");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VKOPF_STD_DRUCK.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VKOPF_STD_DRUCK(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VKOPF_STD_DRUCK", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VKOPF_STD_DRUCK.TABLENAME);
	}
	
	
	
	public RECORDNEW_VKOPF_STD_DRUCK(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VKOPF_STD_DRUCK");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VKOPF_STD_DRUCK.TABLENAME);
	}

	
	
	public RECORD_VKOPF_STD_DRUCK do_WRITE_NEW_VKOPF_STD_DRUCK(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VKOPF_STD_DRUCK");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VKOPF_STD_DRUCK(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VKOPF_STD_DRUCK ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BELEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGINFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESAMT_NETTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMT_NETTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMT_NETTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMT_NETTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMT_NETTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMT_NETTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMT_NETTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMT_NETTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMT_NETTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMT_NETTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMT_NETTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMT_NETTO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_STD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_STD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD_DRUCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_STD_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_STD_DRUCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_DRUCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD_DRUCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_DRUCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD_DRUCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_DRUCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD_DRUCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_DRUCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD_DRUCK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_VERSANDT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL_VERSANDT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_ORIGINAL_VERSANDT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_ORIGINAL_VERSANDT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_VERSANDT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL_VERSANDT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_VERSANDT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL_VERSANDT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_VERSANDT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL_VERSANDT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_VERSANDT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL_VERSANDT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITIONEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITIONEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITIONEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITIONEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITIONEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", calNewValueFormated);
	}
	}
