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

public class RECORDNEW_ADRESSE_INFO extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ADRESSE_INFO";
    private _TAB  tab = _TAB.adresse_info;  


	public RECORDNEW_ADRESSE_INFO() throws myException 
	{
		super("JT_ADRESSE_INFO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_INFO.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ADRESSE_INFO(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ADRESSE_INFO", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_INFO.TABLENAME);
	}
	
	
	
	public RECORDNEW_ADRESSE_INFO(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ADRESSE_INFO");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ADRESSE_INFO.TABLENAME);
	}

	
	
	public RECORD_ADRESSE_INFO do_WRITE_NEW_ADRESSE_INFO(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ADRESSE_INFO");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ADRESSE_INFO(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ADRESSE_INFO ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_DATUMEINTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEINTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUMEINTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUMEINTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUMEINTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEINTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMEINTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEINTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMEINTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEINTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUMEINTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEINTRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUMEREIGNIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEREIGNIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUMEREIGNIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUMEREIGNIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUMEREIGNIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEREIGNIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMEREIGNIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEREIGNIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMEREIGNIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEREIGNIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUMEREIGNIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUMEREIGNIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FOLGEDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FOLGEDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FOLGEDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FOLGEDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FOLGEDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FOLGEDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FOLGEDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FOLGEDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FOLGEDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FOLGEDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FOLGEDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FOLGEDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_AKTIONSANLASS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AKTIONSANLASS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_AKTIONSANLASS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_AKTIONSANLASS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_AKTIONSANLASS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AKTIONSANLASS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AKTIONSANLASS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AKTIONSANLASS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AKTIONSANLASS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AKTIONSANLASS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_AKTIONSANLASS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AKTIONSANLASS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BESUCHSERGEBNIS1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BESUCHSERGEBNIS1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BESUCHSERGEBNIS2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BESUCHSERGEBNIS2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BESUCHSERGEBNIS3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BESUCHSERGEBNIS3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BESUCHSERGEBNIS3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BESUCHSERGEBNIS3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IST_MESSAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_MESSAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_MESSAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_MESSAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_MESSAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_MESSAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_MESSAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_MESSAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_MESSAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_MESSAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_MESSAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_MESSAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MESSAGE_SOFORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_SOFORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MESSAGE_SOFORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MESSAGE_SOFORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_SOFORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_SOFORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_SOFORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_SOFORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_SOFORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_SOFORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_SOFORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_SOFORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MESSAGE_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MESSAGE_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MESSAGE_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGJAEHRLICH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGJAEHRLICH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEDERHOLUNGJAEHRLICH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEDERHOLUNGJAEHRLICH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGJAEHRLICH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGJAEHRLICH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGJAEHRLICH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGJAEHRLICH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGJAEHRLICH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGJAEHRLICH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGJAEHRLICH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGJAEHRLICH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGMONATLICH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGMONATLICH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEDERHOLUNGMONATLICH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEDERHOLUNGMONATLICH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGMONATLICH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGMONATLICH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGMONATLICH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGMONATLICH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGMONATLICH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGMONATLICH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEDERHOLUNGMONATLICH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEDERHOLUNGMONATLICH", calNewValueFormated);
	}
	}
