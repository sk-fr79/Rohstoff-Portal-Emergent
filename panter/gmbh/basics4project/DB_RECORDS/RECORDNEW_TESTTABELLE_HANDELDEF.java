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

public class RECORDNEW_TESTTABELLE_HANDELDEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TESTTABELLE_HANDELDEF";
    private _TAB  tab = _TAB.testtabelle_handeldef;  


	public RECORDNEW_TESTTABELLE_HANDELDEF() throws myException 
	{
		super("JT_TESTTABELLE_HANDELDEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TESTTABELLE_HANDELDEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TESTTABELLE_HANDELDEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TESTTABELLE_HANDELDEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TESTTABELLE_HANDELDEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_TESTTABELLE_HANDELDEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TESTTABELLE_HANDELDEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TESTTABELLE_HANDELDEF.TABLENAME);
	}

	
	
	public RECORD_TESTTABELLE_HANDELDEF do_WRITE_NEW_TESTTABELLE_HANDELDEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TESTTABELLE_HANDELDEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TESTTABELLE_HANDELDEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TESTTABELLE_HANDELDEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_START_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_START_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_ZIEL_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_ZIEL_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INTRASTAT_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INTRASTAT_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_DIENSTLST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_DIENSTLST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_EOW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_EOW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_EOW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_EOW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_EOW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_EOW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_EOW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_IST_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_LAND_GEO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_LAND_GEO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_LAND_JUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_LAND_JUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_PRODUKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_RC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_RC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_RC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_RC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_RC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_RC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_RC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_RC", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_START_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_START_UNTERNEHMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_TPA_VERANTWORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_TPA_VERANTWORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_DIENSTLST(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_DIENSTLST", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_EOW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_EOW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_IST_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_LAND_GEO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_LAND_GEO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_LAND_JUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_LAND_JUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_PRODUKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_RC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_RC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_START_ALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_START_ALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_START_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_START_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_ZIEL_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ_ZIEL_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_START_ALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK_START_ALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_START_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK_START_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_ZIEL_HD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SUCHERGEBNIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SUCHERGEBNIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", calNewValueFormated);
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
