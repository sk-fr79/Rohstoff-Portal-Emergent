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

public class RECORDNEW_PROFIL_GRENZUBERTRITT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_PROFIL_GRENZUBERTRITT";
    private _TAB  tab = _TAB.profil_grenzubertritt;  


	public RECORDNEW_PROFIL_GRENZUBERTRITT() throws myException 
	{
		super("JT_PROFIL_GRENZUBERTRITT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROFIL_GRENZUBERTRITT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_PROFIL_GRENZUBERTRITT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_PROFIL_GRENZUBERTRITT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROFIL_GRENZUBERTRITT.TABLENAME);
	}
	
	
	
	public RECORDNEW_PROFIL_GRENZUBERTRITT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_PROFIL_GRENZUBERTRITT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_PROFIL_GRENZUBERTRITT.TABLENAME);
	}

	
	
	public RECORD_PROFIL_GRENZUBERTRITT do_WRITE_NEW_PROFIL_GRENZUBERTRITT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_PROFIL_GRENZUBERTRITT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_PROFIL_GRENZUBERTRITT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table PROFIL_GRENZUBERTRITT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AUSLANDSVERTRETUNG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSLANDSVERTRETUNG_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSLANDSVERTRETUNG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSLANDSVERTRETUNG_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSLANDSVERTRETUNG_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSLANDSVERTRETUNG_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSLANDSVERTRETUNG_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSLANDSVERTRETUNG_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSLANDSVERTRETUNG_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSLANDSVERTRETUNG_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSLANDSVERTRETUNG_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSLANDSVERTRETUNG_TEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECHPARTNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_ANSPRECHPARTNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECHPARTNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECHPARTNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECHPARTNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECHPARTNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECHPARTNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECHPARTNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECHPARTNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECHPARTNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_BEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_PROFIL_GRENZUBERTRITT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFIL_GRENZUBERTRITT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PROFIL_GRENZUBERTRITT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PROFIL_GRENZUBERTRITT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROFIL_GRENZUBERTRITT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFIL_GRENZUBERTRITT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROFIL_GRENZUBERTRITT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFIL_GRENZUBERTRITT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROFIL_GRENZUBERTRITT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFIL_GRENZUBERTRITT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PROFIL_GRENZUBERTRITT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PROFIL_GRENZUBERTRITT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_RECHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_ANSPRECHPARTNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_BEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME_PROFIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_PROFIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_PROFIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_PROFIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_PROFIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_PROFIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_PROFIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_PROFIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_PROFIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_PROFIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_PROFIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_PROFIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECHPARTNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_ANSPRECHPARTNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECHPARTNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECHPARTNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECHPARTNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECHPARTNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECHPARTNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECHPARTNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECHPARTNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECHPARTNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_BEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER", calNewValueFormated);
	}
	}
