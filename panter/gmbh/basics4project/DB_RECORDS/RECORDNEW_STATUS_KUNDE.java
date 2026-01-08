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

public class RECORDNEW_STATUS_KUNDE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_STATUS_KUNDE";
    private _TAB  tab = _TAB.status_kunde;  


	public RECORDNEW_STATUS_KUNDE() throws myException 
	{
		super("JT_STATUS_KUNDE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_STATUS_KUNDE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_STATUS_KUNDE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_STATUS_KUNDE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_STATUS_KUNDE.TABLENAME);
	}
	
	
	
	public RECORDNEW_STATUS_KUNDE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_STATUS_KUNDE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_STATUS_KUNDE.TABLENAME);
	}

	
	
	public RECORD_STATUS_KUNDE do_WRITE_NEW_STATUS_KUNDE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_STATUS_KUNDE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_STATUS_KUNDE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table STATUS_KUNDE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_KUNDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_STATUS_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_STATUS_KUNDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_KUNDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_KUNDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_KUNDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_KUNDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_KUNDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_KUNDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_STATUS_KUNDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STATUS_KUNDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_FORDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FIBU_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FIBU_FORDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_FORDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_FORDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_FORDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_FORDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_FORDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_FORDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_FORDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_FORDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_VERB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_VERB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FIBU_VERB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FIBU_VERB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_VERB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_VERB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_VERB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_VERB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_VERB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_VERB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FIBU_VERB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FIBU_VERB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_FORDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FREIE_POS_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FREIE_POS_FORDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_FORDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_FORDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_FORDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_FORDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_FORDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_FORDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_FORDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_FORDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_VERB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_VERB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FREIE_POS_VERB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FREIE_POS_VERB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_VERB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_VERB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_VERB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_VERB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_VERB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_VERB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FREIE_POS_VERB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FREIE_POS_VERB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FUHREN_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FUHREN_FORDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG_GEPLANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG_GEPLANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FUHREN_FORDERUNG_GEPLANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FUHREN_FORDERUNG_GEPLANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG_GEPLANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG_GEPLANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG_GEPLANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG_GEPLANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG_GEPLANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG_GEPLANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_FORDERUNG_GEPLANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_FORDERUNG_GEPLANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FUHREN_VERB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FUHREN_VERB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB_GEPLANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB_GEPLANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_FUHREN_VERB_GEPLANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_FUHREN_VERB_GEPLANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB_GEPLANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB_GEPLANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB_GEPLANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB_GEPLANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB_GEPLANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB_GEPLANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_FUHREN_VERB_GEPLANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_FUHREN_VERB_GEPLANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_FORDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_GESAMT_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_GESAMT_FORDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_FORDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_FORDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_FORDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_FORDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_FORDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_FORDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_FORDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_FORDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_VERB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_VERB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_GESAMT_VERB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_GESAMT_VERB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_VERB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_VERB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_VERB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_VERB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_VERB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_VERB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_GESAMT_VERB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_GESAMT_VERB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_FORDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_RECHNUNG_FORDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_RECHNUNG_FORDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_FORDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_FORDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_FORDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_FORDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_FORDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_FORDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_FORDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_FORDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_VERB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_VERB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUM_RECHNUNG_VERB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUM_RECHNUNG_VERB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_VERB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_VERB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_VERB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_VERB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_VERB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_VERB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUM_RECHNUNG_VERB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUM_RECHNUNG_VERB", calNewValueFormated);
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
