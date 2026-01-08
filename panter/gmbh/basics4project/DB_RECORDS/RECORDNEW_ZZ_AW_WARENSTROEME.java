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

public class RECORDNEW_ZZ_AW_WARENSTROEME extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ZZ_AW_WARENSTROEME";
    private _TAB  tab = _TAB.zz_aw_warenstroeme;  


	public RECORDNEW_ZZ_AW_WARENSTROEME() throws myException 
	{
		super("JT_ZZ_AW_WARENSTROEME");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZZ_AW_WARENSTROEME.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ZZ_AW_WARENSTROEME(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ZZ_AW_WARENSTROEME", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZZ_AW_WARENSTROEME.TABLENAME);
	}
	
	
	
	public RECORDNEW_ZZ_AW_WARENSTROEME(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ZZ_AW_WARENSTROEME");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ZZ_AW_WARENSTROEME.TABLENAME);
	}

	
	
	public RECORD_ZZ_AW_WARENSTROEME do_WRITE_NEW_ZZ_AW_WARENSTROEME(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ZZ_AW_WARENSTROEME");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ZZ_AW_WARENSTROEME(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ZZ_AW_WARENSTROEME ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BENUTZERKUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BENUTZERKUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_BRUTTO_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_BRUTTO_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_BRUTTO_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_BRUTTO_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_NETTO_MARGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_NETTO_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_NETTO_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_MGE_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_MGE_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_MGE_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_ABZ_MGE_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_NETTO_MARGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_NETTO_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GPREIS_NETTO_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_FUHRE_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_FUHRE_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_LAGERAUSGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_LAGEREINGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_NETTO_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_NETTO_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_WA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_WA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_WE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_RG_POS_WE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REPORTNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REPORTNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNUMMER", calNewValueFormated);
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
