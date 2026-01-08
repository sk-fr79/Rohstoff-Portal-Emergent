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

public class RECORDNEW_BG_ATOM extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BG_ATOM";
    private _TAB  tab = _TAB.bg_atom;  


	public RECORDNEW_BG_ATOM() throws myException 
	{
		super("JT_BG_ATOM");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_ATOM.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BG_ATOM(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BG_ATOM", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_ATOM.TABLENAME);
	}
	
	
	
	public RECORDNEW_BG_ATOM(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BG_ATOM");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_ATOM.TABLENAME);
	}

	
	
	public RECORD_BG_ATOM do_WRITE_NEW_BG_ATOM(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BG_ATOM");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BG_ATOM(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BG_ATOM ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_EXTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_EXTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_EXTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_EXTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_AUSFUEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_AUSFUEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_VERTRAG_CHECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_VERTRAG_CHECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_VERTRAG_CHECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_VERTRAG_CHECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_VERTRAG_CHECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_VERTRAG_CHECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_VERTRAG_CHECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_VERTRAG_CHECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_VERTRAG_CHECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_VERTRAG_CHECK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS_BASISWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_BASISWAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS_BASISWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS_BASISWAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_BASISWAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_BASISWAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_BASISWAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_BASISWAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_BASISWAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_BASISWAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_BASISWAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_BASISWAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS_FREMDWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_FREMDWAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS_FREMDWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS_FREMDWAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_FREMDWAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_FREMDWAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_FREMDWAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_FREMDWAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_FREMDWAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_FREMDWAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_FREMDWAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_FREMDWAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_BASIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS_RES_NETTO_MGE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_BASIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_BASIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_BASIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_BASIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_BASIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_BASIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_BASIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_BASIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_BASIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_FREMD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_FREMD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_E_PREIS_RES_NETTO_MGE_FREMD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_FREMD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_FREMD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_FREMD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_FREMD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_FREMD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_FREMD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_FREMD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_E_PREIS_RES_NETTO_MGE_FREMD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("E_PREIS_RES_NETTO_MGE_FREMD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_BASIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_BASIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_G_PREIS_ABZUG_BASIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("G_PREIS_ABZUG_BASIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_BASIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_BASIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_BASIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_BASIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_BASIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_BASIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_BASIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_BASIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_FREMD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_G_PREIS_ABZUG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("G_PREIS_ABZUG_FREMD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_FREMD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_FREMD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_FREMD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_FREMD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_FREMD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_FREMD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_ABZUG_FREMD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_ABZUG_FREMD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_G_PREIS_BASISWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_BASISWAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_G_PREIS_BASISWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("G_PREIS_BASISWAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_BASISWAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_BASISWAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_BASISWAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_BASISWAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_BASISWAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_BASISWAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_BASISWAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_BASISWAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_G_PREIS_FREMDWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_FREMDWAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_G_PREIS_FREMDWAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("G_PREIS_FREMDWAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_FREMDWAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_FREMDWAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_FREMDWAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_FREMDWAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_FREMDWAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_FREMDWAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_G_PREIS_FREMDWAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("G_PREIS_FREMDWAEHRUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_ATOM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPORT_GELANGENSBEST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPORT_GELANGENSBEST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_PRUEFPORT_GELANGENSBEST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_PRUEFPORT_GELANGENSBEST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPORT_GELANGENSBEST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPORT_GELANGENSBEST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPORT_GELANGENSBEST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPORT_GELANGENSBEST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPORT_GELANGENSBEST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPORT_GELANGENSBEST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPORT_GELANGENSBEST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPORT_GELANGENSBEST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_PRUEFPROT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_PRUEFPROT_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_PRUEFPROT_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_PRUEFPROT_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_RECH_BLOCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_RECH_BLOCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_RECH_BLOCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_RECH_BLOCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_RECH_BLOCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_RECH_BLOCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_RECH_BLOCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_RECH_BLOCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_RECH_BLOCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_RECH_BLOCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_RECH_BLOCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_RECH_BLOCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STATION_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_STATION_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STATION_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_STATION_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTRASTAT_MELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTZWANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTRAKTZWANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTZWANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTRAKTZWANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KOSTEN_PRODUKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MANUELL_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABRECH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABRECH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_NETTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_NETTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_VERTEILUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_VERTEILUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NOTIFIKATION_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NOTIFIKATION_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSTENNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_IN_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POS_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POS_IN_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_IN_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_IN_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_IN_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_IN_MASK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSIT_MELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_MELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_MELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSIT_MELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_MELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_MELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_MELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_MELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_MELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_MELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_MELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_MELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNGSKURS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNGSKURS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
	}
