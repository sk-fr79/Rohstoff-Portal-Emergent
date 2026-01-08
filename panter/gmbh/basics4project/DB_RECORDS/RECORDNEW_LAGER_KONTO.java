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

public class RECORDNEW_LAGER_KONTO extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_LAGER_KONTO";
    private _TAB  tab = _TAB.lager_konto;  


	public RECORDNEW_LAGER_KONTO() throws myException 
	{
		super("JT_LAGER_KONTO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGER_KONTO.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LAGER_KONTO(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_LAGER_KONTO", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGER_KONTO.TABLENAME);
	}
	
	
	
	public RECORDNEW_LAGER_KONTO(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_LAGER_KONTO");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGER_KONTO.TABLENAME);
	}

	
	
	public RECORD_LAGER_KONTO do_WRITE_NEW_LAGER_KONTO(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LAGER_KONTO");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LAGER_KONTO(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LAGER_KONTO ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSZEIT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSZEIT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNG_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO_PARENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_KONTO_PARENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEAENDERT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_GEAENDERT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_GEAENDERT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEAENDERT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEAENDERT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEAENDERT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GEAENDERT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KOMPLETT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_KOMPLETT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_KOMPLETT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KOMPLETT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KOMPLETT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KOMPLETT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_KOMPLETT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_BUCH", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MENGE_BUCH(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MENGE_BUCH", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_BUCH", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_BUCH", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_BUCH", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MENGE_BUCH", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SALDO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SALDO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SALDO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SALDO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SALDO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SALDO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SALDO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SALDO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SALDO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SALDO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SALDO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SALDO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_PREIS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STATUS_PREIS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STATUS_PREIS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_PREIS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_PREIS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_PREIS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STATUS_PREIS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", calNewValueFormated);
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
