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

public class RECORDNEW_USER extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_USER";
    private _TAB  tab = _TAB.user;  


	public RECORDNEW_USER() throws myException 
	{
		super("JD_USER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_USER.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_USER(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_USER", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_USER.TABLENAME);
	}
	
	
	
	public RECORDNEW_USER(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_USER");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_USER.TABLENAME);
	}

	
	
	public RECORD_USER do_WRITE_NEW_USER(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_USER");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_USER(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table USER ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AKTIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANREDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANREDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANREDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANREDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUTCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUTCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUTCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUTCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUTCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUTCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUTCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUTCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_ERROR_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_ERROR_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_ERROR_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_ERROR_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_OK_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_OK_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_OK_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_OK_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_OK_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_OK_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_WARN_BLUE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_WARN_BLUE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_WARN_GREEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_WARN_GREEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_WARN_RED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLOR_MASK_WARN_RED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVELOPER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEVELOPER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEVELOPER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVELOPER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVELOPER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVELOPER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEVELOPER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EIGENDEF_MENUEBREITE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EIGENDEF_MENUEBREITE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EMAIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EMAIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EMAIL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FENSTER_MIT_SCHATTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FENSTER_MIT_SCHATTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HAT_FAHRPLAN_BUTTON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USERGROUP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USERGROUP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USERGROUP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USERGROUP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USERGROUP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USERGROUP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USERGROUP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_FAHRER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_FAHRER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_FAHRER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_FAHRER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_FAHRER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_FAHRER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_FAHRER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_SUPERVISOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_SUPERVISOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_VERWALTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_VERWALTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KUERZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAUFZEIT_SESSION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAUFZEIT_SESSION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LISTEGESAMTLAENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LISTEGESAMTLAENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LISTESEITELAENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LISTESEITELAENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MAIL_SIGNATUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MAIL_SIGNATUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PASSWORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PASSWORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PASSWORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PASSWORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PASSWORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PASSWORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TELEFAX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TELEFAX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TELEFAX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFAX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TELEFON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TELEFON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TELEFON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXTCOLLECTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXTCOLLECTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TODO_SUPERVISOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TODO_SUPERVISOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
	}
