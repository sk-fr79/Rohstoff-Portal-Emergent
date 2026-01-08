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

public class RECORDNEW_VKOPF_RG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VKOPF_RG";
    private _TAB  tab = _TAB.vkopf_rg;  


	public RECORDNEW_VKOPF_RG() throws myException 
	{
		super("JT_VKOPF_RG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VKOPF_RG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VKOPF_RG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VKOPF_RG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VKOPF_RG.TABLENAME);
	}
	
	
	
	public RECORDNEW_VKOPF_RG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VKOPF_RG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VKOPF_RG.TABLENAME);
	}

	
	
	public RECORD_VKOPF_RG do_WRITE_NEW_VKOPF_RG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VKOPF_RG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VKOPF_RG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VKOPF_RG ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BELEG_IST_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_IST_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEG_IST_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEG_IST_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEG_IST_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_IST_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEG_IST_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_IST_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEG_IST_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_IST_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEG_IST_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_IST_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNGEN_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL_AUF_FORMULAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_ANSPRECH_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_BEARBEITER_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_SACHBEARB_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUELTIG_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUELTIG_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUELTIG_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUELTIG_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUELTIG_VON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_NACHFOLGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_RG_STORNO_NACHFOLGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_NACHFOLGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_NACHFOLGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_NACHFOLGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_NACHFOLGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_VORGAENGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_VORGAENGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_RG_STORNO_VORGAENGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_RG_STORNO_VORGAENGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_VORGAENGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_VORGAENGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_VORGAENGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_VORGAENGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_VORGAENGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_VORGAENGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_STORNO_VORGAENGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG_STORNO_VORGAENGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IS_POB_ACTIVE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IS_POB_ACTIVE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KDNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KDNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KDNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KDNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KDNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KDNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KDNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KDNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KDNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KDNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KDNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KDNR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LETZTER_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LETZTER_DRUCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERADRESSE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERADRESSE_AKTIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_VORNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_VORNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_VORNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_VORNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_VORNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_VORNAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_ANSPRECH_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_BEARBEITER_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_SACHBEARB_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ_POB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLZ_POB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLZ_POB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ_POB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ_POB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ_POB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ_POB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRACHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SPRACHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SPRACHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRACHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPRACHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRACHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPRACHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRACHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SPRACHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRACHE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEILZAHLUNG_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEILZAHLUNG_PROZENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TELEFAX_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TELEFAX_AUF_FORMULAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TELEFON_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TELEFON_AUF_FORMULAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_ANSPRECH_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_BEARBEITER_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_SACHBEARB_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT_AUSLANDSVERTRETUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_AUSLANDSVERTRETUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT_AUSLANDSVERTRETUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT_AUSLANDSVERTRETUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_AUSLANDSVERTRETUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_AUSLANDSVERTRETUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_AUSLANDSVERTRETUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_AUSLANDSVERTRETUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_AUSLANDSVERTRETUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_AUSLANDSVERTRETUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_AUSLANDSVERTRETUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_AUSLANDSVERTRETUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERID_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UMSATZSTEUERID_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID_MANDANT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERLKZ_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UMSATZSTEUERLKZ_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORGANGSGRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORGANGSGRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORGANG_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORGANG_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_BASIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_BASIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNG_BASIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNG_BASIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_BASIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_BASIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_BASIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_BASIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_BASIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_BASIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_BASIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_BASIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNG_FREMD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
	}
