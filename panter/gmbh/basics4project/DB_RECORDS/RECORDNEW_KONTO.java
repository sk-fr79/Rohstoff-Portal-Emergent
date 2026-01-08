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

public class RECORDNEW_KONTO extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_KONTO";
    private _TAB  tab = _TAB.konto;  


	public RECORDNEW_KONTO() throws myException 
	{
		super("JT_KONTO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KONTO.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_KONTO(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_KONTO", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KONTO.TABLENAME);
	}
	
	
	
	public RECORDNEW_KONTO(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_KONTO");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_KONTO.TABLENAME);
	}

	
	
	public RECORD_KONTO do_WRITE_NEW_KONTO(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_KONTO");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_KONTO(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table KONTO ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BIC_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BIC_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BIC_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BIC_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BIC_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BIC_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BIC_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BIC_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BIC_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BIC_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BIC_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BIC_NR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IBAN_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IBAN_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IBAN_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IBAN_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IBAN_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IBAN_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IBAN_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IBAN_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IBAN_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IBAN_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IBAN_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IBAN_NR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BANKENSTAMM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BANKENSTAMM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BANKENSTAMM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BANKENSTAMM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BANKENSTAMM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BANKENSTAMM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BANKENSTAMM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BANKENSTAMM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BANKENSTAMM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BANKENSTAMM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BANKENSTAMM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BANKENSTAMM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_KONTO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KK_LAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_LAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KK_LAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KK_LAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KK_LAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_LAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_LAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_LAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_LAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_LAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KK_LAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_LAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KK_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KK_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KK_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KK_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KK_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KK_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KK_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KK_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KK_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KK_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KK_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KK_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KK_PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KK_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KK_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KK_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KK_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KK_STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KK_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KK_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KK_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KK_STRASSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTONUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTONUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTONUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTONUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTONUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTONUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTONUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTONUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTONUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTONUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTONUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTONUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONTOTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTOTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONTOTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONTOTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONTOTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTOTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTOTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTOTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTOTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTOTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONTOTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONTOTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFMM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFMM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITKARTENABLAUFMM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITKARTENABLAUFMM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFMM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFMM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFMM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFMM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFMM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFMM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFMM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFMM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFYYYY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFYYYY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITKARTENABLAUFYYYY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITKARTENABLAUFYYYY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFYYYY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFYYYY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFYYYY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFYYYY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFYYYY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFYYYY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENABLAUFYYYY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENABLAUFYYYY", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITKARTENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITKARTENNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITKARTENTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITKARTENTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENVV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENVV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KREDITKARTENVV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KREDITKARTENVV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENVV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENVV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENVV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENVV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENVV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENVV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KREDITKARTENVV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KREDITKARTENVV", calNewValueFormated);
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
	}
