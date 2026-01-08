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

public class RECORDNEW_ADRESSE_UST_ID extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ADRESSE_UST_ID";
    private _TAB  tab = _TAB.adresse_ust_id;  


	public RECORDNEW_ADRESSE_UST_ID() throws myException 
	{
		super("JT_ADRESSE_UST_ID");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_UST_ID.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ADRESSE_UST_ID(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ADRESSE_UST_ID", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_UST_ID.TABLENAME);
	}
	
	
	
	public RECORDNEW_ADRESSE_UST_ID(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ADRESSE_UST_ID");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_UST_ID.TABLENAME);
	}

	
	
	public RECORD_ADRESSE_UST_ID do_WRITE_NEW_ADRESSE_UST_ID(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ADRESSE_UST_ID");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ADRESSE_UST_ID(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ADRESSE_UST_ID ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BEGRENZT_AUF_RC_SORTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGRENZT_AUF_RC_SORTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEGRENZT_AUF_RC_SORTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEGRENZT_AUF_RC_SORTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEGRENZT_AUF_RC_SORTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGRENZT_AUF_RC_SORTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEGRENZT_AUF_RC_SORTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGRENZT_AUF_RC_SORTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEGRENZT_AUF_RC_SORTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGRENZT_AUF_RC_SORTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEGRENZT_AUF_RC_SORTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEGRENZT_AUF_RC_SORTEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_UST_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_UST_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_UST_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_UST_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_UST_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_UST_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_UST_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_UST_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_UST_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_UST_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_UST_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_UST_ID", calNewValueFormated);
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
	}
