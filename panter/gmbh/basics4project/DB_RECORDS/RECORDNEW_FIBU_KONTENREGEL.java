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

public class RECORDNEW_FIBU_KONTENREGEL extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FIBU_KONTENREGEL";
    private _TAB  tab = _TAB.fibu_kontenregel;  


	public RECORDNEW_FIBU_KONTENREGEL() throws myException 
	{
		super("JT_FIBU_KONTENREGEL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU_KONTENREGEL.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FIBU_KONTENREGEL(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FIBU_KONTENREGEL", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU_KONTENREGEL.TABLENAME);
	}
	
	
	
	public RECORDNEW_FIBU_KONTENREGEL(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FIBU_KONTENREGEL");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU_KONTENREGEL.TABLENAME);
	}

	
	
	public RECORD_FIBU_KONTENREGEL do_WRITE_NEW_FIBU_KONTENREGEL(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FIBU_KONTENREGEL");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FIBU_KONTENREGEL(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FIBU_KONTENREGEL ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DEF_EK_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_EK_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEF_EK_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEF_EK_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEF_EK_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_EK_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEF_EK_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_EK_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEF_EK_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_EK_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEF_EK_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_EK_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DIENSTLEISTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTENREGEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU_KONTENREGEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU_KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", calNewValueFormated);
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
