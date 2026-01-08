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

public class RECORDNEW_ARTIKEL_DATENBLATT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ARTIKEL_DATENBLATT";
    private _TAB  tab = _TAB.artikel_datenblatt;  


	public RECORDNEW_ARTIKEL_DATENBLATT() throws myException 
	{
		super("JT_ARTIKEL_DATENBLATT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKEL_DATENBLATT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ARTIKEL_DATENBLATT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ARTIKEL_DATENBLATT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKEL_DATENBLATT.TABLENAME);
	}
	
	
	
	public RECORDNEW_ARTIKEL_DATENBLATT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ARTIKEL_DATENBLATT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKEL_DATENBLATT.TABLENAME);
	}

	
	
	public RECORD_ARTIKEL_DATENBLATT do_WRITE_NEW_ARTIKEL_DATENBLATT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ARTIKEL_DATENBLATT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ARTIKEL_DATENBLATT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ARTIKEL_DATENBLATT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABMESSUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABMESSUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABMESSUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABMESSUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABMESSUNG3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABMESSUNG3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABMESSUNG3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABMESSUNG3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EU_SORTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_SORTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_SORTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_SORTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_SORTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_SORTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_SORTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_SORTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_SORTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_SORTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_SORTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_SORTE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEGENSTAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENSTAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEGENSTAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEGENSTAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEGENSTAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENSTAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEGENSTAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENSTAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEGENSTAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENSTAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEGENSTAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENSTAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_DATENBLATT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_DATENBLATT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_DATENBLATT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_DATENBLATT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_DATENBLATT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_DATENBLATT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_DATENBLATT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_DATENBLATT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_DATENBLATT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_DATENBLATT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_DATENBLATT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_DATENBLATT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_ABMESSUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_ABMESSUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT_ABMESSUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINHEIT_ABMESSUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_ABMESSUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_ABMESSUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_ABMESSUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_ABMESSUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_ABMESSUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_ABMESSUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_ABMESSUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_ABMESSUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_STAERKE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINHEIT_STAERKE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_STAERKE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_STAERKE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_STAERKE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_STAERKE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_STAERKE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_STAERKE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_STAERKE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_STAERKE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_KONTAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_KONTAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_KONTAKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ISRI_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISRI_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ISRI_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ISRI_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ISRI_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISRI_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISRI_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISRI_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ISRI_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISRI_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ISRI_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ISRI_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KLASSIFIZIERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KLASSIFIZIERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KLASSIFIZIERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KLASSIFIZIERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KLASSIFIZIERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KLASSIFIZIERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KLASSIFIZIERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KLASSIFIZIERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KLASSIFIZIERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KLASSIFIZIERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KLASSIFIZIERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KLASSIFIZIERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEGIERUNG_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEGIERUNG_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEGIERUNG_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEGIERUNG_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEGIERUNG_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEGIERUNG_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEGIERUNG_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEGIERUNG_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEGIERUNG_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEGIERUNG_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEGIERUNG_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEGIERUNG_INFO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_ABMESSUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_ABMESSUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NACHKOMMA_ABMESSUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NACHKOMMA_ABMESSUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_ABMESSUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_ABMESSUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_ABMESSUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_ABMESSUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_ABMESSUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_ABMESSUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_ABMESSUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_ABMESSUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_STAERKE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NACHKOMMA_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NACHKOMMA_STAERKE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_STAERKE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_STAERKE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_STAERKE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_STAERKE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_STAERKE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_STAERKE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NACHKOMMA_STAERKE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHKOMMA_STAERKE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STAERKE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STAERKE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STAERKE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAERKE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STAERKE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STAERKE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STAERKE", calNewValueFormated);
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
