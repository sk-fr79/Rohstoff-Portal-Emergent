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

public class RECORDNEW_NACHRICHT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_NACHRICHT";
    private _TAB  tab = _TAB.nachricht;  


	public RECORDNEW_NACHRICHT() throws myException 
	{
		super("JT_NACHRICHT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NACHRICHT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_NACHRICHT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_NACHRICHT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NACHRICHT.TABLENAME);
	}
	
	
	
	public RECORDNEW_NACHRICHT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_NACHRICHT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_NACHRICHT.TABLENAME);
	}

	
	
	public RECORD_NACHRICHT do_WRITE_NEW_NACHRICHT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_NACHRICHT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_NACHRICHT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table NACHRICHT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AKTIV_AB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV_AB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIV_AB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIV_AB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_AB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV_AB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV_AB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV_AB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV_AB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV_AB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_AB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV_AB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTAETIGT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTAETIGT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTAETIGT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTAETIGT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTAETIGT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTAETIGT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTAETIGT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTAETIGT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTAETIGT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTAETIGT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTAETIGT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTAETIGT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_SENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_SENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_SENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SENT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GELOESCHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GELOESCHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_GRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_GRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_GRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_GRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_GRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_GRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_GRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_GRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_GRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_GRUPPE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_NACHRICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_KATEGORIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_KATEGORIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_NACHRICHT_KATEGORIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_NACHRICHT_KATEGORIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_KATEGORIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_KATEGORIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_KATEGORIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_KATEGORIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_KATEGORIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_KATEGORIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_KATEGORIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_KATEGORIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_PARENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_PARENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_NACHRICHT_PARENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_NACHRICHT_PARENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_PARENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_PARENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_PARENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_PARENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_PARENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_PARENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_NACHRICHT_PARENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_NACHRICHT_PARENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SENDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SENDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SENDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SENDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SENDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SENDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SENDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SENDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SENDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SENDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SENDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SENDER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MSG_CONVERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MSG_CONVERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MSG_CONVERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MSG_CONVERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MSG_CONVERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MSG_CONVERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MSG_CONVERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MSG_CONVERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MSG_CONVERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MSG_CONVERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MSG_CONVERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MSG_CONVERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NACHRICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SEND_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_EMAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SEND_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SEND_EMAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SEND_EMAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_EMAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_EMAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_EMAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_EMAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_EMAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SEND_EMAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_EMAIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SOFORTANZEIGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOFORTANZEIGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SOFORTANZEIGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SOFORTANZEIGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SOFORTANZEIGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOFORTANZEIGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SOFORTANZEIGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOFORTANZEIGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SOFORTANZEIGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOFORTANZEIGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SOFORTANZEIGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOFORTANZEIGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TYP_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_NACHRICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP_NACHRICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP_NACHRICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_NACHRICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_NACHRICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_NACHRICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_NACHRICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_NACHRICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP_NACHRICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_NACHRICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WV_KENNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WV_KENNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WV_KENNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WV_KENNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WV_KENNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WV_KENNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WV_KENNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WV_KENNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WV_KENNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WV_KENNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WV_KENNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WV_KENNUNG", calNewValueFormated);
	}
	}
