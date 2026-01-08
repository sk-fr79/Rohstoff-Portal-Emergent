package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VKOPF_RG_DRUCK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG_DRUCK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;
import rohstoff.Echo2BusinessLogic._4_ALL.BL_CalcZahlungsDatum;

public class RECORD_VKOPF_RG_ext extends RECORD_VKOPF_RG 
{

	public RECORD_VKOPF_RG_ext() throws myException 
	{
		super();
	}

	public RECORD_VKOPF_RG_ext(long lID_Unformated, MyConnection Conn)	throws myException 
	{
		super(lID_Unformated, Conn);
	}

	public RECORD_VKOPF_RG_ext(long lID_Unformated) throws myException 
	{
		super(lID_Unformated);
	}

	public RECORD_VKOPF_RG_ext(MyConnection Conn) throws myException 
	{
		super(Conn);
	}

	public RECORD_VKOPF_RG_ext(RECORD_VKOPF_RG recordOrig) 
	{
		super(recordOrig);
	}

	public RECORD_VKOPF_RG_ext(String c_ID_or_WHEREBLOCK_OR_SQL,MyConnection Conn) throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_VKOPF_RG_ext(String c_ID_or_WHEREBLOCK_OR_SQL)	throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	
	
	public  MyDate CalcZahlungsZiel(String cRechnungsDatum, String cKorrekturDatum, MyE2_MessageVector oMV) throws myException
	{
		
		//zuerst pruefen
		boolean bOK = true;
		MyDate  oDateTest = new MyDate(cRechnungsDatum);
		if (!oDateTest.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			//das erste datum MUSS vorhanden sein und korrekt
			bOK=false;
		}
		if (bOK && S.isFull(cKorrekturDatum))
		{
			MyDate  oDateTest2 = new MyDate(cKorrekturDatum);
			if (!oDateTest2.get_cErrorCODE().equals(MyDate.ALL_OK))
			{
				//das erste datum MUSS vorhanden sein und korrekt
				bOK=false;
			}
		}
		
		if (!bOK)
		{
			if (oMV!=null)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Falsche Datumsangabe !!"));
			}
			return null;
		}
		
		
		if (this.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,true).size()==0)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Rechnung ohne Position: ",true,this.get___KETTE(bibALL.get_Vector("NAME1", "ORT")),false)));
			return null;
		}
		
		
				
		
		
		
		Vector<MyDate>  vDatumSort = new Vector<MyDate>();
		
		for (Map.Entry<String, RECORD_VPOS_RG> oEntry: this.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,true).entrySet())
		{
			if (oEntry.getValue().is_DELETED_NO())
			{
				RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();
				
				if (bibALL.isEmpty(recVPOS_RG.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Position hat keine Zahlungsbedingung : ",true,this.get___KETTE(bibALL.get_Vector("NAME1", "ORT")),false)));
					return null;
				}

				//leistungs- und rechnungsdatum besorgen
				String cRechnungsDruckDatum = 	cRechnungsDatum;    //rechnungsdatum

				//datum, was benutzt wird, um den zahlungstag zu berechnen
				String cDatumFuerBerechnung = recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF(); 

				if (bibALL.isEmpty(cDatumFuerBerechnung))
				{
					cDatumFuerBerechnung = cRechnungsDruckDatum;
				}

				//nachsehen, ob eine ID_Zahlungsbed. vorhanden ist
				if (!bibALL.isEmpty(recVPOS_RG.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
				{
					if (recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().is_ZAHLDAT_CALC_RECHDAT_YES())
					{
						cDatumFuerBerechnung = cRechnungsDruckDatum;    //dann gibt es in dieser Zahlungsbedingung die vorgabe, dass die Berechung des zahlungsziels ab dem rechnungsdatum erfolgen soll
					}
				}

				//wenn in das textfeld this.oDatumLeistung ein ueberstimmendes leistungsdatum eingetragen wurde,
				//dann wird das genommen
				if (S.isFull(cKorrekturDatum))
				{
					cDatumFuerBerechnung = cKorrekturDatum;
				}
				
				
				//jetzt die inneren positionen mit zahlungsdatum durchkalkulieren
				BL_CalcZahlungsDatum oCalZDat = new BL_CalcZahlungsDatum(
										cDatumFuerBerechnung,
										recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_ZAHLTAGE_lValue(null),
										recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXMONAT_lValue(null),
										recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXTAG_lValue(null));

				//recVPOS_RG.set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(oCalZDat.get_cDateForMask());
				vDatumSort.add(new MyDate(oCalZDat.get_cDateForMask()));
			}
		}

		//jetzt sortieren
		Collections.sort(vDatumSort, new Comparator<MyDate>() 
				{

					@Override
					public int compare(MyDate o1, MyDate o2) 
					{
						return o1.get_cDBFormatErgebnis().compareTo(o2.get_cDBFormatErgebnis());
					}
				});
		
		
		MyDate oDateRueck = null;
		
		if (vDatumSort.size()>0)
		{
			oDateRueck = vDatumSort.get(vDatumSort.size()-1);
			if (bibALL.get_RECORD_MANDANT().is_KORR_ZAHLDAT_WOCHENENDE_YES()) {
				oDateRueck.set_Date_to_Monday_if_Sat_or_Sun();
			}
		}
		
		return oDateRueck;
	}

	
	
	//2012-08-28: methode um den endbetrag einer rechnung zu ermitteln
	public BigDecimal get_bdRECH_GUT_ENDBETRAG_BRUTTO_FW_reale_Abzuege_incl_MWST() throws myException
	{
		String cQuery = BSRG__CONST.get_SQL_4_RECH_GUT_ENDBETRAG_BRUTTO_FW_NUR_REALE_ABZUEGE(
				this.get_VORGANG_TYP_cUF_NN("unknowntyp"), this.get_ID_VKOPF_RG_cUF_NN("-1"), true);
		
		MyRECORD  oRec =  new MyRECORD("SELECT "+cQuery+" AS ENDBETRAG FROM DUAL");
		
		return oRec.get_bdValue("ENDBETRAG", new BigDecimal(0), 2);
	}
	
	
	
	
	//2012-08-28: rechnung-gutschrift auf geldeingang / geldausgang testen
	public boolean get_IST_GELDEINGANG() throws myException
	{
		BigDecimal bdEndbetrag = this.get_bdRECH_GUT_ENDBETRAG_BRUTTO_FW_reale_Abzuege_incl_MWST();
		
		boolean bRueck = true;
		
		if (this.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG) && bdEndbetrag.compareTo(BigDecimal.ZERO)<0)
		{
			bRueck = false;
		}
		else if (this.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_GUTSCHRIFT) && bdEndbetrag.compareTo(BigDecimal.ZERO)>0)
		{
			bRueck = false;
		}
		
		return bRueck;
	}
	
	
	//2012-08-28: pruefen, ob ein beleg eine komplett-storno2 (gegenbeleg) ist
	public boolean get_IST_KOMPLETT_STORNO2()  throws myException
	{
		boolean bRueck = true;
		
		RECLIST_VPOS_RG reclistVPOS = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(JT_VPOS_RG.DELETED,'N')='N'", null, true);
		
		for (RECORD_VPOS_RG recVPosRG: reclistVPOS.values())
		{
			if (S.isEmpty(recVPosRG.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")))
			{
				bRueck = false;
			}
		}
		return bRueck;
	}
	
	
	
	
	
	
	
//	/**
//	 * 2014-11-05:
//	 * @return       zu diesem RG-Datensatz den neuesten am Druckprotokoll angehaengten archiveintrag der nicht den schalter original traegt und 
//	 *	            wo der Druckprotokolleintrag nur ein archivmedium traegt
//	 * @throws myException
//	 */
//	public RECORD_ARCHIVMEDIEN  get_RECORD_ARCHIVMEDIEN_NEWEST_NOT_ORIGINAL_SINGULAR() throws myException {
//		RECLIST_VKOPF_RG_DRUCK  reclistDruck = this.get_DOWN_RECORD_LIST_VKOPF_RG_DRUCK_id_vkopf_rg(
//				"NVL("+_DB.VKOPF_RG_DRUCK$IST_ORIGINAL+",'N')='N'","ID_VKOPF_RG_DRUCK DESC",true);
//		
//		RECORD_ARCHIVMEDIEN recFound = null;
//		
//		if (reclistDruck!=null && reclistDruck.get_vKeyValues().size()>0)
//		{
//			//reclist von neu zu alt durchsuchen
//			for (int i=0;i<reclistDruck.get_vKeyValues().size();i++) {
//				RECORD_VKOPF_RG_DRUCK recDruck = reclistDruck.get(i);
//				
//				//kann eingesetzt werden fuer: JT_VKOPF_RG_DRUCK/JT_VKOPF_STD_DRUCK/JT_VKOPF_TPA_DRUCK/JT_VKOPF_KON_DRUCK/JT_VPOS_TPA_FUHRE_DRUCK
//				String cTableREF_ARCH = "VKOPF_RG_DRUCK";
//				String cTableREF_ID   = recDruck.get_ID_VKOPF_RG_DRUCK_cUF();
//
//				RECLIST_ARCHIVMEDIEN  recArch = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE "+
//						" JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+" AND "+
//						" JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID+" AND"+
//						" NVL("+_DB.ARCHIVMEDIEN$IST_ORIGINAL+",'N')='N'");
//				
//				if (recArch.get_vKeyValues().size()==1) {
//					recFound = recArch.get(0);
//					break;
//				}
//			}
//			
//		}	
//		
//		return recFound;
//
//	}
	
	
	
	/**
	 * 2015-06-11: da es ab jetzt nicht nur RG-Belege, sondern auch kontenblaetter im Archiv gibt, muss die methode geaendert werden
	 * 
	 * 2014-11-05:
	 * @param  onlyFirstArchivRecord when true, then only record with lowest id ist returned
	 * @return       zu diesem RG-Datensatz vom neuesten Druckprotokoll die angehaengten archivmedien in der reihenfolge der ablage
	 * @throws myException
	 */
	public Vector<RECORD_ARCHIVMEDIEN>  get_RECORD_ARCHIVMEDIEN_NEWEST_NOT_ORIGINAL(boolean onlyFirstArchivRecord) throws myException {
		RECLIST_VKOPF_RG_DRUCK  reclistDruck = this.get_DOWN_RECORD_LIST_VKOPF_RG_DRUCK_id_vkopf_rg(
				"NVL("+_DB.VKOPF_RG_DRUCK$IST_ORIGINAL+",'N')='N'","ID_VKOPF_RG_DRUCK DESC",true);
		
		Vector<RECORD_ARCHIVMEDIEN> v_recFound = new Vector<RECORD_ARCHIVMEDIEN>();
		
		if (reclistDruck!=null && reclistDruck.get_vKeyValues().size()>0) {
			
			RECORD_VKOPF_RG_DRUCK recDruck = reclistDruck.get(0);    //der neueste
			
			String cTableREF_ARCH = _DB.VKOPF_RG_DRUCK.substring(3);
			String cTableREF_ID   = recDruck.get_ID_VKOPF_RG_DRUCK_cUF();

			RECLIST_ARCHIVMEDIEN  recArch = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE "+
					" JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+
					" AND JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID+
					" AND NVL("+_DB.ARCHIVMEDIEN$IST_ORIGINAL+",'N')='N' "+
					" ORDER BY "+_DB.ARCHIVMEDIEN$ID_ARCHIVMEDIEN+" ASC"						
					);

			for (String id_archivmedien: recArch.get_vKeyValues()) {
				v_recFound.add(recArch.get(id_archivmedien));
				if (onlyFirstArchivRecord) {
					break;
				}
			}
		}	
		return v_recFound;
	}
	
	
	
	


	
	
}
