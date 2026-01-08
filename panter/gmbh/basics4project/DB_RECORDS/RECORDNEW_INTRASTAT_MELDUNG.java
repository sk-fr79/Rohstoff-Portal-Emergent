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

public class RECORDNEW_INTRASTAT_MELDUNG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_INTRASTAT_MELDUNG";
    private _TAB  tab = _TAB.intrastat_meldung;  


	public RECORDNEW_INTRASTAT_MELDUNG() throws myException 
	{
		super("JT_INTRASTAT_MELDUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_INTRASTAT_MELDUNG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_INTRASTAT_MELDUNG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_INTRASTAT_MELDUNG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_INTRASTAT_MELDUNG.TABLENAME);
	}
	
	
	
	public RECORDNEW_INTRASTAT_MELDUNG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_INTRASTAT_MELDUNG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_INTRASTAT_MELDUNG.TABLENAME);
	}

	
	
	public RECORD_INTRASTAT_MELDUNG do_WRITE_NEW_INTRASTAT_MELDUNG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_INTRASTAT_MELDUNG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_INTRASTAT_MELDUNG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table INTRASTAT_MELDUNG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEFORM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANMELDEFORM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANMELDEFORM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEFORM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEFORM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEFORM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEFORM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEJAHR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANMELDEJAHR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANMELDEJAHR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEJAHR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEJAHR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEJAHR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEJAHR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEMONAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANMELDEMONAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANMELDEMONAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEMONAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEMONAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEMONAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANMELDEMONAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_LAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTIMM_LAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTIMM_LAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_LAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_LAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_LAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_LAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_REGION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTIMM_REGION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTIMM_REGION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_REGION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_REGION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_REGION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTIMM_REGION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSJAHR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZUGSJAHR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZUGSJAHR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSJAHR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSJAHR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSJAHR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSJAHR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSMONAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZUGSMONAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZUGSMONAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSMONAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSMONAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSMONAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZUGSMONAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUNDESLAND_FA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUNDESLAND_FA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENMASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EIGENMASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EIGENMASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENMASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENMASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENMASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENMASSE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EXPORTFREIGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EXPORTFREIGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EXPORTIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EXPORTIERT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EXPORT_NO_PRICE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EXPORT_NO_PRICE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FRACHTKOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FRACHTKOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSART", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHAEFTSART(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHAEFTSART", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSART", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSART", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSART", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSART", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_INTRASTAT_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_INTRASTAT_MELDUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAND_URSPRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAND_URSPRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASSEINHEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MASSEINHEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MASSEINHEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASSEINHEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASSEINHEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASSEINHEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASSEINHEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MELDEART(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDEART", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MELDEART(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MELDEART", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MELDEART(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDEART", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDEART(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDEART", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDEART(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDEART", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MELDEART(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDEART", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NICHT_MELDEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NICHT_MELDEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NICHT_MELDEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NICHT_MELDEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NICHT_MELDEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NICHT_MELDEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NICHT_MELDEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PAGINIERNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PAGINIERNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHBETRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECHBETRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECHBETRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHBETRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHBETRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHBETRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHBETRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATISTISCHER_WERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATISTISCHER_WERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERNR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUER_LKZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UMSATZSTEUER_LKZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UNTERSCHEIDUNGSNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UNTERSCHEIDUNGSNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERKEHRSZWEIG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERKEHRSZWEIG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNGSKENNZIFFER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNGSKENNZIFFER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WARENNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WARENNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WARENNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WARENNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WARENNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WARENNR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAEHLER_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAEHLER_MELDUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", calNewValueFormated);
	}
	}
