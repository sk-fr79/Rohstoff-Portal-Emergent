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

public class RECORDNEW_FIBU_FORMULAR extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FIBU_FORMULAR";
    private _TAB  tab = _TAB.fibu_formular;  


	public RECORDNEW_FIBU_FORMULAR() throws myException 
	{
		super("JT_FIBU_FORMULAR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU_FORMULAR.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FIBU_FORMULAR(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FIBU_FORMULAR", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU_FORMULAR.TABLENAME);
	}
	
	
	
	public RECORDNEW_FIBU_FORMULAR(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FIBU_FORMULAR");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FIBU_FORMULAR.TABLENAME);
	}

	
	
	public RECORD_FIBU_FORMULAR do_WRITE_NEW_FIBU_FORMULAR(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FIBU_FORMULAR");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FIBU_FORMULAR(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FIBU_FORMULAR ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DROPDOWNTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DROPDOWNTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FORMULARNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FORMULARNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FORMULARTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FORMULARTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FORMULARTYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUT_NEGATIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUT_NEGATIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUT_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUT_NEGATIV_STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUT_POSITIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUT_POSITIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUT_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUT_POSITIV_STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_FORMULAR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FIBU_FORMULAR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECH_NEGATIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECH_NEGATIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECH_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECH_NEGATIV_STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECH_POSITIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECH_POSITIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RECH_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RECH_POSITIV_STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SCHECK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SCHECK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SCHECK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SCHECK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SCHECK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SCHECK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSAUSGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSAUSGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSEINGANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSEINGANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", calNewValueFormated);
	}
	}
