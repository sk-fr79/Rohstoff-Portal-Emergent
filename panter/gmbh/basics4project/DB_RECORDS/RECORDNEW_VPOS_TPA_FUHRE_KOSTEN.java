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

public class RECORDNEW_VPOS_TPA_FUHRE_KOSTEN extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE_KOSTEN";
    private _TAB  tab = _TAB.vpos_tpa_fuhre_kosten;  


	public RECORDNEW_VPOS_TPA_FUHRE_KOSTEN() throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_KOSTEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_KOSTEN.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_VPOS_TPA_FUHRE_KOSTEN(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_KOSTEN", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_KOSTEN.TABLENAME);
	}
	
	
	
	public RECORDNEW_VPOS_TPA_FUHRE_KOSTEN(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_VPOS_TPA_FUHRE_KOSTEN");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_VPOS_TPA_FUHRE_KOSTEN.TABLENAME);
	}

	
	
	public RECORD_VPOS_TPA_FUHRE_KOSTEN do_WRITE_NEW_VPOS_TPA_FUHRE_KOSTEN(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_VPOS_TPA_FUHRE_KOSTEN");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_VPOS_TPA_FUHRE_KOSTEN(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table VPOS_TPA_FUHRE_KOSTEN ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
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
		public MyE2_MessageVector set_NEW_VALUE_BELEG_VORHANDEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_VORHANDEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BELEG_VORHANDEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BELEG_VORHANDEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BELEG_VORHANDEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_VORHANDEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEG_VORHANDEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_VORHANDEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BELEG_VORHANDEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_VORHANDEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BELEG_VORHANDEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BELEG_VORHANDEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BETRAG_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_KOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BETRAG_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BETRAG_KOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BETRAG_KOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_KOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRAG_KOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_KOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRAG_KOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_KOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BETRAG_KOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BETRAG_KOSTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_BELEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BELEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_BELEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_BELEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BELEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BELEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_BELEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BELEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_BELEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BELEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BELEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_BELEG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_FREMDBELEG_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEG_NUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FREMDBELEG_NUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FREMDBELEG_NUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEG_NUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEG_NUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEG_NUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEG_NUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEG_NUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEG_NUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEG_NUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FREMDBELEG_NUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FUKO_TYP__KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUKO_TYP__KANN_WEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FUKO_TYP__KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FUKO_TYP__KANN_WEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FUKO_TYP__KANN_WEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUKO_TYP__KANN_WEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUKO_TYP__KANN_WEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUKO_TYP__KANN_WEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FUKO_TYP__KANN_WEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUKO_TYP__KANN_WEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FUKO_TYP__KANN_WEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FUKO_TYP__KANN_WEG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_FUHREN_KOSTEN_TYP", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_KOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_KOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_KOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_KOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_KOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_KOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_KOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_KOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_KOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_KOSTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLAGENTUR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLAGENTUR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZOLLAGENTUR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZOLLAGENTUR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLAGENTUR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLAGENTUR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLAGENTUR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLAGENTUR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLAGENTUR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLAGENTUR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLAGENTUR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZOLLAGENTUR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_INFO_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_KOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_INFO_KOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("INFO_KOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_INFO_KOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_KOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_KOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_KOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_KOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_KOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_INFO_KOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("INFO_KOSTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_PREIS_PRO_TONNE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PREIS_PRO_TONNE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_PREIS_PRO_TONNE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_PREIS_PRO_TONNE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_PREIS_PRO_TONNE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PREIS_PRO_TONNE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PREIS_PRO_TONNE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PREIS_PRO_TONNE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PREIS_PRO_TONNE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PREIS_PRO_TONNE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_PREIS_PRO_TONNE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PREIS_PRO_TONNE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_RECHNUNGSBETRAG___KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_RECHNUNGSBETRAG___KANN_WEG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_RECHNUNGSBETRAG___KANN_WEG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_RECHNUNGSBETRAG___KANN_WEG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_RECHNUNGSBETRAG___KANN_WEG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_RECHNUNGSBETRAG___KANN_WEG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_RECHNUNGSBETRAG___KANN_WEG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_RECHNUNGSBETRAG___KANN_WEG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_RECHNUNGSBETRAG___KANN_WEG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_RECHNUNGSBETRAG___KANN_WEG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_RECHNUNGSBETRAG___KANN_WEG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_RECHNUNGSBETRAG___KANN_WEG", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_NAME_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SPEDITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NAME_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NAME_SPEDITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NAME_SPEDITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SPEDITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SPEDITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SPEDITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SPEDITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SPEDITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NAME_SPEDITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NAME_SPEDITION", calNewValueFormated);
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
