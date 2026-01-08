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

public class RECORDNEW_ADRESSE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ADRESSE";
    private _TAB  tab = _TAB.adresse;  


	public RECORDNEW_ADRESSE() throws myException 
	{
		super("JT_ADRESSE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ADRESSE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ADRESSE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE.TABLENAME);
	}
	
	
	
	public RECORDNEW_ADRESSE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ADRESSE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE.TABLENAME);
	}

	
	
	public RECORD_ADRESSE do_WRITE_NEW_ADRESSE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ADRESSE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ADRESSE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ADRESSE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABN_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABN_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABN_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABN_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABN_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABN_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABN_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABN_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABN_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABN_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABN_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABN_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ADRESSTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ADRESSTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_QUELLE_SICHER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_QUELLE_SICHER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_ZIEL_SICHER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_ZIEL_SICHER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSWEIS_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSWEIS_NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BARKUNDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BARKUNDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BARKUNDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BARKUNDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BARKUNDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BARKUNDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BARKUNDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BARKUNDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FAHRPLAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_FAHRPLAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTKONTAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERSTKONTAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERSTKONTAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTKONTAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTKONTAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTKONTAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERSTKONTAKT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_ANSPRECH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_EMAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_FAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_FAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_INFOFELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_INFOFELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_TEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_TEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBURTSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEBURTSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEBURTSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBURTSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBURTSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBURTSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBURTSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUTSCHRIFTEN_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL5(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL5", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_POTENTIAL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_POTENTIAL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ANREDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ANREDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ANREDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ANREDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ANREDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ANREDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ANREDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ERSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_ERSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_LAGER_HAENDLER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_LAGER_HAENDLER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_LAGER_SACHBEARB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAGER_KONTROLLINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAGER_KONTROLLINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LATITUDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LATITUDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LATITUDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LATITUDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEF_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LONGITUDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LONGITUDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LONGITUDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECHNUNGEN_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECHNUNGEN_SPERREN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECHNUNG_PER_FAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECHNUNG_PER_FAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERLAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SONDERLAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SONDERLAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERLAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERLAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERLAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERLAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TRANSFER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSFER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSFER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSFER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSFER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSFER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSFER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSFER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSFER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSFER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSFER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSFER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WARENAUSGANG_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WARENAUSGANG_SPERREN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WARENEINGANG_SPERREN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WARENEINGANG_SPERREN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", calNewValueFormated);
	}
	}
