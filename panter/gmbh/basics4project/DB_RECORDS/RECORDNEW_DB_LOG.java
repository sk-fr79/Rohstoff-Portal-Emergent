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

public class RECORDNEW_DB_LOG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_DB_LOG";
    private _TAB  tab = _TAB.db_log;  


	public RECORDNEW_DB_LOG() throws myException 
	{
		super("JD_DB_LOG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DB_LOG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_DB_LOG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_DB_LOG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DB_LOG.TABLENAME);
	}
	
	
	
	public RECORDNEW_DB_LOG(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_DB_LOG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_DB_LOG.TABLENAME);
	}

	
	
	public RECORD_DB_LOG do_WRITE_NEW_DB_LOG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_DB_LOG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_DB_LOG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table DB_LOG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AKTION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DBSESSION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DBSESSION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DBSESSION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DBSESSION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DBSESSION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DBSESSION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DBSESSION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DBSESSION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DBSESSION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DBSESSION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DBSESSION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DBSESSION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_DB_LOG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DB_LOG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DB_LOG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DB_LOG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DB_LOG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DB_LOG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DB_LOG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DB_LOG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DB_LOG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DB_LOG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DB_LOG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DB_LOG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TIMESTAMPMILLISECS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMPMILLISECS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TIMESTAMPMILLISECS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TIMESTAMPMILLISECS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMPMILLISECS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMPMILLISECS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMPMILLISECS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMPMILLISECS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMPMILLISECS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMPMILLISECS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMPMILLISECS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TIMESTAMPMILLISECS", calNewValueFormated);
	}
	}
