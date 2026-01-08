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

public class RECORDNEW_LOGIN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_LOGIN";
    private _TAB  tab = _TAB.login;  


	public RECORDNEW_LOGIN() throws myException 
	{
		super("JD_LOGIN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LOGIN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LOGIN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_LOGIN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LOGIN.TABLENAME);
	}
	
	
	
	public RECORDNEW_LOGIN(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_LOGIN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LOGIN.TABLENAME);
	}

	
	
	public RECORD_LOGIN do_WRITE_NEW_LOGIN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LOGIN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LOGIN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LOGIN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LOGIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LOGIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LOGIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LOGIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LOGIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LOGIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LOGIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LOGIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LOGIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LOGIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LOGIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LOGIN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZEIT", calNewValueFormated);
	}
	}
