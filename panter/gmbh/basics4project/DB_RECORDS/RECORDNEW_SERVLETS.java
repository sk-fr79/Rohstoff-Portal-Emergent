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

public class RECORDNEW_SERVLETS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_SERVLETS";
    private _TAB  tab = _TAB.servlets;  


	public RECORDNEW_SERVLETS() throws myException 
	{
		super("JD_SERVLETS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SERVLETS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SERVLETS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_SERVLETS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SERVLETS.TABLENAME);
	}
	
	
	
	public RECORDNEW_SERVLETS(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_SERVLETS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SERVLETS.TABLENAME);
	}

	
	
	public RECORD_SERVLETS do_WRITE_NEW_SERVLETS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SERVLETS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SERVLETS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SERVLETS ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_HAUPTMENUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HAUPTMENUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_HAUPTMENUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_HAUPTMENUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_HAUPTMENUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HAUPTMENUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HAUPTMENUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HAUPTMENUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HAUPTMENUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HAUPTMENUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_HAUPTMENUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HAUPTMENUE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SERVLETS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SERVLETS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SERVLETS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SERVLETS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SERVLETS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SERVLETS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SERVLETS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SERVLETS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SERVLETS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SERVLETS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SERVLETS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SERVLETS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENUEEINTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENUEEINTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENUEEINTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENUEEINTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENUEEINTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENUEEINTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENUEEINTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENUEEINTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENUEEINTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENUEEINTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENUEEINTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENUEEINTRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SERVLETAUFRUF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SERVLETAUFRUF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SERVLETAUFRUF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SERVLETAUFRUF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SERVLETAUFRUF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SERVLETAUFRUF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SERVLETAUFRUF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SERVLETAUFRUF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SERVLETAUFRUF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SERVLETAUFRUF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SERVLETAUFRUF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SERVLETAUFRUF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTNUMMER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABTEXT", calNewValueFormated);
	}
	}
