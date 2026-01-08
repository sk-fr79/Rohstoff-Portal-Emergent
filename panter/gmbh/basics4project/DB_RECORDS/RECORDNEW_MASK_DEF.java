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

public class RECORDNEW_MASK_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_MASK_DEF";
    private _TAB  tab = _TAB.mask_def;  


	public RECORDNEW_MASK_DEF() throws myException 
	{
		super("JD_MASK_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASK_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MASK_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_MASK_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASK_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_MASK_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_MASK_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASK_DEF.TABLENAME);
	}

	
	
	public RECORD_MASK_DEF do_WRITE_NEW_MASK_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MASK_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MASK_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MASK_DEF ::"+vSQL.get(0));
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASK_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASK_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEFT_OFFSET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_OFFSET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEFT_OFFSET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEFT_OFFSET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEFT_OFFSET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_OFFSET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEFT_OFFSET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_OFFSET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEFT_OFFSET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_OFFSET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEFT_OFFSET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_OFFSET", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MASKNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MASKNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MASKNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MASKNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MASKNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MASKNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MASKNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MASKNAME_LONG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME_LONG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MASKNAME_LONG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MASKNAME_LONG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MASKNAME_LONG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME_LONG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MASKNAME_LONG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME_LONG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MASKNAME_LONG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME_LONG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MASKNAME_LONG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MASKNAME_LONG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NB_OF_COLS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NB_OF_COLS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NB_OF_COLS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NB_OF_COLS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NB_OF_COLS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NB_OF_COLS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NB_OF_COLS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NB_OF_COLS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NB_OF_COLS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NB_OF_COLS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NB_OF_COLS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NB_OF_COLS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PIXEL_WIDTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PIXEL_WIDTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PIXEL_WIDTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PIXEL_WIDTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PIXEL_WIDTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PIXEL_WIDTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PIXEL_WIDTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PIXEL_WIDTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PIXEL_WIDTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PIXEL_WIDTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PIXEL_WIDTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PIXEL_WIDTH", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TABLENAME", calNewValueFormated);
	}
	}
