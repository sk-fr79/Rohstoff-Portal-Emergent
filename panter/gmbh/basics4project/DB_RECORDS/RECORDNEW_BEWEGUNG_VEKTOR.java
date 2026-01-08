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

public class RECORDNEW_BEWEGUNG_VEKTOR extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BEWEGUNG_VEKTOR";
    private _TAB  tab = _TAB.bewegung_vektor;  


	public RECORDNEW_BEWEGUNG_VEKTOR() throws myException 
	{
		super("JT_BEWEGUNG_VEKTOR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_VEKTOR.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BEWEGUNG_VEKTOR(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BEWEGUNG_VEKTOR", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_VEKTOR.TABLENAME);
	}
	
	
	
	public RECORDNEW_BEWEGUNG_VEKTOR(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BEWEGUNG_VEKTOR");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BEWEGUNG_VEKTOR.TABLENAME);
	}

	
	
	public RECORD_BEWEGUNG_VEKTOR do_WRITE_NEW_BEWEGUNG_VEKTOR(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BEWEGUNG_VEKTOR");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BEWEGUNG_VEKTOR(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BEWEGUNG_VEKTOR ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_DATUM_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_DATUM_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_DATUM_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_DATUM_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_DATUM_VON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VEKTOR_GRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VEKTOR_GRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_PRODUKT_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_PRODUKT_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_PRODUKT_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_PRODUKT_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_TRANSPORT_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_TRANSPORT_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_TRANSPORT_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_TRANSPORT_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_DATUM_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_DATUM_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_DATUM_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_DATUM_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_DATUM_VON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATISTIK_TIMESTAMP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATISTIK_TIMESTAMP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_VARIANTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VARIANTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VARIANTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VARIANTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VARIANTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VARIANTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VARIANTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VARIANTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VARIANTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VARIANTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VARIANTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VARIANTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENKLASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WARENKLASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WARENKLASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENKLASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENKLASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENKLASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENKLASSE", calNewValueFormated);
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
