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

public class RECORDNEW_VPOS_KON_TRAKT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_KON_TRAKT";
    private _TAB  tab = _TAB.vpos_kon_trakt;  


	public RECORDNEW_VPOS_KON_TRAKT() throws myException 
	{
		super("JT_VPOS_KON_TRAKT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_KON_TRAKT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_KON_TRAKT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_KON_TRAKT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_KON_TRAKT.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_KON_TRAKT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_KON_TRAKT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_KON_TRAKT.TABLENAME);
	}

	
	
	public RECORD_VPOS_KON_TRAKT do_WRITE_NEW_VPOS_KON_TRAKT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_KON_TRAKT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_KON_TRAKT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_KON_TRAKT ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_EXTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXIERUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXIERUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXIERUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXIERUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXIERUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXIERUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXIERUNGSBEDINGUNGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_TRAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_TRAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_TRAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON_TRAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_TRAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_TRAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_TRAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_TRAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_TRAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_TRAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_TRAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_TRAKT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UEBERLIEFER_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UEBERLIEFER_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UEBERLIEFER_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UEBERLIEFER_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UEBERLIEFER_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UEBERLIEFER_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UEBERLIEFER_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UEBERLIEFER_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UEBERLIEFER_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UEBERLIEFER_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UEBERLIEFER_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UEBERLIEFER_OK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERLAENGERTE_FAKT_FRIST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERTE_FAKT_FRIST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERLAENGERTE_FAKT_FRIST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERLAENGERTE_FAKT_FRIST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERTE_FAKT_FRIST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERTE_FAKT_FRIST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERTE_FAKT_FRIST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERTE_FAKT_FRIST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERTE_FAKT_FRIST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERTE_FAKT_FRIST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERTE_FAKT_FRIST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERLAENGERTE_FAKT_FRIST", calNewValueFormated);
	}
	}
