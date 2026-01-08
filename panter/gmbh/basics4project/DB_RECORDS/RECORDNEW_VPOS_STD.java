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

public class RECORDNEW_VPOS_STD extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_STD";
    private _TAB  tab = _TAB.vpos_std;  


	public RECORDNEW_VPOS_STD() throws myException 
	{
		super("JT_VPOS_STD");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_STD.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_STD(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_STD", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_STD.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_STD(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_STD");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_STD.TABLENAME);
	}

	
	
	public RECORD_VPOS_STD do_WRITE_NEW_VPOS_STD(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_STD");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_STD(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_STD ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANR1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ANZAHL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZAHL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZAHL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZAHL_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSFUEHRUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSFUEHRUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEITKURZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINHEITKURZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINHEITKURZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEITKURZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEITKURZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEITKURZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEITKURZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINHEIT_PREIS_KURZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINHEIT_PREIS_KURZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_ABZUG_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_RESULT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_RESULT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_RESULT_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINZELPREIS_RESULT_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUCODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUCODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUCODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EUNOTIZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXMONAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXTAG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEBUCHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBUCHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEBUCHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEBUCHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBUCHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBUCHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBUCHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEBUCHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_FW(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GESAMTPREIS_FW", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_STRECKEN_DEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_STRECKEN_DEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_STD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VKOPF_STD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_ZUGEORD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LAGER_VORZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITIONSKLASSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITIONSKLASSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITIONSNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITIONSNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITION_ACTIVE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITION_ACTIVE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_POSITION_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("POSITION_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("POSITION_TYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STEUERSATZ", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNGSKURS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WAEHRUNGSKURS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", calNewValueFormated);
	}
	}
