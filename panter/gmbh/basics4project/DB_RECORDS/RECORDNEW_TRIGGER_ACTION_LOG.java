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

public class RECORDNEW_TRIGGER_ACTION_LOG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TRIGGER_ACTION_LOG";
    private _TAB  tab = _TAB.trigger_action_log;  


	public RECORDNEW_TRIGGER_ACTION_LOG() throws myException 
	{
		super("JT_TRIGGER_ACTION_LOG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TRIGGER_ACTION_LOG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TRIGGER_ACTION_LOG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TRIGGER_ACTION_LOG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TRIGGER_ACTION_LOG.TABLENAME);
	}
	
	
	
	public RECORDNEW_TRIGGER_ACTION_LOG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TRIGGER_ACTION_LOG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TRIGGER_ACTION_LOG.TABLENAME);
	}

	
	
	public RECORD_TRIGGER_ACTION_LOG do_WRITE_NEW_TRIGGER_ACTION_LOG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TRIGGER_ACTION_LOG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TRIGGER_ACTION_LOG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TRIGGER_ACTION_LOG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DB_ACTION_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DB_ACTION_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DB_ACTION_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DB_ACTION_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DB_ACTION_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DB_ACTION_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DB_ACTION_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DB_ACTION_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DB_ACTION_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DB_ACTION_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DB_ACTION_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DB_ACTION_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TRIGGER_ACTION_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TRIGGER_ACTION_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_LOG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_LOG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TRIGGER_ACTION_LOG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TRIGGER_ACTION_LOG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_LOG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_LOG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_LOG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_LOG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_LOG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_LOG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TRIGGER_ACTION_LOG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRIGGER_ACTION_LOG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_BASENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_BASENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASENAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TABLE_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_USER_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_USER_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("USER_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_USER_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_USER_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VAL_AFTER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_AFTER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VAL_AFTER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VAL_AFTER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VAL_AFTER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_AFTER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VAL_AFTER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_AFTER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VAL_AFTER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_AFTER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VAL_AFTER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_AFTER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VAL_BEFORE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_BEFORE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VAL_BEFORE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VAL_BEFORE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VAL_BEFORE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_BEFORE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VAL_BEFORE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_BEFORE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VAL_BEFORE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_BEFORE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VAL_BEFORE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VAL_BEFORE", calNewValueFormated);
	}
	}
