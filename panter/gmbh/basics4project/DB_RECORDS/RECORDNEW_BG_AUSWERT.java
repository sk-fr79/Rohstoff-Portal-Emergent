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

public class RECORDNEW_BG_AUSWERT extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_BG_AUSWERT";
    private _TAB  tab = _TAB.bg_auswert;  


	public RECORDNEW_BG_AUSWERT() throws myException 
	{
		super("JT_BG_AUSWERT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_AUSWERT.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_BG_AUSWERT(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_BG_AUSWERT", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_AUSWERT.TABLENAME);
	}
	
	
	
	public RECORDNEW_BG_AUSWERT(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_BG_AUSWERT");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_BG_AUSWERT.TABLENAME);
	}

	
	
	public RECORD_BG_AUSWERT do_WRITE_NEW_BG_AUSWERT(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_BG_AUSWERT");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_BG_AUSWERT(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table BG_AUSWERT ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_AUSFUEHRUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_AUSFUEHRUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSFUEHRUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_AUSFUEHRUNG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_GEGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_GEGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEGEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_ATOM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_ATOM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_AUSWERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_AUSWERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_AUSWERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_AUSWERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_AUSWERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_AUSWERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_AUSWERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_AUSWERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_AUSWERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_AUSWERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_AUSWERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_AUSWERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STATION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_STATION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STATION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGENVORZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGENVORZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGENVORZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGENVORZEICHEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABRECH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABRECH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABRECH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABRECH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_NETTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_NETTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_NETTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_NETTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_VERTEILUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_VERTEILUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VERTEILUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_VERTEILUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATION_KZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATION_KZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATION_KZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATION_KZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATION_KZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATION_KZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATION_KZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATION_KZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATION_KZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATION_KZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATION_KZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATION_KZ", calNewValueFormated);
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
