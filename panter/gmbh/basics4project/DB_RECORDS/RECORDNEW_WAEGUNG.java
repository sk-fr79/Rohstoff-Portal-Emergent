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

public class RECORDNEW_WAEGUNG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_WAEGUNG";
    private _TAB  tab = _TAB.waegung;  


	public RECORDNEW_WAEGUNG() throws myException 
	{
		super("JT_WAEGUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WAEGUNG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_WAEGUNG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_WAEGUNG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WAEGUNG.TABLENAME);
	}
	
	
	
	public RECORDNEW_WAEGUNG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_WAEGUNG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WAEGUNG.TABLENAME);
	}

	
	
	public RECORD_WAEGUNG do_WRITE_NEW_WAEGUNG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_WAEGUNG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_WAEGUNG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table WAEGUNG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HANDEINGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HANDEINGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HANDEINGABE_BEM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HANDEINGABE_BEM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_WAEGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_WAEGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAAGE_SETTINGS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAAGE_SETTINGS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAEGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAEGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEGUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAAGE_DS_ORI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAAGE_DS_ORI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEGUNG_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEGUNG_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEGUNG_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEGUNG_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEGUNG_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEGUNG_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEGUNG_POS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_BRUTTO_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_BRUTTO_GEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_EINHEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_EINHEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_EINHEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_EINHEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_EINHEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_EINHEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_EINHEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_FEHLERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_FEHLERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_FEHLERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_FEHLERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_FEHLERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_FEHLERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_FEHLERCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_IDENT_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_IDENT_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_IDENT_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_IDENT_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_IDENT_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_IDENT_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_IDENT_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_NETTO_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_NETTO_GEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_PRUEFZIFFER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_PRUEFZIFFER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_STATUS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_TARACODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARACODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_TARACODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_TARACODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARACODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARACODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARACODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARACODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_TARAGEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_TARAGEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TERMINAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_TERMINAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_TERMINAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TERMINAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TERMINAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TERMINAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_TERMINAL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_WAAGEN_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_WAAGEN_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_WAEGEBEREICH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_WAEGEBEREICH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_W_ZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_ZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_W_ZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("W_ZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_ZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_ZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_ZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("W_ZEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", calNewValueFormated);
	}
	}
