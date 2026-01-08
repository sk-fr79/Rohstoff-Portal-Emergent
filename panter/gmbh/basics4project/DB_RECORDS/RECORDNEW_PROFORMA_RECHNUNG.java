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

public class RECORDNEW_PROFORMA_RECHNUNG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_PROFORMA_RECHNUNG";
    private _TAB  tab = _TAB.proforma_rechnung;  


	public RECORDNEW_PROFORMA_RECHNUNG() throws myException 
	{
		super("JT_PROFORMA_RECHNUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROFORMA_RECHNUNG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_PROFORMA_RECHNUNG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_PROFORMA_RECHNUNG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROFORMA_RECHNUNG.TABLENAME);
	}
	
	
	
	public RECORDNEW_PROFORMA_RECHNUNG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_PROFORMA_RECHNUNG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROFORMA_RECHNUNG.TABLENAME);
	}

	
	
	public RECORD_PROFORMA_RECHNUNG do_WRITE_NEW_PROFORMA_RECHNUNG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_PROFORMA_RECHNUNG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_PROFORMA_RECHNUNG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table PROFORMA_RECHNUNG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BELEGDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGNR_PROFORMA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNR_PROFORMA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGNR_PROFORMA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGNR_PROFORMA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGNR_PROFORMA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNR_PROFORMA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGNR_PROFORMA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNR_PROFORMA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGNR_PROFORMA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNR_PROFORMA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGNR_PROFORMA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNR_PROFORMA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_SACHBEARBEITER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FORMULARTEXT_ANFANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FORMULARTEXT_ANFANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FORMULARTEXT_ENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FORMULARTEXT_ENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KAEUFER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_KAEUFER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_KAEUFER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_KAEUFER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KAEUFER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_KAEUFER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KAEUFER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_KAEUFER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KAEUFER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_KAEUFER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KAEUFER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_KAEUFER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_VERTRETER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_VERTRETER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_VERTRETER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_VERTRETER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_VERTRETER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_VERTRETER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_VERTRETER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_VERTRETER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_VERTRETER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_VERTRETER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_VERTRETER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_VERTRETER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_PROFORMA_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFORMA_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PROFORMA_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PROFORMA_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROFORMA_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFORMA_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROFORMA_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFORMA_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROFORMA_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFORMA_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROFORMA_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFORMA_RECHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARBEITER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TELEFON_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TELEFON_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TELEFON_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_SACHBEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORNAME_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORNAME_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORNAME_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME_SACHBEARBEITER", calNewValueFormated);
	}
	}
