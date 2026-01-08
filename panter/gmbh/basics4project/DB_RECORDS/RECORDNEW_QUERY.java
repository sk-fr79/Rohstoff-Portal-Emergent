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

public class RECORDNEW_QUERY extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_QUERY";
    private _TAB  tab = _TAB.query;  


	public RECORDNEW_QUERY() throws myException 
	{
		super("JT_QUERY");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_QUERY.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_QUERY(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_QUERY", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_QUERY.TABLENAME);
	}
	
	
	
	public RECORDNEW_QUERY(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_QUERY");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_QUERY.TABLENAME);
	}

	
	
	public RECORD_QUERY do_WRITE_NEW_QUERY(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_QUERY");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_QUERY(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table QUERY ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGESQL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZEIGESQL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZEIGESQL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGESQL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGESQL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGESQL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZEIGESQL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIB1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIB2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIB3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB4", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB4(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIB4", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB4", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB4", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB4", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIB4", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFAULT01(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT01", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULT01(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULT01", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT01", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT01", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT01", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT01", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFAULT02(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT02", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULT02(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULT02", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT02", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT02", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT02", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT02", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFAULT03(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT03", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULT03(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULT03", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT03", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT03", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT03", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT03", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFAULT04(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT04", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULT04(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULT04", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT04", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT04", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT04", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT04", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFAULT05(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT05", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULT05(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULT05", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT05", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT05", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT05", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT05", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEFAULT06(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT06", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEFAULT06(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEFAULT06", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT06", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT06", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT06", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEFAULT06", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOAD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DOWNLOAD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DOWNLOAD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOAD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOAD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOAD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DOWNLOAD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_QUERY(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_QUERY", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_QUERY(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_QUERY", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_QUERY", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_QUERY", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_QUERY", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_QUERY", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTE_TITEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LISTE_TITEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LISTE_TITEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTE_TITEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTE_TITEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTE_TITEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTE_TITEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM01(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM01", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM01(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM01", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM01(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM01", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM01(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM01", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM01(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM01", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM01(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM01", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM02(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM02", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM02(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM02", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM02(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM02", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM02(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM02", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM02(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM02", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM02(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM02", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM03(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM03", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM03(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM03", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM03(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM03", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM03(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM03", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM03(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM03", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM03(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM03", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM04(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM04", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM04(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM04", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM04(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM04", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM04(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM04", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM04(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM04", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM04(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM04", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM05(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM05", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM05(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM05", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM05(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM05", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM05(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM05", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM05(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM05", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM05(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM05", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAM06(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM06", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAM06(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAM06", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAM06(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM06", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM06(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM06", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM06(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM06", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAM06(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAM06", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF01(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDROPDOWNDEF01", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF02(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDROPDOWNDEF02", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF03(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDROPDOWNDEF03", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF04(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDROPDOWNDEF04", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF05(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDROPDOWNDEF05", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF06(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PARAMDROPDOWNDEF06", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFELDLISTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQLFELDLISTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQLFELDLISTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFELDLISTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFELDLISTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFELDLISTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFELDLISTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQLFROMBLOCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQLFROMBLOCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLINDEXFELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQLINDEXFELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQLINDEXFELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLINDEXFELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLINDEXFELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLINDEXFELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLINDEXFELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQLORDERBLOCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQLORDERBLOCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SQLWHEREBLOCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SQLWHEREBLOCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", calNewValueFormated);
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
