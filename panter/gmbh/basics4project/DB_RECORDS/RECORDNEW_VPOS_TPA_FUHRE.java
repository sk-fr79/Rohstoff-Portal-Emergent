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

public class RECORDNEW_VPOS_TPA_FUHRE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE";
    private _TAB  tab = _TAB.vpos_tpa_fuhre;  


	public RECORDNEW_VPOS_TPA_FUHRE() throws myException 
	{
		super("JT_VPOS_TPA_FUHRE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_TPA_FUHRE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_TPA_FUHRE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_TPA_FUHRE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_TPA_FUHRE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE.TABLENAME);
	}

	
	
	public RECORD_VPOS_TPA_FUHRE do_WRITE_NEW_VPOS_TPA_FUHRE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_TPA_FUHRE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_TPA_FUHRE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_TPA_FUHRE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLADEMENGE_RECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLADEMENGE_RECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLADEN_BRUTTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_TARA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABLADEN_TARA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABLADEN_TARA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_TARA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_TARA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_TARA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABLADEN_TARA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR1_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR1_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR2_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR2_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR2_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR2_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR2_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR2_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANRUFDATUM_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANRUFDATUM_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANRUFER_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANRUFER_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_LADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_PLANMENGE_ABN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_CONTAINER_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZAHL_CONTAINER_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUFLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUFLADEN_BRUTTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUFLADEN_TARA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUFLADEN_TARA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_HAUSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_LAENDERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_LAENDERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_LAENDERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_LAENDERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_LAENDERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_LAENDERCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_ORTZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_A_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_A_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("A_STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("A_STRASSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_START_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_START_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_ZIEL_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_ZIEL_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTELLNUMMER_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTELLNUMMER_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNR_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSNR_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABHOLUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ABHOLUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABHOLUNG_ENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABLADEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ABLADEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANLIEFERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ANLIEFERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_AUFLADEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_AUFLADEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAT_FAHRPLAN_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAT_FAHRPLAN_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAT_VORGEMERKT_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EAN_CODE_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EAN_CODE_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINHEIT_MENGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINHEIT_MENGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EK_VK_SORTE_LOCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EK_VK_SORTE_LOCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERFASSER_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERFASSER_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_STEUER_VERMERK_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_STEUER_VERMERK_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRER_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRER_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRPLANGRUPPE_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRPLANGRUPPE_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ANFANG_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRT_ANFANG_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ENDE_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRT_ENDE_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_ABNEHMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAX_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAX_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FUHRENGRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FUHRENGRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FUHRE_AUS_FAHRPLAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FUHRE_IST_UMGELEITET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FUHRE_IST_UMGELEITET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FUHRE_KOMPLETT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FUHRE_KOMPLETT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINERTYP_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINERTYP_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_ANH_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASCHINEN_ANH_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_LKW_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASCHINEN_LKW_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VERARBEITUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_STD_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_STD_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_ABN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE_ABN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD_IN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTRASTAT_MELD_IN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD_OUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTRASTAT_MELD_OUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_GEPLANT_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_GEPLANT_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STORNIERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_STORNIERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_STORNIERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STORNIERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STORNIERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STORNIERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_STORNIERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KENNER_ALTE_SAETZE_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LADEMENGE_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERINFO_TPA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERINFO_TPA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_PLZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("L_STRASSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MANUELL_PREIS_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MANUELL_PREIS_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABLADEN_KO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABLADEN_KO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_AUFLADEN_KO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_AUFLADEN_KO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_VORGABE_KO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_VORGABE_KO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NOTIFIKATION_NR_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NOTIFIKATION_NR_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN_ABN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ORDNUNGSNUMMER_CMR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ORDNUNGSNUMMER_CMR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSTENNUMMER_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSTENNUMMER_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_EK_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_EK_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_LADEMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_LADEMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_VK_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_VK_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ROUTING_DISTANCE_KM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ROUTING_DISTANCE_KM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ROUTING_TIME_MINUTES(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ROUTING_TIME_MINUTES", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHLIESSE_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHLIESSE_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHLIESSE_FUHRE_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHLIESSE_FUHRE_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHLIESSE_FUHRE_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHLIESSE_FUHRE_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTIERUNG_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTIERUNG_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS_BUCHUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS_BUCHUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNO_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNO_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNO_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNO_KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT_FP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TAETIGKEIT_FP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_ABNEHMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEL_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEL_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TP_VERANTWORTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TP_VERANTWORTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_EK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSIT_EK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_VK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSIT_VK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER_LADEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITANGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEITANGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEITANGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITANGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITANGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITANGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITANGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", calNewValueFormated);
	}
	}
