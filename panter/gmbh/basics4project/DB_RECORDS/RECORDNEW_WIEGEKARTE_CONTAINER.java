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

public class RECORDNEW_WIEGEKARTE_CONTAINER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_WIEGEKARTE_CONTAINER";
    private _TAB  tab = _TAB.wiegekarte_container;  


	public RECORDNEW_WIEGEKARTE_CONTAINER() throws myException 
	{
		super("JT_WIEGEKARTE_CONTAINER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WIEGEKARTE_CONTAINER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_WIEGEKARTE_CONTAINER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_WIEGEKARTE_CONTAINER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WIEGEKARTE_CONTAINER.TABLENAME);
	}
	
	
	
	public RECORDNEW_WIEGEKARTE_CONTAINER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_WIEGEKARTE_CONTAINER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WIEGEKARTE_CONTAINER.TABLENAME);
	}

	
	
	public RECORD_WIEGEKARTE_CONTAINER do_WRITE_NEW_WIEGEKARTE_CONTAINER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_WIEGEKARTE_CONTAINER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_WIEGEKARTE_CONTAINER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table WIEGEKARTE_CONTAINER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTAINER_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINERTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINERTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_CONTAINER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE_CONTAINER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_CONTAINER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_CONTAINER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_CONTAINER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_CONTAINER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_CONTAINER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_CONTAINER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_CONTAINER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_CONTAINER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TARA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TARA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TARA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TARA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TARA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA", calNewValueFormated);
	}
	}
