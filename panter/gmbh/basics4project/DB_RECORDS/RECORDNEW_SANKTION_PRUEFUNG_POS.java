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

public class RECORDNEW_SANKTION_PRUEFUNG_POS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_SANKTION_PRUEFUNG_POS";
    private _TAB  tab = _TAB.sanktion_pruefung_pos;  


	public RECORDNEW_SANKTION_PRUEFUNG_POS() throws myException 
	{
		super("JT_SANKTION_PRUEFUNG_POS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SANKTION_PRUEFUNG_POS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_SANKTION_PRUEFUNG_POS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_SANKTION_PRUEFUNG_POS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SANKTION_PRUEFUNG_POS.TABLENAME);
	}
	
	
	
	public RECORDNEW_SANKTION_PRUEFUNG_POS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_SANKTION_PRUEFUNG_POS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_SANKTION_PRUEFUNG_POS.TABLENAME);
	}

	
	
	public RECORD_SANKTION_PRUEFUNG_POS do_WRITE_NEW_SANKTION_PRUEFUNG_POS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_SANKTION_PRUEFUNG_POS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_SANKTION_PRUEFUNG_POS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table SANKTION_PRUEFUNG_POS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SCHLUESSELWORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SCHLUESSELWORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ADRESSE_SCHLUESSELWORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ADRESSE_SCHLUESSELWORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SCHLUESSELWORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SCHLUESSELWORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SCHLUESSELWORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SCHLUESSELWORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SCHLUESSELWORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SCHLUESSELWORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SCHLUESSELWORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SCHLUESSELWORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SANKTION_PRUEFUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SANKTION_PRUEFUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SANKTION_PRUEFUNG_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SANKTION_PRUEFUNG_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SANKTION_PRUEFUNG_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SANKTION_PRUEFUNG_POS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SANKTION_SCHLUESSELWORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_SCHLUESSELWORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SANKTION_SCHLUESSELWORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SANKTION_SCHLUESSELWORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SANKTION_SCHLUESSELWORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_SCHLUESSELWORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SANKTION_SCHLUESSELWORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_SCHLUESSELWORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SANKTION_SCHLUESSELWORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_SCHLUESSELWORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SANKTION_SCHLUESSELWORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_SCHLUESSELWORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SANKTION_WEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SANKTION_WEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SANKTION_WEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SANKTION_WEG", calNewValueFormated);
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
