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

public class RECORDNEW_FAHRPLANPOS extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_FAHRPLANPOS";
    private _TAB  tab = _TAB.fahrplanpos;  


	public RECORDNEW_FAHRPLANPOS() throws myException 
	{
		super("JT_FAHRPLANPOS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FAHRPLANPOS.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_FAHRPLANPOS(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_FAHRPLANPOS", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FAHRPLANPOS.TABLENAME);
	}
	
	
	
	public RECORDNEW_FAHRPLANPOS(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_FAHRPLANPOS");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_FAHRPLANPOS.TABLENAME);
	}

	
	
	public RECORD_FAHRPLANPOS do_WRITE_NEW_FAHRPLANPOS(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_FAHRPLANPOS");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_FAHRPLANPOS(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table FAHRPLANPOS ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANRUFDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANRUFDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANRUFER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANRUFER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANRUFER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANRUFER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_KUNDENSPEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_KUNDENSPEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1_KUNDENSPEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ1_KUNDENSPEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_KUNDENSPEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_KUNDENSPEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_KUNDENSPEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_KUNDENSPEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_KUNDENSPEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_KUNDENSPEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_KUNDENSPEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ1_KUNDENSPEZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FAHRPLAN_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_FAHRPLAN_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FAHRPLAN_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_FAHRPLAN_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAT_FAHRPLAN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAT_FAHRPLAN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAT_VORGEMERKT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_ENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DAT_VORGEMERKT_ENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DELETED(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EAN_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EAN_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EAN_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EAN_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERFASSER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERFASSER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERFASSER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERFASSER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRPLANGRUPPE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRPLANGRUPPE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ANFANG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRT_ANFANG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ENDE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAHRT_ENDE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINERTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINERTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLANPOS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLANPOS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FAHRPLANPOS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FAHRPLANPOS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLANPOS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLANPOS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLANPOS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLANPOS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLANPOS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLANPOS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLANPOS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FAHRPLANPOS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_ANH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASCHINEN_ANH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_LKW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MASCHINEN_LKW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_GEPLANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_GEPLANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEPLANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KENNER_ALTE_SAETZE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KENNER_ALTE_SAETZE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTIERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTIERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTIERUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TAETIGKEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", calNewValueFormated);
	}
	}
