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

public class RECORDNEW_MITARBEITER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_MITARBEITER";
    private _TAB  tab = _TAB.mitarbeiter;  


	public RECORDNEW_MITARBEITER() throws myException 
	{
		super("JT_MITARBEITER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MITARBEITER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MITARBEITER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_MITARBEITER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MITARBEITER.TABLENAME);
	}
	
	
	
	public RECORDNEW_MITARBEITER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_MITARBEITER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MITARBEITER.TABLENAME);
	}

	
	
	public RECORD_MITARBEITER do_WRITE_NEW_MITARBEITER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MITARBEITER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MITARBEITER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MITARBEITER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_ABNAHMEANGEBOT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_ABNAHMEANGEBOT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_ANGEBOT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_ANGEBOT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_EK_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_EK_KONTRAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_FIBU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_FIBU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_FIBU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_FIBU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_FIBU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_FIBU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_FIBU", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_TRANSPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_TRANSPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ASP_VK_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ASP_VK_KONTRAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_KALENDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHENK_KALENDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_SEKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHENK_SEKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_SPARGEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHENK_SPARGEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_WEIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHENK_WEIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MITARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_MITARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MITARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MITARBEITERTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MITARBEITERTYP2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MITARBEITERTYP3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MITARBEITERTYP4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_ANSPRECHPARTNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SOMMERGESCHENK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SOMMERGESCHENK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WEIHNACHTSGESCHENK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WEIHNACHTSGESCHENK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", calNewValueFormated);
	}
	}
