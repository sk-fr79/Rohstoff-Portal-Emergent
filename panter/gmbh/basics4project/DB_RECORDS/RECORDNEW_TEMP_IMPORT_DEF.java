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

public class RECORDNEW_TEMP_IMPORT_DEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_TEMP_IMPORT_DEF";
    private _TAB  tab = _TAB.temp_import_def;  


	public RECORDNEW_TEMP_IMPORT_DEF() throws myException 
	{
		super("JT_TEMP_IMPORT_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TEMP_IMPORT_DEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_TEMP_IMPORT_DEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_TEMP_IMPORT_DEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TEMP_IMPORT_DEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_TEMP_IMPORT_DEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_TEMP_IMPORT_DEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_TEMP_IMPORT_DEF.TABLENAME);
	}

	
	
	public RECORD_TEMP_IMPORT_DEF do_WRITE_NEW_TEMP_IMPORT_DEF(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_TEMP_IMPORT_DEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_TEMP_IMPORT_DEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table TEMP_IMPORT_DEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANR1_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR1_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AVV_IN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_IN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVV_IN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVV_IN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVV_IN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_IN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_IN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_IN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_IN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_IN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVV_IN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_IN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AVV_OUT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_OUT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AVV_OUT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AVV_OUT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_OUT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_OUT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_OUT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AVV_OUT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BASEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BASEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BASEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BASEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BASEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BASEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEREICHDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEREICHDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEREICHDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEREICHDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEREICHDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEREICHDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEREICHDEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINHEIT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINHEIT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINHEIT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINHEIT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINHEIT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINHEIT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT2", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPENDEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GRUPPENDEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GRUPPENDEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPENDEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPENDEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPENDEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPENDEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPE_END", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GRUPPE_END(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GRUPPE_END", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPE_END", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPE_END", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPE_END", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUPPE_END", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_TEMP_IMPORT_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_TEMP_IMPORT_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_DIENSTLEISTUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_GEFAEHRLICH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_SORTFIELD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTFIELD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTFIELD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTFIELD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTFIELD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTFIELD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTFIELD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTFIELD", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZOLLTARIF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIF", calNewValueFormated);
	}
	}
