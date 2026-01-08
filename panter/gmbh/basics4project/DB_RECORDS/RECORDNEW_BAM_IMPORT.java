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

public class RECORDNEW_BAM_IMPORT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BAM_IMPORT";
    private _TAB  tab = _TAB.bam_import;  


	public RECORDNEW_BAM_IMPORT() throws myException 
	{
		super("JT_BAM_IMPORT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BAM_IMPORT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BAM_IMPORT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BAM_IMPORT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BAM_IMPORT.TABLENAME);
	}
	
	
	
	public RECORDNEW_BAM_IMPORT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BAM_IMPORT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BAM_IMPORT.TABLENAME);
	}

	
	
	public RECORD_BAM_IMPORT do_WRITE_NEW_BAM_IMPORT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BAM_IMPORT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BAM_IMPORT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BAM_IMPORT ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_BAM_ANGELEGT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ANGELEGT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BAM_ANGELEGT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BAM_ANGELEGT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BAM_ANGELEGT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ANGELEGT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_ANGELEGT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ANGELEGT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_ANGELEGT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ANGELEGT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BAM_ANGELEGT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_ANGELEGT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BAM_GESENDET_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GESENDET_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BAM_GESENDET_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BAM_GESENDET_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BAM_GESENDET_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GESENDET_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_GESENDET_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GESENDET_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_GESENDET_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GESENDET_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BAM_GESENDET_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BAM_GESENDET_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BELEGNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEGNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEGNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEGNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEGNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEGNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEGNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEVICE_ID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVICE_ID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEVICE_ID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEVICE_ID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEVICE_ID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVICE_ID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEVICE_ID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVICE_ID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEVICE_ID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVICE_ID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEVICE_ID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVICE_ID", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BAM_IMPORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BAM_IMPORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BAM_IMPORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BAM_IMPORT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
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
