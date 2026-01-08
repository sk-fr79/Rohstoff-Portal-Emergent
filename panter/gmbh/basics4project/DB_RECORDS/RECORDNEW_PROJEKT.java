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

public class RECORDNEW_PROJEKT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_PROJEKT";
    private _TAB  tab = _TAB.projekt;  


	public RECORDNEW_PROJEKT() throws myException 
	{
		super("JT_PROJEKT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROJEKT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_PROJEKT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_PROJEKT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROJEKT.TABLENAME);
	}
	
	
	
	public RECORDNEW_PROJEKT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_PROJEKT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROJEKT.TABLENAME);
	}

	
	
	public RECORD_PROJEKT do_WRITE_NEW_PROJEKT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_PROJEKT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_PROJEKT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table PROJEKT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEENDET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEENDET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEENDET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEENDET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEENDET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEENDET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEENDET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEENDET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEENDET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEENDET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEENDET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEENDET", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PROJEKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PROJEKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PROJEKTGEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PROJEKTGEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROJEKTBEGIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROJEKTBEGIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROJEKTBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROJEKTBESCHREIBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROJEKTDEADLINE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROJEKTDEADLINE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROJEKTNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROJEKTNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROJEKTNAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEDERVORLAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEDERVORLAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEDERVORLAGE_DELTA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEDERVORLAGE_DELTA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", calNewValueFormated);
	}
	}
