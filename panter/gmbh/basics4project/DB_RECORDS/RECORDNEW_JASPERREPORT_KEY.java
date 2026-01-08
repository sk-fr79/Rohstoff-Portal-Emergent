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

public class RECORDNEW_JASPERREPORT_KEY extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_JASPERREPORT_KEY";
    private _TAB  tab = _TAB.jasperreport_key;  


	public RECORDNEW_JASPERREPORT_KEY() throws myException 
	{
		super("JD_JASPERREPORT_KEY");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_JASPERREPORT_KEY.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_JASPERREPORT_KEY(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_JASPERREPORT_KEY", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_JASPERREPORT_KEY.TABLENAME);
	}
	
	
	
	public RECORDNEW_JASPERREPORT_KEY(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_JASPERREPORT_KEY");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_JASPERREPORT_KEY.TABLENAME);
	}

	
	
	public RECORD_JASPERREPORT_KEY do_WRITE_NEW_JASPERREPORT_KEY(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_JASPERREPORT_KEY");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_JASPERREPORT_KEY(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table JASPERREPORT_KEY ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULTVALUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULTVALUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULTVALUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULTVALUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULTVALUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULTVALUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULTVALUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_JASPERREPORT_KEY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPERREPORT_KEY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_JASPERREPORT_KEY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_JASPERREPORT_KEY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPERREPORT_KEY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPERREPORT_KEY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_JASPERREPORT_KEY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPERREPORT_KEY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_JASPERREPORT_KEY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPERREPORT_KEY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPERREPORT_KEY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPERREPORT_KEY", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KEYAREA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYAREA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEYAREA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEYAREA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEYAREA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYAREA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEYAREA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYAREA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEYAREA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYAREA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEYAREA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYAREA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEYCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEYCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEYCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEYCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEYCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEYCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEYCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEYTYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYTYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEYTYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEYTYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEYTYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYTYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEYTYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYTYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEYTYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYTYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEYTYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEYTYPE", calNewValueFormated);
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
