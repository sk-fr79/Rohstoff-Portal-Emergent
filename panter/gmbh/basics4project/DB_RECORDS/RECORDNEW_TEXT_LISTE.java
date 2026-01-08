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

public class RECORDNEW_TEXT_LISTE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TEXT_LISTE";
    private _TAB  tab = _TAB.text_liste;  


	public RECORDNEW_TEXT_LISTE() throws myException 
	{
		super("JT_TEXT_LISTE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TEXT_LISTE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TEXT_LISTE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TEXT_LISTE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TEXT_LISTE.TABLENAME);
	}
	
	
	
	public RECORDNEW_TEXT_LISTE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TEXT_LISTE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TEXT_LISTE.TABLENAME);
	}

	
	
	public RECORD_TEXT_LISTE do_WRITE_NEW_TEXT_LISTE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TEXT_LISTE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TEXT_LISTE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TEXT_LISTE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUFZAEHL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BOLD_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BOLD_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BOLD_LANG_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BOLD_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BOLD_TITEL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FONTSIZE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FONTSIZE_LANG_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FONTSIZE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FONTSIZE_TITEL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TEXT_LISTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TEXT_LISTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ITALIC_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ITALIC_LANG_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ITALIC_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ITALIC_TITEL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANG_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LANG_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANG_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANG_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANG_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANG_TEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_POSITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ENUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITION_ENUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITION_ENUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ENUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ENUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ENUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ENUM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TITEL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TITEL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UNDERLINE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UNDERLINE_LANG_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UNDERLINE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UNDERLINE_TITEL_TEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", calNewValueFormated);
	}
	}
