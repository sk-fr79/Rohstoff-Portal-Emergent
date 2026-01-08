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

public class RECORDNEW_M2N extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_M2N";
    private _TAB  tab = _TAB.m2n;  


	public RECORDNEW_M2N() throws myException 
	{
		super("JT_M2N");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_M2N.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_M2N(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_M2N", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_M2N.TABLENAME);
	}
	
	
	
	public RECORDNEW_M2N(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_M2N");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_M2N.TABLENAME);
	}

	
	
	public RECORD_M2N do_WRITE_NEW_M2N(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_M2N");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_M2N(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table M2N ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_M2N(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_M2N", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_M2N(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_M2N", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_M2N(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_M2N", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_M2N(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_M2N", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_M2N(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_M2N", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_M2N(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_M2N", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_M2N_CONTEXT_ENUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("M2N_CONTEXT_ENUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_M2N_CONTEXT_ENUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("M2N_CONTEXT_ENUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_M2N_CONTEXT_ENUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("M2N_CONTEXT_ENUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_M2N_CONTEXT_ENUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("M2N_CONTEXT_ENUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_M2N_CONTEXT_ENUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("M2N_CONTEXT_ENUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_M2N_CONTEXT_ENUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("M2N_CONTEXT_ENUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STAERKE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STAERKE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAERKE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAERKE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STAERKE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_BASE_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_BASE_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_BASE_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_BASE_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_BASE_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_BASE_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_ID_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_ID_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLE_ID_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLE_ID_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLE_ID_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLE_ID_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", calNewValueFormated);
	}
	}
