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

public class RECORDNEW_Z_TEST extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_Z_TEST";
    private _TAB  tab = _TAB.z_test;  


	public RECORDNEW_Z_TEST() throws myException 
	{
		super("JT_Z_TEST");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_Z_TEST.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_Z_TEST(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_Z_TEST", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_Z_TEST.TABLENAME);
	}
	
	
	
	public RECORDNEW_Z_TEST(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_Z_TEST");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_Z_TEST.TABLENAME);
	}

	
	
	public RECORD_Z_TEST do_WRITE_NEW_Z_TEST(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_Z_TEST");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_Z_TEST(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table Z_TEST ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_Z_TEST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_Z_TEST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_Z_TEST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_Z_TEST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_Z_TEST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_Z_TEST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_Z_TEST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_Z_TEST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_Z_TEST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_Z_TEST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_Z_TEST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_Z_TEST", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEST_BIGDECIMAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BIGDECIMAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEST_BIGDECIMAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEST_BIGDECIMAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEST_BIGDECIMAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BIGDECIMAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_BIGDECIMAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BIGDECIMAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_BIGDECIMAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BIGDECIMAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEST_BIGDECIMAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BIGDECIMAL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEST_BOOLEAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BOOLEAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEST_BOOLEAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEST_BOOLEAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEST_BOOLEAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BOOLEAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_BOOLEAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BOOLEAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_BOOLEAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BOOLEAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEST_BOOLEAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_BOOLEAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEST_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEST_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEST_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEST_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEST_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEST_LONG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_LONG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEST_LONG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEST_LONG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEST_LONG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_LONG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_LONG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_LONG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_LONG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_LONG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEST_LONG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_LONG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEST_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEST_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEST_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEST_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEST_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEST_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEST_TEXT", calNewValueFormated);
	}
	}
