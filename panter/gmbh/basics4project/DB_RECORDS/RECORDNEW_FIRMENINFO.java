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

public class RECORDNEW_FIRMENINFO extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FIRMENINFO";
    private _TAB  tab = _TAB.firmeninfo;  


	public RECORDNEW_FIRMENINFO() throws myException 
	{
		super("JT_FIRMENINFO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIRMENINFO.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FIRMENINFO(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FIRMENINFO", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIRMENINFO.TABLENAME);
	}
	
	
	
	public RECORDNEW_FIRMENINFO(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FIRMENINFO");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIRMENINFO.TABLENAME);
	}

	
	
	public RECORD_FIRMENINFO do_WRITE_NEW_FIRMENINFO(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FIRMENINFO");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FIRMENINFO(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FIRMENINFO ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AKONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKONTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AQUISITIONSKOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AQUISITIONSKOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESUCHSRYTHMUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESUCHSRYTHMUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETRIEBSNUMMER_SAA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETRIEBSNUMMER_SAA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEBITOR_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEBITOR_NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEBITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEBITOR_NUMMER_ALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DOKUMENTKOPIEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DOKUMENTKOPIEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FBAM_NUR_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FBAM_NUR_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIRMA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIRMA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIRMA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIRMA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIRMA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIRMA_OHNE_USTID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIRMA_OHNE_USTID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FORDERUNGSVERRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FORDERUNGSVERRECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GRUENDUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GRUENDUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDELSREGISTER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HANDELSREGISTER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HANDELSREGISTER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDELSREGISTER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDELSREGISTER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDELSREGISTER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HANDELSREGISTER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BRANCHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BRANCHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BRANCHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BRANCHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BRANCHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BRANCHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BRANCHE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_BRANCHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_BRANCHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIRMENINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIRMENINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_RECHTSFORM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_RECHTSFORM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSMEDIUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INNERGEMEIN_LIEF_EU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INNERGEMEIN_LIEF_EU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEINE_MAHNUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEINE_MAHNUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITBETRAG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITBETRAG_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT2_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT2_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT2_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT2_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT3_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT3_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT3_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT3_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITLIMIT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITOR_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITOR_NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITOR_NUMMER_ALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITSTAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITSTAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITSTAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITSTAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITSTAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITSTAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITSTAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITVERS_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITVERS_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITVERS_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITVERS_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITVERS_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITVERS_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITVERS_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERMENGE_MAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERMENGE_MAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERMENGE_SCHNITT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERMENGE_SCHNITT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_STEUER_WARENEINGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRIVAT_MIT_USTID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRIVAT_MIT_USTID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_JN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECKDRUCK_JN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMMKAPITAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STAMMKAPITAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STAMMKAPITAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMMKAPITAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMMKAPITAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMMKAPITAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAMMKAPITAL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERNUMMER_STATT_UST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERNUMMER_STATT_UST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UMSATZSTEUERID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERLKZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UMSATZSTEUERLKZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERSICHANFR_DAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERSICHANFR_DAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERSICHANFR_SUMME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERSICHANFR_SUMME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSVERFAHREN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERWERTUNGSVERFAHREN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLANGESTELLTE_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLANGESTELLTE_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHL_ANGESTELLTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHL_ANGESTELLTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", calNewValueFormated);
	}
	}
