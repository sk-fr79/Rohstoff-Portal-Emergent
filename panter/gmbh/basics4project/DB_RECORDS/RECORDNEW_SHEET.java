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

public class RECORDNEW_SHEET extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_SHEET";
    private _TAB  tab = _TAB.sheet;  


	public RECORDNEW_SHEET() throws myException 
	{
		super("JT_SHEET");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SHEET(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_SHEET", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET.TABLENAME);
	}
	
	
	
	public RECORDNEW_SHEET(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_SHEET");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET.TABLENAME);
	}

	
	
	public RECORD_SHEET do_WRITE_NEW_SHEET(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SHEET");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SHEET(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SHEET ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BORDERSIZE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BORDERSIZE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BORDERSIZE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BORDERSIZE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BORDERSIZE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BORDERSIZE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BORDERSIZE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BORDERSIZE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BORDERSIZE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BORDERSIZE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BORDERSIZE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BORDERSIZE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CELLMARGIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CELLMARGIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CELLMARGIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CELLMARGIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CELLMARGIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CELLMARGIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CELLMARGIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CELLMARGIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CELLMARGIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CELLMARGIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CELLMARGIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CELLMARGIN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLORBLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORBLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLORBLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLORBLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLORBLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORBLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORBLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORBLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORBLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORBLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLORBLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORBLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLORGREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORGREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLORGREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLORGREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLORGREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORGREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORGREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORGREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORGREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORGREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLORGREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORGREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLORRED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORRED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLORRED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLORRED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLORRED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORRED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORRED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORRED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLORRED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORRED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLORRED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLORRED", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMBEROFCOLUMNS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFCOLUMNS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMBEROFCOLUMNS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMBEROFCOLUMNS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFCOLUMNS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFCOLUMNS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFCOLUMNS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFCOLUMNS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFCOLUMNS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFCOLUMNS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFCOLUMNS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFCOLUMNS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMBEROFROWS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFROWS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMBEROFROWS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMBEROFROWS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFROWS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFROWS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFROWS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFROWS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFROWS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFROWS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMBEROFROWS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMBEROFROWS", calNewValueFormated);
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
