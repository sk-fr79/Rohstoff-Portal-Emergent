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

public class RECORDNEW_KOMMUNIKATION extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_KOMMUNIKATION";
    private _TAB  tab = _TAB.kommunikation;  


	public RECORDNEW_KOMMUNIKATION() throws myException 
	{
		super("JT_KOMMUNIKATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KOMMUNIKATION.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_KOMMUNIKATION(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_KOMMUNIKATION", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KOMMUNIKATION.TABLENAME);
	}
	
	
	
	public RECORDNEW_KOMMUNIKATION(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_KOMMUNIKATION");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KOMMUNIKATION.TABLENAME);
	}

	
	
	public RECORD_KOMMUNIKATION do_WRITE_NEW_KOMMUNIKATION(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_KOMMUNIKATION");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_KOMMUNIKATION(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table KOMMUNIKATION ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KOMMUNIKATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KOMMUNIKATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATIONS_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATIONS_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KOMMUNIKATIONS_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KOMMUNIKATIONS_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATIONS_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATIONS_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATIONS_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATIONS_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATIONS_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATIONS_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KOMMUNIKATIONS_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KOMMUNIKATIONS_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WERT_LAENDERVORWAHL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_LAENDERVORWAHL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT_LAENDERVORWAHL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT_LAENDERVORWAHL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT_LAENDERVORWAHL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_LAENDERVORWAHL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_LAENDERVORWAHL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_LAENDERVORWAHL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_LAENDERVORWAHL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_LAENDERVORWAHL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT_LAENDERVORWAHL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_LAENDERVORWAHL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT_RUFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_RUFNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT_RUFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT_RUFNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT_RUFNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_RUFNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_RUFNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_RUFNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_RUFNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_RUFNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT_RUFNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_RUFNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT_SONSTIGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_SONSTIGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT_SONSTIGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT_SONSTIGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT_SONSTIGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_SONSTIGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_SONSTIGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_SONSTIGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_SONSTIGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_SONSTIGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT_SONSTIGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_SONSTIGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT_VORWAHL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_VORWAHL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT_VORWAHL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT_VORWAHL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT_VORWAHL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_VORWAHL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_VORWAHL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_VORWAHL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT_VORWAHL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_VORWAHL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT_VORWAHL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT_VORWAHL", calNewValueFormated);
	}
	}
