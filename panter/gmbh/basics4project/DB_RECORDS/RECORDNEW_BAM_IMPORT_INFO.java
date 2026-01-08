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

public class RECORDNEW_BAM_IMPORT_INFO extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BAM_IMPORT_INFO";
    private _TAB  tab = _TAB.bam_import_info;  


	public RECORDNEW_BAM_IMPORT_INFO() throws myException 
	{
		super("JT_BAM_IMPORT_INFO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BAM_IMPORT_INFO.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BAM_IMPORT_INFO(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BAM_IMPORT_INFO", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BAM_IMPORT_INFO.TABLENAME);
	}
	
	
	
	public RECORDNEW_BAM_IMPORT_INFO(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BAM_IMPORT_INFO");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BAM_IMPORT_INFO.TABLENAME);
	}

	
	
	public RECORD_BAM_IMPORT_INFO do_WRITE_NEW_BAM_IMPORT_INFO(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BAM_IMPORT_INFO");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BAM_IMPORT_INFO(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BAM_IMPORT_INFO ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANHAFTEND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAFTEND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHAFTEND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHAFTEND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHAFTEND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAFTEND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAFTEND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAFTEND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAFTEND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAFTEND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHAFTEND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAFTEND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ABZUGSGRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ABZUGSGRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BAM_IMPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BAM_IMPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BAM_IMPORT_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BAM_IMPORT_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT_INFO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFOS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFOS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFOS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZUSATZINFOS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFOS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFOS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFOS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFOS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFOS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFOS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFOS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZUSATZINFOS", calNewValueFormated);
	}
	}
