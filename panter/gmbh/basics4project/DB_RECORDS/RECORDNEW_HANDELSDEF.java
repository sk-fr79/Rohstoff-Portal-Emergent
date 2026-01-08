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

public class RECORDNEW_HANDELSDEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_HANDELSDEF";
    private _TAB  tab = _TAB.handelsdef;  


	public RECORDNEW_HANDELSDEF() throws myException 
	{
		super("JT_HANDELSDEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_HANDELSDEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_HANDELSDEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_HANDELSDEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_HANDELSDEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_HANDELSDEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_HANDELSDEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_HANDELSDEF.TABLENAME);
	}

	
	
	public RECORD_HANDELSDEF do_WRITE_NEW_HANDELSDEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_HANDELSDEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_HANDELSDEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table HANDELSDEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_QUELLE_GEO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_QUELLE_JUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_ZIEL_GEO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAND_ZIEL_JUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TAX_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_FUER_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MELDUNG_FUER_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_QUELLE_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("QUELLE_IST_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_EOW_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_EOW_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_EOW_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_EOW_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_PRODUKT_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_PRODUKT_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_PRODUKT_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_PRODUKT_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_RC_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_RC_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_RC_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_RC_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_MELDUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP_MELDUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP_MELDUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_MELDUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_MELDUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_MELDUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_MELDUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UST_TEILNEHMER_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UST_TEILNEHMER_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UST_TEILNEHMER_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UST_TEILNEHMER_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERSIONSZAEHLER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERSIONSZAEHLER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZIEL_IST_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", calNewValueFormated);
	}
	}
