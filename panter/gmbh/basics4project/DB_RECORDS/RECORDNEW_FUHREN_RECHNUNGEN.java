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

public class RECORDNEW_FUHREN_RECHNUNGEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FUHREN_RECHNUNGEN";
    private _TAB  tab = _TAB.fuhren_rechnungen;  


	public RECORDNEW_FUHREN_RECHNUNGEN() throws myException 
	{
		super("JT_FUHREN_RECHNUNGEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_RECHNUNGEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FUHREN_RECHNUNGEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FUHREN_RECHNUNGEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_RECHNUNGEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_FUHREN_RECHNUNGEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FUHREN_RECHNUNGEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_RECHNUNGEN.TABLENAME);
	}

	
	
	public RECORD_FUHREN_RECHNUNGEN do_WRITE_NEW_FUHREN_RECHNUNGEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FUHREN_RECHNUNGEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FUHREN_RECHNUNGEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FUHREN_RECHNUNGEN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGS_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGS_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGS_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGS_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGS_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGS_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGS_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGS_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGS_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGS_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGS_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGS_NR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_STANDORT_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_STANDORT_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_STANDORT_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_STANDORT_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_RECHNUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_RECHNUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FUHREN_RECHNUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FUHREN_RECHNUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_RECHNUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_RECHNUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_RECHNUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_RECHNUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_RECHNUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_RECHNUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_RECHNUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_RECHNUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_GEGENSEITE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_GEGENSEITE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_GEGENSEITE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_GEGENSEITE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_GEGENSEITE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_GEGENSEITE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_GEGENSEITE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_GEGENSEITE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_GEGENSEITE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_GEGENSEITE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_GEGENSEITE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_GEGENSEITE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SONDERLAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SONDERLAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SONDERLAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SONDERLAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SONDERLAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SONDERLAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SONDERLAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SONDERLAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SONDERLAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SONDERLAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SONDERLAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SONDERLAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_DIENSTLEISTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KORREKTUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KORREKTUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KORREKTUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KORREKTUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KORREKTUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KORREKTUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KORREKTUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORREKTUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEISTUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEISTUNGSDATUM_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEISTUNGSDATUM_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_LAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGEN_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGEN_VORZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGEN_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGEN_VORZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGEN_VORZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGEN_VORZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGEN_VORZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGEN_VORZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGEN_VORZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGEN_VORZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGEN_VORZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGEN_VORZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER_ORI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER_ORI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_LAGER_ORI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_LAGER_ORI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER_ORI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER_ORI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER_ORI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER_ORI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER_ORI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER_ORI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGER_ORI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGER_ORI", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_NACH_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG_ORI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG_ORI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_NACH_ABZUG_ORI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_NACH_ABZUG_ORI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG_ORI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG_ORI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG_ORI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG_ORI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG_ORI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG_ORI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG_ORI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG_ORI", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_BUCHUNGS_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_BUCHUNGS_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_BUCHUNGS_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_BUCHUNGS_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_BUCHUNGS_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_BUCHUNGS_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_BUCHUNGS_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_BUCHUNGS_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_BUCHUNGS_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_BUCHUNGS_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_BUCHUNGS_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_BUCHUNGS_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_ID_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_ID_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_ID_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_ID_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_ID_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ARTIKEL_BEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_ID_ARTIKEL_BEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_ID_ARTIKEL_BEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ARTIKEL_BEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_ID_ARTIKEL_BEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ARTIKEL_BEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_ID_ARTIKEL_BEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_ID_ARTIKEL_BEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_ID_ARTIKEL_BEZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_LAGER_VORZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_LAGER_VORZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_LAGER_VORZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_LAGER_VORZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_LAGER_VORZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_LAGER_VORZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_LAGER_VORZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_LAGER_VORZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_LAGER_VORZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_LAGER_VORZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PCT_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PCT_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PCT_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PCT_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PCT_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PCT_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PCT_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PCT_METALL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_METALL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PCT_METALL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PCT_METALL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PCT_METALL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_METALL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PCT_METALL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_METALL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PCT_METALL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_METALL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PCT_METALL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PCT_METALL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG_EINZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG_EINZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_ABZUG_EINZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_ABZUG_EINZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG_EINZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG_EINZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG_EINZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG_EINZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG_EINZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG_EINZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_ABZUG_EINZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_ABZUG_EINZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_EINZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_EINZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_EINZEL_RESULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_EINZEL_RESULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT_ORI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT_ORI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_EINZEL_RESULT_ORI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_EINZEL_RESULT_ORI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT_ORI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT_ORI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT_ORI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT_ORI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT_ORI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT_ORI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_EINZEL_RESULT_ORI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_EINZEL_RESULT_ORI", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_GESAMT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_NACH_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG_ORI(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG_ORI", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_R_PREIS_NACH_ABZUG_ORI(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("R_PREIS_NACH_ABZUG_ORI", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG_ORI(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG_ORI", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG_ORI(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG_ORI", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG_ORI(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG_ORI", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_R_PREIS_NACH_ABZUG_ORI(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("R_PREIS_NACH_ABZUG_ORI", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_BEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_USER_FUHRE_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("USER_FUHRE_BEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_BEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_BEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_BEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_BEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_BEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_BEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_BEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_BEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_ERZEUGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_ERZEUGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_USER_FUHRE_ERZEUGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("USER_FUHRE_ERZEUGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_ERZEUGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_ERZEUGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_ERZEUGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_ERZEUGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_ERZEUGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_ERZEUGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_USER_FUHRE_ERZEUGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USER_FUHRE_ERZEUGER", calNewValueFormated);
	}
	}
