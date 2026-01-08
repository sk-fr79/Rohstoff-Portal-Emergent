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

public class RECORDNEW_FILTER_OR extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FILTER_OR";
    private _TAB  tab = _TAB.filter_or;  


	public RECORDNEW_FILTER_OR() throws myException 
	{
		super("JT_FILTER_OR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FILTER_OR.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FILTER_OR(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FILTER_OR", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FILTER_OR.TABLENAME);
	}
	
	
	
	public RECORDNEW_FILTER_OR(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FILTER_OR");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FILTER_OR.TABLENAME);
	}

	
	
	public RECORD_FILTER_OR do_WRITE_NEW_FILTER_OR(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FILTER_OR");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FILTER_OR(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FILTER_OR ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONDITION_LEFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONDITION_LEFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONDITION_LEFT_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONDITION_LEFT_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_LEFT_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_LEFT_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONDITION_OP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_OP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONDITION_OP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONDITION_OP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_OP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_OP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_OP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_OP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_OP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_OP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_OP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_OP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONDITION_POSITIVE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_POSITIVE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONDITION_POSITIVE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONDITION_POSITIVE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_POSITIVE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_POSITIVE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_POSITIVE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_POSITIVE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_POSITIVE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_POSITIVE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_POSITIVE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_POSITIVE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONDITION_RIGHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONDITION_RIGHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONDITION_RIGHT_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONDITION_RIGHT_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONDITION_RIGHT_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONDITION_RIGHT_TYPE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_AND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_AND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FILTER_AND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FILTER_AND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_AND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_AND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_AND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_AND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_AND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_AND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_AND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_AND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_OR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_OR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FILTER_OR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FILTER_OR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_OR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_OR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_OR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_OR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_OR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_OR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FILTER_OR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FILTER_OR", calNewValueFormated);
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
