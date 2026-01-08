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

public class RECORDNEW_FBAM extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FBAM";
    private _TAB  tab = _TAB.fbam;  


	public RECORDNEW_FBAM() throws myException 
	{
		super("JT_FBAM");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FBAM.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FBAM(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FBAM", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FBAM.TABLENAME);
	}
	
	
	
	public RECORDNEW_FBAM(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FBAM");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FBAM.TABLENAME);
	}

	
	
	public RECORD_FBAM do_WRITE_NEW_FBAM(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FBAM");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FBAM(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FBAM ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSWIRKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSWIRKUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BAM_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BAM_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BAM_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BAM_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BAM_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BAM_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BAM_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEARBEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEARBEITUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEARBEITUNG_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEHEB_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEHEB_VERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEHEB_VORSCHLAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEHEB_VORSCHLAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_BAM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETREFF_BAM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETREFF_BAM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_BAM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_BAM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_BAM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF_BAM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_AUSSTELLUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_BEHEBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_BEHEBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_KONTROLLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_KONTROLLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FEHLERBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FEHLERBESCHREIBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FEHLERINFO_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FEHLERINFO_EXTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERURSACHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FEHLERURSACHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FEHLERURSACHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERURSACHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERURSACHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERURSACHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FEHLERURSACHE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FESTSTELLUNG_BAM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FESTSTELLUNG_BAM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FBAM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FBAM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FBAM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FBAM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FBAM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FBAM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FBAM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FBAM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_AUSSTELLUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BEARBEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEHEBUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BEHEBUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_KONTROLLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_KONTROLLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ABHOLDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ABHOLDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABNEHMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ABNEHMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABNEHMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABNEHMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABNEHMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ABNEHMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ANLIEFERDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ANLIEFERDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ARTBEZ1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_FAXNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_FAXNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_GESPERRT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_GESPERRT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_GESPERRT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_GESPERRT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_GESPERRT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_GESPERRT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_GESPERRT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_LETZTERDRUCK_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_MENGE_ABLADEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_MENGE_ABLADEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UHRZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_UHRZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_UHRZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UHRZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UHRZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UHRZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_UHRZEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", calNewValueFormated);
	}
	}
