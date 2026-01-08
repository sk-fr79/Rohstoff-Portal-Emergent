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

public class RECORDNEW_VPOS_TPA_FUHRE_ORT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE_ORT";
    private _TAB  tab = _TAB.vpos_tpa_fuhre_ort;  


	public RECORDNEW_VPOS_TPA_FUHRE_ORT() throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_ORT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_TPA_FUHRE_ORT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_ORT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_TPA_FUHRE_ORT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_TPA_FUHRE_ORT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}

	
	
	public RECORD_VPOS_TPA_FUHRE_ORT do_WRITE_NEW_VPOS_TPA_FUHRE_ORT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_TPA_FUHRE_ORT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_TPA_FUHRE_ORT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_TPA_FUHRE_ORT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLADEMENGE_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLADEMENGE_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_ABLADEMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_LADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_LADEMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_PLANMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_PLANMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_LADE_ABLADE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_LADE_ABLADE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEF_QUELLE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEF_QUELLE_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINHEIT_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINHEIT_MENGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTRASTAT_MELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LADEMENGE_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBED_ALTERNATIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBED_ALTERNATIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", calNewValueFormated);
	}
	}
