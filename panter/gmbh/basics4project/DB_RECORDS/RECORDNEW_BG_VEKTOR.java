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

public class RECORDNEW_BG_VEKTOR extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BG_VEKTOR";
    private _TAB  tab = _TAB.bg_vektor;  


	public RECORDNEW_BG_VEKTOR() throws myException 
	{
		super("JT_BG_VEKTOR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_VEKTOR.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BG_VEKTOR(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BG_VEKTOR", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_VEKTOR.TABLENAME);
	}
	
	
	
	public RECORDNEW_BG_VEKTOR(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BG_VEKTOR");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_VEKTOR.TABLENAME);
	}

	
	
	public RECORD_BG_VEKTOR do_WRITE_NEW_BG_VEKTOR(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BG_VEKTOR");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BG_VEKTOR(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BG_VEKTOR ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_VOLUMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_PLANUNG_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_PLANUNG_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_PLANUNG_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_PLANUNG_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EK_VK_SORTE_LOCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EK_VK_SORTE_LOCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EN_TRANSPORT_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EN_TRANSPORT_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EN_VEKTOR_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EN_VEKTOR_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EN_VEKTOR_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EN_VEKTOR_STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_FREMDWARE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_FREMDWARE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LOGI_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LOGI_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_ATOM_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_ATOM_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_ATOM_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_ATOM_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR_BASE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_VEKTOR_BASE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR_SUB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_VEKTOR_SUB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_TRANSIT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_TRANSIT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_TRANSIT3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TRANSPORTMITTEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VERARBEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_TRANSPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_TRANSPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRINT_ANHANG7(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRINT_ANHANG7", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTVERANTWORTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORTVERANTWORTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", calNewValueFormated);
	}
	}
