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

public class RECORDNEW_AH7_PROFIL extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_AH7_PROFIL";
    private _TAB  tab = _TAB.ah7_profil;  


	public RECORDNEW_AH7_PROFIL() throws myException 
	{
		super("JT_AH7_PROFIL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_AH7_PROFIL.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_AH7_PROFIL(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_AH7_PROFIL", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_AH7_PROFIL.TABLENAME);
	}
	
	
	
	public RECORDNEW_AH7_PROFIL(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_AH7_PROFIL");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_AH7_PROFIL.TABLENAME);
	}

	
	
	public RECORD_AH7_PROFIL do_WRITE_NEW_AH7_PROFIL(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_AH7_PROFIL");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_AH7_PROFIL(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table AH7_PROFIL ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABFALLERZEUGER_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABFALLERZEUGER_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABFALLERZEUGER_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABFALLERZEUGER_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABFALLERZEUGER_3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABFALLERZEUGER_3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_QUELLE_SICHER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_QUELLE_SICHER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AH7_ZIEL_SICHER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AH7_ZIEL_SICHER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_AH7_PROFIL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_AH7_PROFIL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IMPORT_EMPFAENGER_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IMPORT_EMPFAENGER_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IMPORT_EMPFAENGER_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IMPORT_EMPFAENGER_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IMPORT_EMPFAENGER_3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IMPORT_EMPFAENGER_3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PROFILE4ALLOTHERS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PROFILE4ALLOTHERS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STARTLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STARTLAGER_FREMDBESITZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_START_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("START_EIGENES_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_START_INLAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_INLAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_START_INLAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("START_INLAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_START_INLAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_INLAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_INLAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_INLAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_START_INLAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_INLAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_START_INLAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("START_INLAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS_RELATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS_RELATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERBR_VERANLASSER_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERBR_VERANLASSER_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERBR_VERANLASSER_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERBR_VERANLASSER_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERBR_VERANLASSER_3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERBR_VERANLASSER_3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSANLAGE_1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERWERTUNGSANLAGE_1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSANLAGE_2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERWERTUNGSANLAGE_2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSANLAGE_3(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERWERTUNGSANLAGE_3", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZIELLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZIEL_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZIEL_EIGENES_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_INLAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZIEL_INLAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZIEL_INLAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_INLAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_INLAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_INLAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZIEL_INLAND", calNewValueFormated);
	}
	}
