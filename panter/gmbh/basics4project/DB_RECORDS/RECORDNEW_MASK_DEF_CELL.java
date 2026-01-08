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

public class RECORDNEW_MASK_DEF_CELL extends MyRECORD_NEW 
{

    public static String TABLENAME = "JD_MASK_DEF_CELL";
    private _TAB  tab = _TAB.mask_def_cell;  


	public RECORDNEW_MASK_DEF_CELL() throws myException 
	{
		super("JD_MASK_DEF_CELL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASK_DEF_CELL.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_MASK_DEF_CELL(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JD_MASK_DEF_CELL", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASK_DEF_CELL.TABLENAME);
	}
	
	
	
	public RECORDNEW_MASK_DEF_CELL(MyConnection Conn) throws myException 
	{
		super(Conn, "JD_MASK_DEF_CELL");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_MASK_DEF_CELL.TABLENAME);
	}

	
	
	public RECORD_MASK_DEF_CELL do_WRITE_NEW_MASK_DEF_CELL(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_MASK_DEF_CELL");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_MASK_DEF_CELL(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table MASK_DEF_CELL ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ALIGNMENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ALIGNMENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ALIGNMENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BOTTOM_INSETS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOTTOM_INSETS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BOTTOM_INSETS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BOTTOM_INSETS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BOTTOM_INSETS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOTTOM_INSETS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOTTOM_INSETS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOTTOM_INSETS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BOTTOM_INSETS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOTTOM_INSETS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BOTTOM_INSETS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BOTTOM_INSETS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CLASSNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CLASSNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CLASSNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CLASSNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CLASSNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CLASSNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CLASSNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CLASSNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CLASSNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CLASSNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CLASSNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CLASSNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_COLSPAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLSPAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_COLSPAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("COLSPAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_COLSPAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLSPAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLSPAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLSPAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_COLSPAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLSPAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_COLSPAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("COLSPAN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELDNAME", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIELD_HEIGTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_HEIGTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELD_HEIGTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELD_HEIGTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELD_HEIGTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_HEIGTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_HEIGTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_HEIGTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_HEIGTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_HEIGTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELD_HEIGTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_HEIGTH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIELD_LENGTH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_LENGTH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIELD_LENGTH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIELD_LENGTH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIELD_LENGTH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_LENGTH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_LENGTH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_LENGTH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_LENGTH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_LENGTH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIELD_LENGTH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIELD_LENGTH", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASK_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASK_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF_CELL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF_CELL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASK_DEF_CELL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASK_DEF_CELL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF_CELL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF_CELL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF_CELL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF_CELL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF_CELL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF_CELL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASK_DEF_CELL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASK_DEF_CELL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LEFT_INSETS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_INSETS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LEFT_INSETS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LEFT_INSETS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LEFT_INSETS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_INSETS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEFT_INSETS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_INSETS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LEFT_INSETS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_INSETS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LEFT_INSETS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LEFT_INSETS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_RIGHT_INSETS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RIGHT_INSETS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_RIGHT_INSETS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("RIGHT_INSETS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_RIGHT_INSETS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RIGHT_INSETS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RIGHT_INSETS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RIGHT_INSETS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_RIGHT_INSETS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RIGHT_INSETS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_RIGHT_INSETS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("RIGHT_INSETS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ROWSPAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROWSPAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ROWSPAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ROWSPAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ROWSPAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROWSPAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROWSPAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROWSPAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ROWSPAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROWSPAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ROWSPAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ROWSPAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_START_COL_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_COL_IN_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_START_COL_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("START_COL_IN_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_START_COL_IN_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_COL_IN_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_COL_IN_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_COL_IN_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_COL_IN_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_COL_IN_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_START_COL_IN_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_COL_IN_MASK", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_START_ROW_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_ROW_IN_MASK", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_START_ROW_IN_MASK(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("START_ROW_IN_MASK", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_START_ROW_IN_MASK(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_ROW_IN_MASK", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_ROW_IN_MASK(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_ROW_IN_MASK", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_ROW_IN_MASK(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_ROW_IN_MASK", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_START_ROW_IN_MASK(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_ROW_IN_MASK", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TEXT_BOLD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_BOLD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT_BOLD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT_BOLD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_BOLD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_BOLD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_BOLD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_BOLD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_BOLD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_BOLD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_BOLD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_BOLD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT_ITALIC(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_ITALIC", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT_ITALIC(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT_ITALIC", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_ITALIC(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_ITALIC", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_ITALIC(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_ITALIC", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_ITALIC(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_ITALIC", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_ITALIC(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_ITALIC", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TEXT_SIZE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_SIZE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TEXT_SIZE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TEXT_SIZE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_SIZE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_SIZE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_SIZE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_SIZE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT_SIZE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_SIZE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TEXT_SIZE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TEXT_SIZE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TOP_INSETS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOP_INSETS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TOP_INSETS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TOP_INSETS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TOP_INSETS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOP_INSETS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TOP_INSETS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOP_INSETS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TOP_INSETS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOP_INSETS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TOP_INSETS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TOP_INSETS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_USERTEXT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERTEXT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_USERTEXT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("USERTEXT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_USERTEXT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERTEXT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USERTEXT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERTEXT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_USERTEXT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERTEXT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_USERTEXT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("USERTEXT", calNewValueFormated);
	}
	}
