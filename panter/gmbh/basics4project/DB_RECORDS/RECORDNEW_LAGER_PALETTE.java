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

public class RECORDNEW_LAGER_PALETTE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_LAGER_PALETTE";
    private _TAB  tab = _TAB.lager_palette;  


	public RECORDNEW_LAGER_PALETTE() throws myException 
	{
		super("JT_LAGER_PALETTE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGER_PALETTE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_LAGER_PALETTE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_LAGER_PALETTE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGER_PALETTE.TABLENAME);
	}
	
	
	
	public RECORDNEW_LAGER_PALETTE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_LAGER_PALETTE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_LAGER_PALETTE.TABLENAME);
	}

	
	
	public RECORD_LAGER_PALETTE do_WRITE_NEW_LAGER_PALETTE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_LAGER_PALETTE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_LAGER_PALETTE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table LAGER_PALETTE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_AUSBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("AUSBUCHUNG_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRUTTOMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BRUTTOMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BRUTTOMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRUTTOMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRUTTOMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRUTTOMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BRUTTOMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNR_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BUCHUNGSNR_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CHARGENNUMMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CHARGENNUMMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CHARGENNUMMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CHARGENNUMMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CHARGENNUMMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CHARGENNUMMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CHARGENNUMMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DATUM_VERARBEITET(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DATUM_VERARBEITET", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_EINBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("EINBUCHUNG_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ENDKONTROLLE_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ENDKONTROLLE_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRESST_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEPRESST_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEPRESST_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRESST_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRESST_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRESST_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEPRESST_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_BOX(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_BOX", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_PALETTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGER_PALETTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PALETTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_PALETTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_PALETTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PALETTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PALETTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PALETTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_PALETTE", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_MATERIAL_ZUSATZINFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("MATERIAL_ZUSATZINFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NETTOMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NETTOMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOMENGE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PALETTIERT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_PALETTIERT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("PALETTIERT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PALETTIERT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PALETTIERT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PALETTIERT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("PALETTIERT_VON", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TARAMENGE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARAMENGE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TARAMENGE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TARAMENGE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARAMENGE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARAMENGE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARAMENGE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARAMENGE", calNewValueFormated);
	}
	}
