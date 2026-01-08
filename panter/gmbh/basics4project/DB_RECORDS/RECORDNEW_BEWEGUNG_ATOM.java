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

public class RECORDNEW_BEWEGUNG_ATOM extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BEWEGUNG_ATOM";
    private _TAB  tab = _TAB.bewegung_atom;  


	public RECORDNEW_BEWEGUNG_ATOM() throws myException 
	{
		super("JT_BEWEGUNG_ATOM");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_ATOM.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BEWEGUNG_ATOM(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BEWEGUNG_ATOM", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_ATOM.TABLENAME);
	}
	
	
	
	public RECORDNEW_BEWEGUNG_ATOM(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BEWEGUNG_ATOM");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_ATOM.TABLENAME);
	}

	
	
	public RECORD_BEWEGUNG_ATOM do_WRITE_NEW_BEWEGUNG_ATOM(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BEWEGUNG_ATOM");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BEWEGUNG_ATOM(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BEWEGUNG_ATOM ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABGERECHNET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGERECHNET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGERECHNET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGERECHNET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGERECHNET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGERECHNET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGERECHNET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGERECHNET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGERECHNET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGERECHNET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGERECHNET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGERECHNET", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABRECHENBAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABRECHENBAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABRECHENBAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABRECHENBAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABRECHENBAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABRECHENBAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABRECHENBAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABRECHENBAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABRECHENBAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABRECHENBAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABRECHENBAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABRECHENBAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_BRUTTO_MGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_BRUTTO_MGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS_RESULT_BRUTTO_MGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS_RESULT_BRUTTO_MGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_BRUTTO_MGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_BRUTTO_MGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_BRUTTO_MGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_BRUTTO_MGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_BRUTTO_MGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_BRUTTO_MGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_BRUTTO_MGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_BRUTTO_MGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_NETTO_MGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_NETTO_MGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS_RESULT_NETTO_MGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS_RESULT_NETTO_MGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_NETTO_MGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_NETTO_MGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_NETTO_MGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_NETTO_MGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_NETTO_MGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_NETTO_MGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RESULT_NETTO_MGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RESULT_NETTO_MGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_MGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_MGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_VORAUSZAHLUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_VORAUSZAHLUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_VORAUSZAHLUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_VORAUSZAHLUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_VORAUSZAHLUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_VORAUSZAHLUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_VORAUSZAHLUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_VORAUSZAHLUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_VORAUSZAHLUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_VORAUSZAHLUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_VORAUSZAHLUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_VORAUSZAHLUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_WB_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_WB_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_WB_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_WB_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_WB_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_WB_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNGSTATION_LOGI_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNGSTATION_LOGI_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_VEKTOR_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR_POS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTZWANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKTZWANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTZWANG_AUS_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKTZWANG_AUS_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTZWANG_AUS_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKTZWANG_AUS_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTZWANG_AUS_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKTZWANG_AUS_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG_AUS_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG_AUS_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEISTUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERINFO_TPA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERINFO_TPA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MANUELL_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_NETTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_NETTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_VERTEILUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_VERTEILUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NOTIFIKATION_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NOTIFIKATION_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORDNUNGSNUMMER_CMR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORDNUNGSNUMMER_CMR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PLANMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLANMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLANMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLANMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSNR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSTENNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISERMITTLUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISERMITTLUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISERMITTLUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISERMITTLUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISERMITTLUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISERMITTLUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISERMITTLUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISERMITTLUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISERMITTLUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISERMITTLUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISERMITTLUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISERMITTLUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_MENGE_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_MENGE_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_MENGE_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_MENGE_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_PREIS_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_PREIS_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_PREIS_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_PREIS_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SETZKASTEN_KOMPLETT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SETZKASTEN_KOMPLETT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SETZKASTEN_KOMPLETT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SETZKASTEN_KOMPLETT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SETZKASTEN_KOMPLETT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SETZKASTEN_KOMPLETT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SETZKASTEN_KOMPLETT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SETZKASTEN_KOMPLETT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SETZKASTEN_KOMPLETT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SETZKASTEN_KOMPLETT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SETZKASTEN_KOMPLETT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SETZKASTEN_KOMPLETT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", calNewValueFormated);
	}
	}
