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

public class RECORDNEW_UMA_KONTRAKT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_UMA_KONTRAKT";
    private _TAB  tab = _TAB.uma_kontrakt;  


	public RECORDNEW_UMA_KONTRAKT() throws myException 
	{
		super("JT_UMA_KONTRAKT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_UMA_KONTRAKT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_UMA_KONTRAKT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_UMA_KONTRAKT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_UMA_KONTRAKT.TABLENAME);
	}
	
	
	
	public RECORDNEW_UMA_KONTRAKT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_UMA_KONTRAKT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_UMA_KONTRAKT.TABLENAME);
	}

	
	
	public RECORD_UMA_KONTRAKT do_WRITE_NEW_UMA_KONTRAKT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_UMA_KONTRAKT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_UMA_KONTRAKT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table UMA_KONTRAKT ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_DATUM_VERTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_VERTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_VERTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERTRAG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_AUSGANG_KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_AUSGANG_KANN_WEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_A_BEZ_AUSGANG_KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_A_BEZ_AUSGANG_KANN_WEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_AUSGANG_KANN_WEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_AUSGANG_KANN_WEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_AUSGANG_KANN_WEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_AUSGANG_KANN_WEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_AUSGANG_KANN_WEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_AUSGANG_KANN_WEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_AUSGANG_KANN_WEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_AUSGANG_KANN_WEG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_ZIEL_KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_ZIEL_KANN_WEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_A_BEZ_ZIEL_KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_A_BEZ_ZIEL_KANN_WEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_ZIEL_KANN_WEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_ZIEL_KANN_WEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_ZIEL_KANN_WEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_ZIEL_KANN_WEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_ZIEL_KANN_WEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_ZIEL_KANN_WEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_A_BEZ_ZIEL_KANN_WEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_A_BEZ_ZIEL_KANN_WEG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BETREUER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BETREUER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BETREUER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BETREUER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BETREUER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BETREUER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BETREUER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BETREUER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BETREUER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BETREUER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BETREUER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BETREUER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_AUSGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_AUSGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ARTIKEL_AUSGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ARTIKEL_AUSGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_AUSGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_AUSGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_AUSGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_AUSGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_AUSGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_AUSGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_AUSGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_AUSGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ARTIKEL_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ARTIKEL_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ARTIKEL_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ARTIKEL_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_AUSGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_AUSGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STARTSALDO_MENGE_AUSGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STARTSALDO_MENGE_AUSGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_AUSGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_AUSGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_AUSGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_AUSGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_AUSGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_AUSGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_AUSGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_AUSGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STARTSALDO_MENGE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STARTSALDO_MENGE_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STARTSALDO_MENGE_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTSALDO_MENGE_ZIEL", calNewValueFormated);
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
