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

public class RECORDNEW_ENUM_VEKTOR_POS_TYP extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_ENUM_VEKTOR_POS_TYP";
    private _TAB  tab = _TAB.enum_vektor_pos_typ;  


	public RECORDNEW_ENUM_VEKTOR_POS_TYP() throws myException 
	{
		super("JD_ENUM_VEKTOR_POS_TYP");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ENUM_VEKTOR_POS_TYP.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ENUM_VEKTOR_POS_TYP(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_ENUM_VEKTOR_POS_TYP", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ENUM_VEKTOR_POS_TYP.TABLENAME);
	}
	
	
	
	public RECORDNEW_ENUM_VEKTOR_POS_TYP(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_ENUM_VEKTOR_POS_TYP");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ENUM_VEKTOR_POS_TYP.TABLENAME);
	}

	
	
	public RECORD_ENUM_VEKTOR_POS_TYP do_WRITE_NEW_ENUM_VEKTOR_POS_TYP(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ENUM_VEKTOR_POS_TYP");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ENUM_VEKTOR_POS_TYP(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ENUM_VEKTOR_POS_TYP ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AUTOMATIC_DELETE_WRITE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTOMATIC_DELETE_WRITE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUTOMATIC_DELETE_WRITE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUTOMATIC_DELETE_WRITE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUTOMATIC_DELETE_WRITE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTOMATIC_DELETE_WRITE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUTOMATIC_DELETE_WRITE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTOMATIC_DELETE_WRITE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUTOMATIC_DELETE_WRITE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTOMATIC_DELETE_WRITE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUTOMATIC_DELETE_WRITE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTOMATIC_DELETE_WRITE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONTAINS_MAIN_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINS_MAIN_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTAINS_MAIN_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTAINS_MAIN_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINS_MAIN_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINS_MAIN_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINS_MAIN_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINS_MAIN_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINS_MAIN_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINS_MAIN_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINS_MAIN_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINS_MAIN_POS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EDIT_ROW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDIT_ROW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EDIT_ROW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EDIT_ROW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EDIT_ROW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDIT_ROW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EDIT_ROW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDIT_ROW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EDIT_ROW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDIT_ROW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EDIT_ROW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EDIT_ROW", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ENUM_VEKTOR_POS_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ENUM_VEKTOR_POS_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ENUM_VEKTOR_POS_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ENUM_VEKTOR_POS_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ENUM_VEKTOR_POS_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ENUM_VEKTOR_POS_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ENUM_VEKTOR_POS_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ENUM_VEKTOR_POS_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ENUM_VEKTOR_POS_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ENUM_VEKTOR_POS_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ENUM_VEKTOR_POS_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ENUM_VEKTOR_POS_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", calNewValueFormated);
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
