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

public class RECORDNEW_REPORTING_QUERY_FIELD extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORTING_QUERY_FIELD";
    private _TAB  tab = _TAB.reporting_query_field;  


	public RECORDNEW_REPORTING_QUERY_FIELD() throws myException 
	{
		super("JT_REPORTING_QUERY_FIELD");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY_FIELD.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORTING_QUERY_FIELD(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORTING_QUERY_FIELD", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY_FIELD.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORTING_QUERY_FIELD(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORTING_QUERY_FIELD");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORTING_QUERY_FIELD.TABLENAME);
	}

	
	
	public RECORD_REPORTING_QUERY_FIELD do_WRITE_NEW_REPORTING_QUERY_FIELD(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORTING_QUERY_FIELD");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORTING_QUERY_FIELD(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORTING_QUERY_FIELD ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALIGNMENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALIGNMENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BREITE_LISTE_PX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BREITE_LISTE_PX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME_4_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELDNAME_4_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORTING_QUERY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORTING_QUERY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORTING_QUERY_FIELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IS_SEARCHFIELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IS_SEARCHFIELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IS_SELECTORFIELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IS_SELECTORFIELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERFOLGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTIERFOLGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTIERFOLGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERFOLGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERFOLGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERFOLGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERFOLGE", calNewValueFormated);
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
