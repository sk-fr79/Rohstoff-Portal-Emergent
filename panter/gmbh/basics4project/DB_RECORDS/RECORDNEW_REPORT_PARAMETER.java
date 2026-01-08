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

public class RECORDNEW_REPORT_PARAMETER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORT_PARAMETER";
    private _TAB  tab = _TAB.report_parameter;  


	public RECORDNEW_REPORT_PARAMETER() throws myException 
	{
		super("JT_REPORT_PARAMETER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_PARAMETER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORT_PARAMETER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORT_PARAMETER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_PARAMETER.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORT_PARAMETER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORT_PARAMETER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_PARAMETER.TABLENAME);
	}

	
	
	public RECORD_REPORT_PARAMETER do_WRITE_NEW_REPORT_PARAMETER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORT_PARAMETER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORT_PARAMETER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORT_PARAMETER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNGSTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIBUNGSTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DROPDOWN_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DROPDOWN_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERSATZTEXT_FUER_LEER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERSATZTEXT_FUER_LEER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERSATZTEXT_FUER_WERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERSATZTEXT_FUER_WERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_PARAMETER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORT_PARAMETER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETERNAME_IN_REPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETERNAME_IN_REPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETERTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETERTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETERTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER_MUST_BE_SET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER_MUST_BE_SET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXTUSERINTERFACE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXTUSERINTERFACE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", calNewValueFormated);
	}
	}
