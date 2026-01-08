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

public class RECORDNEW_JASPER_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_JASPER_DEF";
    private _TAB  tab = _TAB.jasper_def;  


	public RECORDNEW_JASPER_DEF() throws myException 
	{
		super("JT_JASPER_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_JASPER_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_JASPER_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_JASPER_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_JASPER_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_JASPER_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_JASPER_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_JASPER_DEF.TABLENAME);
	}

	
	
	public RECORD_JASPER_DEF do_WRITE_NEW_JASPER_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_JASPER_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_JASPER_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table JASPER_DEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_CSV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_CSV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_CSV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_CSV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_CSV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_CSV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_CSV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_HTML(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_HTML", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_PDF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_PDF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_XLS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_XLS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALLOW_XML(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALLOW_XML", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALLOW_XML", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION5", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION5(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DESCRIPTION5", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION5", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION5", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION5", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DESCRIPTION5", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_JASPER_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_JASPER_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_OF_JASPERFILE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_OF_JASPERFILE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM1_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM1_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM1_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM1_DEFINITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM1_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM1_DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM1_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM1_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM1_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM1_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM1_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM2_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM2_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM2_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM2_DEFINITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM2_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM2_DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM2_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM2_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM2_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM2_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM2_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM3_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM3_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM3_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM3_DEFINITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM3_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM3_DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM3_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM3_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM3_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM3_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM3_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM4_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM4_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM4_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM4_DEFINITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM4_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM4_DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM4_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM4_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM4_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM4_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM4_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM5_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM5_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM5_DEFINITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM5_DEFINITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM5_DESCRIPTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM5_DESCRIPTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM5_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM5_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_TYPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM5_TYPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM5_TYPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_TYPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_TYPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_TYPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM5_TYPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORT_NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_REPORT_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("REPORT_NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORT_NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORT_NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORT_NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("REPORT_NAME", calNewValueFormated);
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
