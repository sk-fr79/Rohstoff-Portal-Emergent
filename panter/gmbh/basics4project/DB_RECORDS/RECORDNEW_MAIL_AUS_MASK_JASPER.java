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

public class RECORDNEW_MAIL_AUS_MASK_JASPER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_MAIL_AUS_MASK_JASPER";
    private _TAB  tab = _TAB.mail_aus_mask_jasper;  


	public RECORDNEW_MAIL_AUS_MASK_JASPER() throws myException 
	{
		super("JT_MAIL_AUS_MASK_JASPER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAIL_AUS_MASK_JASPER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MAIL_AUS_MASK_JASPER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_MAIL_AUS_MASK_JASPER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAIL_AUS_MASK_JASPER.TABLENAME);
	}
	
	
	
	public RECORDNEW_MAIL_AUS_MASK_JASPER(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_MAIL_AUS_MASK_JASPER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MAIL_AUS_MASK_JASPER.TABLENAME);
	}

	
	
	public RECORD_MAIL_AUS_MASK_JASPER do_WRITE_NEW_MAIL_AUS_MASK_JASPER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MAIL_AUS_MASK_JASPER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MAIL_AUS_MASK_JASPER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MAIL_AUS_MASK_JASPER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER01(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER01", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER01(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER01", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER01", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER01", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER01", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER01", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER02(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER02", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER02(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER02", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER02", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER02", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER02", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER02", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER03(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER03", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER03(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER03", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER03", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER03", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER03", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER03", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER04(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER04", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER04(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER04", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER04", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER04", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER04", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER04", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER05(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER05", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER05(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER05", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER05", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER05", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER05", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER05", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER06(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER06", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER06(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER06", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER06", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER06", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER06", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER06", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER07(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER07", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER07(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER07", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER07", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER07", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER07", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER07", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER08(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER08", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER08(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER08", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER08", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER08", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER08", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER08", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER09(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER09", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER09(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER09", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER09", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER09", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER09", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER09", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER10(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER10", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER10(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER10", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER10", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER10", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER10", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER10", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER11(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER11", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER11(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER11", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER11", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER11", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER11", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER11", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER12(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER12", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER12(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER12", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER12", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER12", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER12", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER12", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER13(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER13", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER13(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER13", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER13", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER13", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER13", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER13", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER14(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER14", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER14(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER14", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER14", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER14", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER14", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER14", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER15(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER15", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER15(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER15", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER15", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER15", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER15", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER15", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER16(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER16", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER16(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER16", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER16", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER16", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER16", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER16", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER17(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER17", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER17(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER17", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER17", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER17", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER17", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER17", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER18(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER18", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER18(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER18", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER18", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER18", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER18", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER18", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER19(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER19", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER19(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER19", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER19", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER19", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER19", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER19", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMETER20(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER20", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMETER20(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMETER20", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER20", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER20", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER20", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMETER20", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REPORTNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REPORTNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REPORTNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORTNAME", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WERT01(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT01", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT01(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT01", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT01(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT01", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT01(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT01", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT01(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT01", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT01(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT01", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT02(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT02", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT02(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT02", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT02(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT02", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT02(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT02", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT02(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT02", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT02(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT02", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT03(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT03", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT03(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT03", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT03(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT03", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT03(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT03", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT03(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT03", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT03(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT03", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT04(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT04", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT04(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT04", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT04(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT04", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT04(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT04", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT04(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT04", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT04(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT04", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT05(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT05", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT05(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT05", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT05(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT05", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT05(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT05", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT05(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT05", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT05(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT05", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT06(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT06", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT06(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT06", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT06(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT06", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT06(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT06", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT06(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT06", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT06(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT06", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT07(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT07", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT07(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT07", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT07(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT07", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT07(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT07", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT07(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT07", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT07(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT07", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT08(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT08", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT08(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT08", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT08(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT08", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT08(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT08", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT08(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT08", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT08(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT08", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT09(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT09", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT09(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT09", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT09(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT09", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT09(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT09", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT09(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT09", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT09(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT09", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT10(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT10", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT10(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT10", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT10(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT10", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT10(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT10", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT10(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT10", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT10(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT10", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT11(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT11", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT11(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT11", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT11(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT11", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT11(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT11", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT11(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT11", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT11(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT11", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT12(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT12", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT12(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT12", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT12(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT12", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT12(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT12", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT12(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT12", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT12(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT12", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT13(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT13", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT13(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT13", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT13(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT13", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT13(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT13", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT13(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT13", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT13(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT13", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT14(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT14", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT14(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT14", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT14(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT14", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT14(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT14", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT14(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT14", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT14(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT14", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT15(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT15", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT15(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT15", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT15(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT15", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT15(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT15", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT15(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT15", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT15(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT15", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT16(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT16", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT16(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT16", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT16(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT16", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT16(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT16", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT16(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT16", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT16(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT16", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT17(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT17", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT17(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT17", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT17(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT17", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT17(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT17", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT17(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT17", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT17(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT17", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT18(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT18", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT18(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT18", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT18(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT18", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT18(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT18", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT18(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT18", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT18(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT18", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT19(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT19", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT19(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT19", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT19(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT19", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT19(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT19", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT19(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT19", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT19(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT19", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WERT20(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT20", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WERT20(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WERT20", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WERT20(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT20", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT20(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT20", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT20(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT20", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WERT20(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WERT20", calNewValueFormated);
	}
	}
