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

public class RECORDNEW_MAIL_AUS_MASK extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_MAIL_AUS_MASK";
    private _TAB  tab = _TAB.mail_aus_mask;  


	public RECORDNEW_MAIL_AUS_MASK() throws myException 
	{
		super("JT_MAIL_AUS_MASK");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAIL_AUS_MASK.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MAIL_AUS_MASK(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_MAIL_AUS_MASK", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAIL_AUS_MASK.TABLENAME);
	}
	
	
	
	public RECORDNEW_MAIL_AUS_MASK(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_MAIL_AUS_MASK");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAIL_AUS_MASK.TABLENAME);
	}

	
	
	public RECORD_MAIL_AUS_MASK do_WRITE_NEW_MAIL_AUS_MASK(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MAIL_AUS_MASK");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MAIL_AUS_MASK(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MAIL_AUS_MASK ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASISTABELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASISTABELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASISTABELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASISTABELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASISTABELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASISTABELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASISTABELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BETREFF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETREFF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETREFF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETREFF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETREFF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUTTONBESCHRIFTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUTTONBESCHRIFTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUTTONKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUTTONKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUTTONKENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_EDIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERLAUBT_BEI_EDIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_UNDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERLAUBT_BEI_UNDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_VIEW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERLAUBT_BEI_VIEW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GROOVY_BEDINGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GROOVY_BEDINGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAILTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MODULKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MODULKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MODULKENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SIGNATUR_ANHAENGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SIGNATUR_ANHAENGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", calNewValueFormated);
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
	}
