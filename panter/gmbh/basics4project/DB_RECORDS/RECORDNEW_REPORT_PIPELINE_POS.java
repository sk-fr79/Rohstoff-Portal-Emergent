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

public class RECORDNEW_REPORT_PIPELINE_POS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_REPORT_PIPELINE_POS";
    private _TAB  tab = _TAB.report_pipeline_pos;  


	public RECORDNEW_REPORT_PIPELINE_POS() throws myException 
	{
		super("JT_REPORT_PIPELINE_POS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_PIPELINE_POS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_REPORT_PIPELINE_POS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_REPORT_PIPELINE_POS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_PIPELINE_POS.TABLENAME);
	}
	
	
	
	public RECORDNEW_REPORT_PIPELINE_POS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_REPORT_PIPELINE_POS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_REPORT_PIPELINE_POS.TABLENAME);
	}

	
	
	public RECORD_REPORT_PIPELINE_POS do_WRITE_NEW_REPORT_PIPELINE_POS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_REPORT_PIPELINE_POS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_REPORT_PIPELINE_POS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table REPORT_PIPELINE_POS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARCHIV_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARCHIV_TABLENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DRUCKER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DRUCKER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_PIPELINE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORT_PIPELINE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_PIPELINE_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_REPORT_PIPELINE_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORIGINAL_DOKUMENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORIGINAL_DOKUMENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RELEVANT_4_MAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RELEVANT_4_MAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RELEVANT_4_PREVIEW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RELEVANT_4_PREVIEW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RELEVANT_4_PRINT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RELEVANT_4_PRINT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REPORTFILEBASENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REPORTFILEBASENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQL_ARCHIVFILENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQL_ARCHIVFILENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQL_ARCHIV_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQL_ARCHIV_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQL_EXEC_TRUE_FALSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP_VERARBEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", calNewValueFormated);
	}
	}
