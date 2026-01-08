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

public class RECORDNEW_LAUFZETTEL_EINTRAG extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_LAUFZETTEL_EINTRAG";
    private _TAB  tab = _TAB.laufzettel_eintrag;  


	public RECORDNEW_LAUFZETTEL_EINTRAG() throws myException 
	{
		super("JT_LAUFZETTEL_EINTRAG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAUFZETTEL_EINTRAG.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LAUFZETTEL_EINTRAG(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_LAUFZETTEL_EINTRAG", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAUFZETTEL_EINTRAG.TABLENAME);
	}
	
	
	
	public RECORDNEW_LAUFZETTEL_EINTRAG(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_LAUFZETTEL_EINTRAG");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAUFZETTEL_EINTRAG.TABLENAME);
	}

	
	
	public RECORD_LAUFZETTEL_EINTRAG do_WRITE_NEW_LAUFZETTEL_EINTRAG(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LAUFZETTEL_EINTRAG");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LAUFZETTEL_EINTRAG(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LAUFZETTEL_EINTRAG ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGELEGT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANGELEGT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANGELEGT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGELEGT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGELEGT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGELEGT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGELEGT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_AUFGABE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUFGABE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUFGABE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUFGABE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUFGABE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUFGABE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BERICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BERICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BERICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BERICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BERICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BERICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BERICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BERICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BERICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BERICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BERICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BERICHT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAELLIG_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FAELLIG_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FAELLIG_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAELLIG_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAELLIG_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAELLIG_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FAELLIG_AM", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GELOESCHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GELOESCHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GELOESCHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EINTRAG_PARENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EINTRAG_PARENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAUFZETTEL", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_PRIO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAUFZETTEL_PRIO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_STATUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAUFZETTEL_STATUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_PARENT_KADENZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_PARENT_KADENZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEARBEITER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BEARBEITER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BESITZER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_USER_BESITZER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NACHRICHT_SENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NACHRICHT_SENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PRIVAT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SEND_NACHRICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SEND_NACHRICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", calNewValueFormated);
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
