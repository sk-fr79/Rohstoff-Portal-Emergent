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

public class RECORDNEW_MANDANT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_MANDANT";
    private _TAB  tab = _TAB.mandant;  


	public RECORDNEW_MANDANT() throws myException 
	{
		super("JD_MANDANT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MANDANT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_MANDANT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT.TABLENAME);
	}
	
	
	
	public RECORDNEW_MANDANT(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_MANDANT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MANDANT.TABLENAME);
	}

	
	
	public RECORD_MANDANT do_WRITE_NEW_MANDANT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MANDANT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MANDANT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MANDANT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOWED_DATE_DIFF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOWED_DATE_DIFF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOWED_DATE_DIFF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOWED_DATE_DIFF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_ABZUG_IN_FUHREN_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_ABZUG_IN_FUHREN_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EDIT_PRICE_IN_FUHREN_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_EDIT_PRICE_IN_FUHREN_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_GLEICHHEIT_FUHRE_STELLEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_GLEICHHEIT_FUHRE_STELLEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANREDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANREDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANREDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_APPEND_ATTACHMENT_PDF_TO_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("APPEND_ATTACHMENT_PDF_TO_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSSEN_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSSEN_STEUER_VERMERK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSSEN_STEUER_VERMERK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSSEN_STEUER_VERMERK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_BANK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_BANK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BANK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BANK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_BLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_BLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_BLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_EMAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_EMAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_EMAIL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_HANDELSREG_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_HANDELSREG_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_KONTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_REGISTERGERICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_REGISTERGERICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_STEUERNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_STEUERNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_STEUERNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_STEUERNR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_TELEFAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_TELEFAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFAX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_TELEFON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_TELEFON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_TELEFON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_TELEFON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_USTID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_USTID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_USTID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_USTID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGDRUCK_WWW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGDRUCK_WWW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGDRUCK_WWW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGDRUCK_WWW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_ABANGEB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_ABANGEB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_EKK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_EKK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_EKK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_EKK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_GUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_GUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_GUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_GUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_LIEFANGEB(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_LIEFANGEB", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_RECH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_RECH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_RECH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_RECH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_TPA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_TPA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_TPA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_TPA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSPREFIX_VKK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSPREFIX_VKK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSPREFIX_VKK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSPREFIX_VKK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_BACKTEXT_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_BACKTEXT_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_BACKTEXT_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_BACKTEXT_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_BACKTEXT_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_BACKTEXT_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BACKTEXT_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BACKTEXT_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_DIFF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_DIFF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_DIFF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_DIFF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_DIFF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_DIFF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_DIFF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_DIFF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_HIGHLIGHT_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_HIGHLIGHT_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_POPUP_TITEL_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_POPUP_TITEL_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_POPUP_TITEL_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_POPUP_TITEL_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EIGENE_ADRESS_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EIGENE_ADRESS_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_ADRESS_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_ADRESS_ID", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FILENAME_INTRASTAT_IN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FILENAME_INTRASTAT_IN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_IN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_IN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FILENAME_INTRASTAT_OUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FILENAME_INTRASTAT_OUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FILENAME_INTRASTAT_OUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FILENAME_INTRASTAT_OUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKRECHTSOBEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKRECHTSOBEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIRMENBLOCKSEITENFUSS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIRMENBLOCKSEITENFUSS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIRMENBLOCKSEITENFUSS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GRENZE_MENG_DIFF_ABRECH_PROZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRENZE_MENG_DIFF_ABRECH_PROZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HAT_ABZUEGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HAT_ABZUEGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HAT_ABZUEGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_ABZUEGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DUMMY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DUMMY", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KARENZ_ZAHLFRIST_AB_HEUTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KARENZ_ZAHLFRIST_AB_HEUTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KORR_ZAHLDAT_WOCHENENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KORR_ZAHLDAT_WOCHENENDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KURZNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KURZNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KURZNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KURZNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KURZNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LANDKURZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANDKURZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LANDKURZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LANDKURZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANDKURZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANDKURZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANDKURZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LANDKURZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANDKURZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOGROESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LOGOGROESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LOGOGROESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOGROESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOGROESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOGROESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LOGOGROESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOGROESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LOGONAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGONAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LOGONAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LOGONAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LOGONAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGONAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGONAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGONAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGONAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGONAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LOGONAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGONAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LOGOSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LOGOSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LOGOSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LOGOTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LOGOTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LOGOTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LOGOTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILACCOUNT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILACCOUNT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILACCOUNT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILACCOUNT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILACCOUNT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILACCOUNT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILACCOUNT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILACCOUNT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILPASSWORD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILPASSWORD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILPASSWORD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILPASSWORD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILPASSWORD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILPASSWORD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILPASSWORD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILPASSWORD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAILSERVER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILSERVER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILSERVER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILSERVER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILSERVER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILSERVER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILSERVER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILSERVER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILSERVER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILUSERNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAILUSERNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAILUSERNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILUSERNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILUSERNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILUSERNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAILUSERNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAILUSERNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_EK_KONTRAKT_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_EK_KONTRAKT_POS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MG_TOLERANZ_VK_KONTRAKT_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MG_TOLERANZ_VK_KONTRAKT_POS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_EU_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_EU_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_INLAND_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_INLAND_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_DEBITOR_NICHT_EU_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_DEBITOR_NICHT_EU_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_EU_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_EU_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_INLAND_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_INLAND_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_BIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_BIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NUMKREIS_KREDITOR_NICHT_EU_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NUMKREIS_KREDITOR_NICHT_EU_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_ANGEB_NUR_GEDRUCKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_ANGEB_NUR_GEDRUCKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISFIND_KONTR_NUR_GEDRUCKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFIND_KONTR_NUR_GEDRUCKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISFREIGABE_AUTO_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFREIGABE_AUTO_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECHDAT_IST_LEISTUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECHDAT_IST_LEISTUNGSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RUNDEN_ABZUGS_MENGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RUNDEN_ABZUGS_MENGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_BANKNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECKDRUCK_BANKNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BANKNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BANKNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_BLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECKDRUCK_BLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_BLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_BLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_KONTO_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_KONTO_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_KONTO_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECKVERMERK_AUF_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECKVERMERK_AUF_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SHOW_IDS_ON_REPORT_LABELS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_IDS_ON_REPORT_LABELS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SHOW_IDS_ON_REPORT_LABELS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SHOW_IDS_ON_REPORT_LABELS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SHOW_IDS_ON_REPORT_LABELS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_IDS_ON_REPORT_LABELS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SHOW_IDS_ON_REPORT_LABELS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_IDS_ON_REPORT_LABELS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SHOW_IDS_ON_REPORT_LABELS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_IDS_ON_REPORT_LABELS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SHOW_IDS_ON_REPORT_LABELS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SHOW_IDS_ON_REPORT_LABELS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERFINDUNG_OHNE_EIGENORTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERFINDUNG_OHNE_EIGENORTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STICHTAG_START_FIBU(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STICHTAG_START_FIBU", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STICHTAG_START_FIBU(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STICHTAG_START_FIBU", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITELUEBERANSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITELUEBERANSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITELUEBERANSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITELUEBERANSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_INTRASTAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR_INTRASTAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERMERK_STEUERFR_DIENSTLEIST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERMERK_STEUERFR_DIENSTLEIST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_FONTNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WASSERZEICHEN_FONTNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_FONTSIZE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_FONTSIZE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_FONTSIZE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_ROTATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WASSERZEICHEN_ROTATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_ROTATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_ROTATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WASSERZEICHEN_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WASSERZEICHEN_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WASSERZEICHEN_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WASSERZEICHEN_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_FAHRPLANERFASSUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_FAHRPLANERFASSUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_GLOBAL_REPORTS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_GLOBAL_REPORTS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_KALENDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIGE_MODUL_KALENDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_KALENDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_KALENDER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_MESSAGES(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_MESSAGES(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_MESSAGES", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_TODOS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIGE_MODUL_TODOS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_TODOS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_TODOS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIGE_MODUL_WORKFLOW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIGE_MODUL_WORKFLOW", calNewValueFormated);
	}
	}
