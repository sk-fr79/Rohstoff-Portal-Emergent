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

public class RECORDNEW_VPOS_RG_ABZUG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_RG_ABZUG";
    private _TAB  tab = _TAB.vpos_rg_abzug;  


	public RECORDNEW_VPOS_RG_ABZUG() throws myException 
	{
		super("JT_VPOS_RG_ABZUG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_RG_ABZUG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_RG_ABZUG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_RG_ABZUG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_RG_ABZUG.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_RG_ABZUG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_RG_ABZUG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_RG_ABZUG.TABLENAME);
	}

	
	
	public RECORD_VPOS_RG_ABZUG do_WRITE_NEW_VPOS_RG_ABZUG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_RG_ABZUG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_RG_ABZUG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_RG_ABZUG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUGTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUGTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUGTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUGTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUGTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUGTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUGTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUGTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUGTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUGTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUGTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUGTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_BELEGTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_BELEGTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABZUG_BELEGTEXT_SCHABLONE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABZUG_GESAMT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_ABZUG_GESAMT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABZUG_GESAMT_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANTEIL_ABZUG_GESAMT_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABZUG_GESAMT_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANTEIL_ABZUG_GESAMT_FW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_NACH_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NACH_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_NACH_ABZUG_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NACH_ABZUG_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_NACH_ABZUG_FW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_VOR_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_VOR_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EPREIS_VOR_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EPREIS_VOR_ABZUG_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_VOR_ABZUG_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EPREIS_VOR_ABZUG_FW", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ABZUGSGRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ABZUGSGRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ABZUGSGRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ABZUGSGRUND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_RG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_RG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_RG_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_RG_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KURZTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KURZTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KURZTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KURZTEXT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LANGTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LANGTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LANGTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LANGTEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGENFAKTOR_FUER_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENFAKTOR_FUER_DRUCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENFAKTOR_FUER_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENFAKTOR_FUER_DRUCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENFAKTOR_FUER_DRUCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENFAKTOR_FUER_DRUCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENFAKTOR_FUER_DRUCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENFAKTOR_FUER_DRUCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENFAKTOR_FUER_DRUCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENFAKTOR_FUER_DRUCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENFAKTOR_FUER_DRUCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENFAKTOR_FUER_DRUCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_NACH_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NACH_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NACH_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_VOR_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VOR_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_VOR_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_VOR_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VOR_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VOR_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VOR_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VOR_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VOR_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VOR_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VOR_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VOR_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POS_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POS_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POS_NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POS_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POS_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POS_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POS_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POS_NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISFAKTOR_FUER_DRUCK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISFAKTOR_FUER_DRUCK_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISFAKTOR_FUER_DRUCK_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISFAKTOR_FUER_DRUCK_FW", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_WAAGE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAAGE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAAGE_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAAGE_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAAGE_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAAGE_ABZUG", calNewValueFormated);
	}
	}
