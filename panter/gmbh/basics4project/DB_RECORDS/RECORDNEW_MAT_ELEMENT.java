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

public class RECORDNEW_MAT_ELEMENT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_MAT_ELEMENT";
    private _TAB  tab = _TAB.mat_element;  


	public RECORDNEW_MAT_ELEMENT() throws myException 
	{
		super("JT_MAT_ELEMENT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAT_ELEMENT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MAT_ELEMENT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_MAT_ELEMENT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAT_ELEMENT.TABLENAME);
	}
	
	
	
	public RECORDNEW_MAT_ELEMENT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_MAT_ELEMENT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAT_ELEMENT.TABLENAME);
	}

	
	
	public RECORD_MAT_ELEMENT do_WRITE_NEW_MAT_ELEMENT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MAT_ELEMENT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MAT_ELEMENT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MAT_ELEMENT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEILMIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEILMIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEILMIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEILMIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEILMIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEILMIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEILMIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEILMIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEILMIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEILMIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEILMIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEILMIN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFANTEIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFANTEIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFANTEIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFANTEIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFANTEIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFANTEIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFANTEIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFANTEIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFANTEIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFANTEIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFANTEIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFANTEIL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ELEMENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ELEMENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ELEMENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ELEMENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ELEMENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ELEMENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ELEMENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ELEMENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ELEMENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ELEMENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ELEMENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ELEMENT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MAT_ELEMENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_ELEMENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MAT_ELEMENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MAT_ELEMENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_ELEMENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_ELEMENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_ELEMENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_ELEMENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_ELEMENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_ELEMENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_ELEMENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_ELEMENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MAT_SPEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_SPEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MAT_SPEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MAT_SPEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_SPEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_SPEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_SPEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_SPEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_SPEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_SPEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAT_SPEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAT_SPEZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INFOTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFOTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INFOTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INFOTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INFOTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFOTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFOTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFOTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFOTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFOTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INFOTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFOTEXT", calNewValueFormated);
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
	}
