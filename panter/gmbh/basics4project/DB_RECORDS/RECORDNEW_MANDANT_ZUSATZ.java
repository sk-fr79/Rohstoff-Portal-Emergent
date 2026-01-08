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

public class RECORDNEW_MANDANT_ZUSATZ extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_MANDANT_ZUSATZ";
    private _TAB  tab = _TAB.mandant_zusatz;  


	public RECORDNEW_MANDANT_ZUSATZ() throws myException 
	{
		super("JD_MANDANT_ZUSATZ");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT_ZUSATZ.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MANDANT_ZUSATZ(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_MANDANT_ZUSATZ", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT_ZUSATZ.TABLENAME);
	}
	
	
	
	public RECORDNEW_MANDANT_ZUSATZ(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_MANDANT_ZUSATZ");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT_ZUSATZ.TABLENAME);
	}

	
	
	public RECORD_MANDANT_ZUSATZ do_WRITE_NEW_MANDANT_ZUSATZ(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MANDANT_ZUSATZ");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MANDANT_ZUSATZ(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MANDANT_ZUSATZ ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FLOAT_VALUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLOAT_VALUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FLOAT_VALUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FLOAT_VALUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FLOAT_VALUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLOAT_VALUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FLOAT_VALUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLOAT_VALUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FLOAT_VALUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLOAT_VALUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FLOAT_VALUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FLOAT_VALUE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MANDANT_ZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LONG_VALUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONG_VALUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LONG_VALUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LONG_VALUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LONG_VALUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONG_VALUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LONG_VALUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONG_VALUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LONG_VALUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONG_VALUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LONG_VALUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONG_VALUE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT_VALUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_VALUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT_VALUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT_VALUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_VALUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_VALUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_VALUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_VALUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_VALUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_VALUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_VALUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_VALUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_YES_NO_VALUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YES_NO_VALUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_YES_NO_VALUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("YES_NO_VALUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_YES_NO_VALUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YES_NO_VALUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_YES_NO_VALUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YES_NO_VALUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_YES_NO_VALUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YES_NO_VALUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_YES_NO_VALUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("YES_NO_VALUE", calNewValueFormated);
	}
	}
