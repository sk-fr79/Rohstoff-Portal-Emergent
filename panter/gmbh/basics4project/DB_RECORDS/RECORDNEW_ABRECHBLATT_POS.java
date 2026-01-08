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

public class RECORDNEW_ABRECHBLATT_POS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ABRECHBLATT_POS";
    private _TAB  tab = _TAB.abrechblatt_pos;  


	public RECORDNEW_ABRECHBLATT_POS() throws myException 
	{
		super("JT_ABRECHBLATT_POS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ABRECHBLATT_POS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ABRECHBLATT_POS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ABRECHBLATT_POS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ABRECHBLATT_POS.TABLENAME);
	}
	
	
	
	public RECORDNEW_ABRECHBLATT_POS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ABRECHBLATT_POS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ABRECHBLATT_POS.TABLENAME);
	}

	
	
	public RECORD_ABRECHBLATT_POS do_WRITE_NEW_ABRECHBLATT_POS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_ABRECHBLATT_POS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ABRECHBLATT_POS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ABRECHBLATT_POS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ABRECHBLATT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ABRECHBLATT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ABRECHBLATT_POS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ABRECHBLATT_POS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KFZ_KENNZ_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KFZ_KENNZ_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEISTUNGSDATUM_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MATERIAL_TEXT_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MATERIAL_TEXT_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITION_FIXIERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITION_FIXIERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", calNewValueFormated);
	}
	}
