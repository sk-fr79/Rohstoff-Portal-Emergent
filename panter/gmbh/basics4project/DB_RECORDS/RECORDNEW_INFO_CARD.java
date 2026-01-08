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

public class RECORDNEW_INFO_CARD extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_INFO_CARD";
    private _TAB  tab = _TAB.info_card;  


	public RECORDNEW_INFO_CARD() throws myException 
	{
		super("JT_INFO_CARD");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_INFO_CARD.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_INFO_CARD(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_INFO_CARD", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_INFO_CARD.TABLENAME);
	}
	
	
	
	public RECORDNEW_INFO_CARD(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_INFO_CARD");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_INFO_CARD.TABLENAME);
	}

	
	
	public RECORD_INFO_CARD do_WRITE_NEW_INFO_CARD(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_INFO_CARD");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_INFO_CARD(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table INFO_CARD ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ARCHIVIERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIVIERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARCHIVIERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARCHIVIERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARCHIVIERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIVIERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARCHIVIERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIVIERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARCHIVIERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIVIERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARCHIVIERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARCHIVIERT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_INFO_CARD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INFO_CARD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_INFO_CARD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_INFO_CARD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_INFO_CARD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INFO_CARD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_INFO_CARD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INFO_CARD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_INFO_CARD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INFO_CARD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_INFO_CARD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INFO_CARD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BESITZER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BESITZER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_BEGINN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_BEGINN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEITRAUM_BEGINN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEITRAUM_BEGINN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_BEGINN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_BEGINN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_BEGINN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_BEGINN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_BEGINN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_BEGINN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_BEGINN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_BEGINN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_ENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_ENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEITRAUM_ENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEITRAUM_ENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_ENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_ENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_ENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_ENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_ENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_ENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEITRAUM_ENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITRAUM_ENDE", calNewValueFormated);
	}
	}
