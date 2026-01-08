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

public class RECORDNEW_FIELD_RULE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FIELD_RULE";
    private _TAB  tab = _TAB.field_rule;  


	public RECORDNEW_FIELD_RULE() throws myException 
	{
		super("JT_FIELD_RULE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIELD_RULE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FIELD_RULE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FIELD_RULE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIELD_RULE.TABLENAME);
	}
	
	
	
	public RECORDNEW_FIELD_RULE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FIELD_RULE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIELD_RULE.TABLENAME);
	}

	
	
	public RECORD_FIELD_RULE do_WRITE_NEW_FIELD_RULE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FIELD_RULE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FIELD_RULE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FIELD_RULE ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_FIELD_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELD_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELD_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELD_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELD_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_NAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FIELD_RULE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIELD_RULE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIELD_RULE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIELD_RULE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIELD_RULE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIELD_RULE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIELD_RULE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIELD_RULE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIELD_RULE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIELD_RULE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIELD_RULE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIELD_RULE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MODUL_KENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_KENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODUL_KENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODUL_KENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODUL_KENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_KENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_KENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_KENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_KENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_KENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODUL_KENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODUL_KENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RULE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RULE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RULE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RULE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RULE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RULE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RULE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RULETYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULETYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RULETYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RULETYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RULETYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULETYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RULETYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULETYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RULETYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULETYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RULETYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULETYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RULE_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RULE_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RULE_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RULE_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RULE_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RULE_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RULE_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RULE_INFO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_NAME", calNewValueFormated);
	}
	}
