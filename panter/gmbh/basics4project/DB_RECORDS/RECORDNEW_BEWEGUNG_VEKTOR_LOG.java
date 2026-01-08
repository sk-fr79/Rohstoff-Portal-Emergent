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

public class RECORDNEW_BEWEGUNG_VEKTOR_LOG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BEWEGUNG_VEKTOR_LOG";
    private _TAB  tab = _TAB.bewegung_vektor_log;  


	public RECORDNEW_BEWEGUNG_VEKTOR_LOG() throws myException 
	{
		super("JT_BEWEGUNG_VEKTOR_LOG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_VEKTOR_LOG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BEWEGUNG_VEKTOR_LOG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BEWEGUNG_VEKTOR_LOG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_VEKTOR_LOG.TABLENAME);
	}
	
	
	
	public RECORDNEW_BEWEGUNG_VEKTOR_LOG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BEWEGUNG_VEKTOR_LOG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_VEKTOR_LOG.TABLENAME);
	}

	
	
	public RECORD_BEWEGUNG_VEKTOR_LOG do_WRITE_NEW_BEWEGUNG_VEKTOR_LOG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BEWEGUNG_VEKTOR_LOG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BEWEGUNG_VEKTOR_LOG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BEWEGUNG_VEKTOR_LOG ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_LOG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_LOG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_VEKTOR_LOG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_LOG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_LOG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_LOG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_LOG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_LOG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_LOG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_LOG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_LOG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_LOG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHL_GUTPOS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHL_GUTPOS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHL_RECHPOS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHL_RECHPOS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", calNewValueFormated);
	}
	}
