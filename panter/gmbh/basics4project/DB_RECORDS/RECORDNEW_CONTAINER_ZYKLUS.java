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

public class RECORDNEW_CONTAINER_ZYKLUS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_CONTAINER_ZYKLUS";
    private _TAB  tab = _TAB.container_zyklus;  


	public RECORDNEW_CONTAINER_ZYKLUS() throws myException 
	{
		super("JT_CONTAINER_ZYKLUS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINER_ZYKLUS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_CONTAINER_ZYKLUS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_CONTAINER_ZYKLUS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINER_ZYKLUS.TABLENAME);
	}
	
	
	
	public RECORDNEW_CONTAINER_ZYKLUS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_CONTAINER_ZYKLUS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_CONTAINER_ZYKLUS.TABLENAME);
	}

	
	
	public RECORD_CONTAINER_ZYKLUS do_WRITE_NEW_CONTAINER_ZYKLUS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_CONTAINER_ZYKLUS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_CONTAINER_ZYKLUS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table CONTAINER_ZYKLUS ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ZEIT_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_LAST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG_LAST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ZEIT_BUCHUNG_LAST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ZEIT_BUCHUNG_LAST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_LAST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG_LAST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_LAST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG_LAST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_LAST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG_LAST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_LAST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG_LAST", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_ZYKLUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINER_ZYKLUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_HAENDLER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_HAENDLER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_HAENDLER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_HAENDLER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_HAENDLER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_HAENDLER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_HAENDLER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_HAENDLER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_HAENDLER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_HAENDLER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_HAENDLER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_HAENDLER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_LETZTE_POS_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LETZTE_POS_BUCHUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_LETZTE_POS_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_LETZTE_POS_BUCHUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LETZTE_POS_BUCHUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LETZTE_POS_BUCHUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LETZTE_POS_BUCHUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LETZTE_POS_BUCHUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LETZTE_POS_BUCHUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LETZTE_POS_BUCHUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LETZTE_POS_BUCHUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_LETZTE_POS_BUCHUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_BEGINN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT_BEGINN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NETTOGEWICHT_BEGINN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NETTOGEWICHT_BEGINN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_BEGINN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT_BEGINN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_BEGINN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT_BEGINN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_BEGINN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT_BEGINN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_BEGINN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT_BEGINN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_LETZTE_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_LETZTE_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT_LETZTE_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TAETIGKEIT_LETZTE_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_LETZTE_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_LETZTE_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_LETZTE_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_LETZTE_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_LETZTE_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_LETZTE_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_LETZTE_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_LETZTE_POS", calNewValueFormated);
	}
	}
