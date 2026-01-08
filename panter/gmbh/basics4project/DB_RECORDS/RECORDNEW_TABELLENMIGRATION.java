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

public class RECORDNEW_TABELLENMIGRATION extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_TABELLENMIGRATION";
    private _TAB  tab = _TAB.tabellenmigration;  


	public RECORDNEW_TABELLENMIGRATION() throws myException 
	{
		super("JD_TABELLENMIGRATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TABELLENMIGRATION.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TABELLENMIGRATION(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_TABELLENMIGRATION", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TABELLENMIGRATION.TABLENAME);
	}
	
	
	
	public RECORDNEW_TABELLENMIGRATION(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_TABELLENMIGRATION");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TABELLENMIGRATION.TABLENAME);
	}

	
	
	public RECORD_TABELLENMIGRATION do_WRITE_NEW_TABELLENMIGRATION(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TABELLENMIGRATION");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TABELLENMIGRATION(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TABELLENMIGRATION ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADDIEREN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ADDIEREN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ADDIEREN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADDIEREN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADDIEREN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADDIEREN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADDIEREN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIBUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESCHREIBUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_FELDLAENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_FELDLAENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_FELDNACHKOMMA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_FELDNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_FELDNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_FELDTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_FELDTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_FELDTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_NULLOK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_NULLOK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_NULLOK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_NULLOK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_NULLOK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_NULLOK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_NULLOK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_TABELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DF_TABELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DF_TABELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_TABELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_TABELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_TABELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DF_TABELLE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TABELLENMIGRATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TABELLENMIGRATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_DEFAULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_DEFAULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDLAENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_FELDLAENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_FELDNACHKOMMA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_FELDNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_FELDTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_INDEXFELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_INDEXFELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_NULLOK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_NULLOK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_NULLOK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_NULLOK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_NULLOK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_NULLOK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_NULLOK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_TABELLE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_JAVA_TABELLE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("JAVA_TABELLE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_TABELLE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_TABELLE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_TABELLE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("JAVA_TABELLE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KONSTANTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONSTANTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KONSTANTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KONSTANTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONSTANTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONSTANTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONSTANTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KONSTANTE", calNewValueFormated);
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
