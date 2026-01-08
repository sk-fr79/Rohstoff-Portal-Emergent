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

public class RECORDNEW_SHEET_CONTENT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_SHEET_CONTENT";
    private _TAB  tab = _TAB.sheet_content;  


	public RECORDNEW_SHEET_CONTENT() throws myException 
	{
		super("JT_SHEET_CONTENT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET_CONTENT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SHEET_CONTENT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_SHEET_CONTENT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET_CONTENT.TABLENAME);
	}
	
	
	
	public RECORDNEW_SHEET_CONTENT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_SHEET_CONTENT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET_CONTENT.TABLENAME);
	}

	
	
	public RECORD_SHEET_CONTENT do_WRITE_NEW_SHEET_CONTENT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SHEET_CONTENT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SHEET_CONTENT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SHEET_CONTENT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_COLUMN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLUMN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLUMN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLUMN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLUMN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLUMN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLUMN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONTENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTENT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SHEET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SHEET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SHEET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_CONTENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_CONTENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SHEET_CONTENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SHEET_CONTENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_CONTENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_CONTENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_CONTENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_CONTENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_CONTENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_CONTENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_CONTENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_CONTENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SHEET_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SHEET_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SHEET_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SHEET_DEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ROW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ROW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ROW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ROW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ROW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROW", calNewValueFormated);
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
