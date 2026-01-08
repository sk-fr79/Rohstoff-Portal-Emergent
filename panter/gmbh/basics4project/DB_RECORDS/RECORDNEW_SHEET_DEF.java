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

public class RECORDNEW_SHEET_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_SHEET_DEF";
    private _TAB  tab = _TAB.sheet_def;  


	public RECORDNEW_SHEET_DEF() throws myException 
	{
		super("JT_SHEET_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SHEET_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_SHEET_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_SHEET_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_SHEET_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SHEET_DEF.TABLENAME);
	}

	
	
	public RECORD_SHEET_DEF do_WRITE_NEW_SHEET_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SHEET_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SHEET_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SHEET_DEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_DATATYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATATYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATATYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATATYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATATYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATATYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATATYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATATYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATATYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATATYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATATYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATATYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DECIMALSIZE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECIMALSIZE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DECIMALSIZE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DECIMALSIZE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DECIMALSIZE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECIMALSIZE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DECIMALSIZE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECIMALSIZE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DECIMALSIZE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECIMALSIZE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DECIMALSIZE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DECIMALSIZE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EDITABLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDITABLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EDITABLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EDITABLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EDITABLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDITABLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EDITABLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDITABLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EDITABLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDITABLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EDITABLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDITABLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FORMULAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FORMULAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FORMULAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULAR", calNewValueFormated);
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
