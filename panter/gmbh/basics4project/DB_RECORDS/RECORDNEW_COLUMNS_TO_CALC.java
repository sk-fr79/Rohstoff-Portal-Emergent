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

public class RECORDNEW_COLUMNS_TO_CALC extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_COLUMNS_TO_CALC";
    private _TAB  tab = _TAB.columns_to_calc;  


	public RECORDNEW_COLUMNS_TO_CALC() throws myException 
	{
		super("JT_COLUMNS_TO_CALC");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_COLUMNS_TO_CALC.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_COLUMNS_TO_CALC(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_COLUMNS_TO_CALC", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_COLUMNS_TO_CALC.TABLENAME);
	}
	
	
	
	public RECORDNEW_COLUMNS_TO_CALC(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_COLUMNS_TO_CALC");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_COLUMNS_TO_CALC.TABLENAME);
	}

	
	
	public RECORD_COLUMNS_TO_CALC do_WRITE_NEW_COLUMNS_TO_CALC(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_COLUMNS_TO_CALC");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_COLUMNS_TO_CALC(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table COLUMNS_TO_CALC ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ACTIVE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ACTIVE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ACTIVE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ACTIVE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ACTIVE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ACTIVE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ACTIVE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ACTIVE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ACTIVE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ACTIVE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ACTIVE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ACTIVE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN_LABEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLUMN_LABEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLUMN_LABEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN_LABEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN_LABEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN_LABEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLUMN_LABEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_COLUMNS_TO_CALC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_COLUMNS_TO_CALC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODULNAME_LISTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODULNAME_LISTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMBER_DECIMALS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMBER_DECIMALS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUMMATION_VIA_QUERY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUMMATION_VIA_QUERY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT4SUMMATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT4SUMMATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT4TITLE_IN_WINDOW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT4WINDOWTITLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT4WINDOWTITLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIPS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TOOLTIPS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TOOLTIPS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIPS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIPS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIPS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOOLTIPS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VALIDATION_TAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VALIDATION_TAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VALIDATION_TAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VALIDATION_TAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VALIDATION_TAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VALIDATION_TAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VALIDATION_TAG", calNewValueFormated);
	}
	}
