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

public class RECORDNEW_FUHREN_CO2_PROFIL extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FUHREN_CO2_PROFIL";
    private _TAB  tab = _TAB.fuhren_co2_profil;  


	public RECORDNEW_FUHREN_CO2_PROFIL() throws myException 
	{
		super("JT_FUHREN_CO2_PROFIL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_CO2_PROFIL.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FUHREN_CO2_PROFIL(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FUHREN_CO2_PROFIL", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_CO2_PROFIL.TABLENAME);
	}
	
	
	
	public RECORDNEW_FUHREN_CO2_PROFIL(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FUHREN_CO2_PROFIL");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FUHREN_CO2_PROFIL.TABLENAME);
	}

	
	
	public RECORD_FUHREN_CO2_PROFIL do_WRITE_NEW_FUHREN_CO2_PROFIL(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FUHREN_CO2_PROFIL");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FUHREN_CO2_PROFIL(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FUHREN_CO2_PROFIL ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_CO2_ERZEUGUNG_FUHRE_KG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_ERZEUGUNG_FUHRE_KG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CO2_ERZEUGUNG_FUHRE_KG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CO2_ERZEUGUNG_FUHRE_KG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CO2_ERZEUGUNG_FUHRE_KG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_ERZEUGUNG_FUHRE_KG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CO2_ERZEUGUNG_FUHRE_KG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_ERZEUGUNG_FUHRE_KG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CO2_ERZEUGUNG_FUHRE_KG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_ERZEUGUNG_FUHRE_KG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CO2_ERZEUGUNG_FUHRE_KG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_ERZEUGUNG_FUHRE_KG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CO2_PRO_LITER_DIESEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_PRO_LITER_DIESEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CO2_PRO_LITER_DIESEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CO2_PRO_LITER_DIESEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CO2_PRO_LITER_DIESEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_PRO_LITER_DIESEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CO2_PRO_LITER_DIESEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_PRO_LITER_DIESEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CO2_PRO_LITER_DIESEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_PRO_LITER_DIESEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CO2_PRO_LITER_DIESEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CO2_PRO_LITER_DIESEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EIGENE_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EIGENE_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EIGENE_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENE_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENE_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EIGENE_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENE_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EN_CO2_RESULT_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_CO2_RESULT_STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EN_CO2_RESULT_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EN_CO2_RESULT_STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EN_CO2_RESULT_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_CO2_RESULT_STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_CO2_RESULT_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_CO2_RESULT_STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_CO2_RESULT_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_CO2_RESULT_STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EN_CO2_RESULT_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EN_CO2_RESULT_STATUS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_CO2_PROFIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_CO2_PROFIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FUHREN_CO2_PROFIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FUHREN_CO2_PROFIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_CO2_PROFIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_CO2_PROFIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_CO2_PROFIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_CO2_PROFIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_CO2_PROFIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_CO2_PROFIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_CO2_PROFIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_CO2_PROFIL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_KM_DISTANZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KM_DISTANZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KM_DISTANZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KM_DISTANZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KM_DISTANZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KM_DISTANZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KM_DISTANZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KM_DISTANZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KM_DISTANZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KM_DISTANZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KM_DISTANZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KM_DISTANZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LADUNG_FUHRE_IN_TONNEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADUNG_FUHRE_IN_TONNEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LADUNG_FUHRE_IN_TONNEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LADUNG_FUHRE_IN_TONNEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LADUNG_FUHRE_IN_TONNEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADUNG_FUHRE_IN_TONNEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADUNG_FUHRE_IN_TONNEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADUNG_FUHRE_IN_TONNEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LADUNG_FUHRE_IN_TONNEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADUNG_FUHRE_IN_TONNEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LADUNG_FUHRE_IN_TONNEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LADUNG_FUHRE_IN_TONNEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LITER_PRO_100_LKW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LITER_PRO_100_LKW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW_ANH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW_ANH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LITER_PRO_100_LKW_ANH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LITER_PRO_100_LKW_ANH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW_ANH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW_ANH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW_ANH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW_ANH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW_ANH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW_ANH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LITER_PRO_100_LKW_ANH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LITER_PRO_100_LKW_ANH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LKW_MIT_ANHAENGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LKW_MIT_ANHAENGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LKW_MIT_ANHAENGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LKW_MIT_ANHAENGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LKW_MIT_ANHAENGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LKW_MIT_ANHAENGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LKW_MIT_ANHAENGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LKW_MIT_ANHAENGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LKW_MIT_ANHAENGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LKW_MIT_ANHAENGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LKW_MIT_ANHAENGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LKW_MIT_ANHAENGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SPRITVERBRAUCH_FUHRE_L(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRITVERBRAUCH_FUHRE_L", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SPRITVERBRAUCH_FUHRE_L(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SPRITVERBRAUCH_FUHRE_L", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SPRITVERBRAUCH_FUHRE_L(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRITVERBRAUCH_FUHRE_L", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPRITVERBRAUCH_FUHRE_L(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRITVERBRAUCH_FUHRE_L", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SPRITVERBRAUCH_FUHRE_L(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRITVERBRAUCH_FUHRE_L", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SPRITVERBRAUCH_FUHRE_L(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SPRITVERBRAUCH_FUHRE_L", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_UUID_CODE_OF_CALL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UUID_CODE_OF_CALL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UUID_CODE_OF_CALL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UUID_CODE_OF_CALL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UUID_CODE_OF_CALL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UUID_CODE_OF_CALL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UUID_CODE_OF_CALL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UUID_CODE_OF_CALL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UUID_CODE_OF_CALL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UUID_CODE_OF_CALL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UUID_CODE_OF_CALL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UUID_CODE_OF_CALL", calNewValueFormated);
	}
	}
