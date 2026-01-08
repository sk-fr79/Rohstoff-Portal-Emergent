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

public class RECORDNEW_VPOS_EXPORT_RG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_EXPORT_RG";
    private _TAB  tab = _TAB.vpos_export_rg;  


	public RECORDNEW_VPOS_EXPORT_RG() throws myException 
	{
		super("JT_VPOS_EXPORT_RG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_EXPORT_RG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_EXPORT_RG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_EXPORT_RG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_EXPORT_RG.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_EXPORT_RG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_EXPORT_RG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_EXPORT_RG.TABLENAME);
	}

	
	
	public RECORD_VPOS_EXPORT_RG do_WRITE_NEW_VPOS_EXPORT_RG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_EXPORT_RG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_EXPORT_RG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_EXPORT_RG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEGENKONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENKONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEGENKONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEGENKONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEGENKONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENKONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEGENKONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENKONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEGENKONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENKONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEGENKONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEGENKONTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EXPORT_LOG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EXPORT_LOG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EXPORT_LOG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EXPORT_LOG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EXPORT_LOG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EXPORT_LOG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EXPORT_LOG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EXPORT_LOG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EXPORT_LOG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EXPORT_LOG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EXPORT_LOG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EXPORT_LOG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTENREGEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU_KONTENREGEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTENREGEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTENREGEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_EXPORT_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_EXPORT_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_EXPORT_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_EXPORT_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_EXPORT_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_EXPORT_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_EXPORT_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_EXPORT_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_EXPORT_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_EXPORT_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_EXPORT_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_EXPORT_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_PARENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_PARENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_RG_PARENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_RG_PARENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_PARENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_PARENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_PARENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_PARENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_PARENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_PARENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_PARENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_PARENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAGER_VORZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PRIO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRIO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRIO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRIO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRIO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUMME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUMME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUMME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUMME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUMME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUMME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUMME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUMME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNG", calNewValueFormated);
	}
	}
