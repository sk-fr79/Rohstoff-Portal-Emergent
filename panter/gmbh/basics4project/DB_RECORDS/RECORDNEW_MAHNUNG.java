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

public class RECORDNEW_MAHNUNG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_MAHNUNG";
    private _TAB  tab = _TAB.mahnung;  


	public RECORDNEW_MAHNUNG() throws myException 
	{
		super("JT_MAHNUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAHNUNG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MAHNUNG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_MAHNUNG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAHNUNG.TABLENAME);
	}
	
	
	
	public RECORDNEW_MAHNUNG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_MAHNUNG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAHNUNG.TABLENAME);
	}

	
	
	public RECORD_MAHNUNG do_WRITE_NEW_MAHNUNG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MAHNUNG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MAHNUNG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MAHNUNG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_FRIST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_FRIST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_FRIST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_FRIST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_FRIST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_FRIST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_FRIST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL1_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL1_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL2_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL2_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL3_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL3_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL4_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL4_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL5_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL5_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAXNUMMER_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAXNUMMER_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FRIST_IN_TAGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FRIST_IN_TAGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MAHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MAHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER_3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAHNGEBUEHR_BETRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAHNGEBUEHR_BETRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAHNGEBUEHR_PROZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAHNGEBUEHR_PROZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAHNSALDO_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAHNSALDO_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSTUFE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAHNSTUFE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAHNSTUFE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSTUFE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSTUFE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSTUFE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNSTUFE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAHNTEXT_AUSLEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAHNTEXT_EINLEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAHNTEXT_EINLEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", calNewValueFormated);
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
