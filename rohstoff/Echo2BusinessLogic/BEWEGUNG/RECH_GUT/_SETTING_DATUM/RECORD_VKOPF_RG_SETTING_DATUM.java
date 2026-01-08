package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.BL_CalcZahlungsDatum;


public class RECORD_VKOPF_RG_SETTING_DATUM extends RECORD_VKOPF_RG {


	
	public RECORD_VKOPF_RG_SETTING_DATUM(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	/**
	 * rechnungs- und zahlungsdatum nach der Methode: Rechnungsdatum ist das neueste Leistungsdatum,
	 * Zahlungsdatum wird nach dem Rechnungsdatum ermittelt, wenn es > aktuelles Datum+Karenzzeit ist,
	 * sonst aktuelles Datum+Karenzzeit
	 * Rueckgabe ueber die HashMap hmErgebnisse unter den keys DRUCKDATUM und ZAHLUNGSBED_CALC_DATUM
	 * @throws myException
	 */
	public MyE2_MessageVector Suche_RechnungsDatum_und_ZahlDatum_Alternativ(HashMap<String, MyDate> hmErgebnisse) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		int iKarenzZeit = bibALL.get_RECORD_MANDANT().get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(0l).intValue();

		MyDate  oDatumNeuestesLeistungsdatum = 			new MyDate("01.01.1900");
		MyDate  oDatumJetztPlusKarenz = 				new MyDate(bibALL.get_cDateNOW()).get_DatePlusDaysAsMyDate(iKarenzZeit);
		MyDate  oDatumZahlDatNachZahlungsbedingungen = 	null;

		
		RECLIST_VPOS_RG  				rlVPOS_RG = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,true);
		
		Vector<MyDate>   			    vZahlungsZiele = new Vector<MyDate>();
		
		
		//jetzt das neueste Leistungsdatum suchen, gleichzeitig die Zahlungsbedingungen (die ersten gefundene)
		for (Entry<String, RECORD_VPOS_RG> oEntry: rlVPOS_RG.entrySet()) {
			RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();
			
			if (S.isEmpty(recVPOS_RG.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()) || S.isEmpty(recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""))) {  //sollte nicht vorkommen
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String("Eine Position hat keine Zahlungsbedingung oder kein Leistungsdatum: ",true,this.get___KETTE(bibALL.get_Vector("NAME1", "ORT")),false)));
				return oMV;
			} else {
				MyDate oDatePos = new MyDate(recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""));
				if (myDateHelper.get_Date1_GreaterEqual_Date2(oDatePos.get_cDateStandardFormat(), oDatumNeuestesLeistungsdatum.get_cDateStandardFormat())) {
					oDatumNeuestesLeistungsdatum = oDatePos;
				}
			}
			
			//jetzt die inneren positionen mit zahlungsdatum durchkalkulieren
			BL_CalcZahlungsDatum oCalZDat = new BL_CalcZahlungsDatum(
									recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""),
									recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_ZAHLTAGE_lValue(null),
									recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXMONAT_lValue(null),
									recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXTAG_lValue(null));

			vZahlungsZiele.add(new MyDate(oCalZDat.get_cDateForMask()));
		}

		//jetzt steht das neueste Leistungsdatum fest
		//jetzt sortieren
		Collections.sort(vZahlungsZiele, new Comparator<MyDate>() 
		{
			@Override
			public int compare(MyDate o1, MyDate o2) 
			{
				return o1.get_cDBFormatErgebnis().compareTo(o2.get_cDBFormatErgebnis());
			}
		});

		//und jetzt das neueste Zahlungsziel, das dann fuer alle gilt, ausser es ist kleiner als das aktuelle datum + karenzzeit
		if (vZahlungsZiele.size()==0) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String("Es wurde keine Positionen gefunden: Rechnung-ID: "+this.get_ID_VKOPF_RG_cUF())));
			return oMV;
		}
		
		oDatumZahlDatNachZahlungsbedingungen = vZahlungsZiele.get(vZahlungsZiele.size()-1);
		
		MyDate oDateEndgueltigesZahlungsDatum = oDatumJetztPlusKarenz;
		
		//jetzt vergleichen, welches Zahlungsziel genommen wird
		if (myDateHelper.get_Date1_GreaterEqual_Date2(	oDatumZahlDatNachZahlungsbedingungen.get_cDateStandardFormat(), 
														oDatumJetztPlusKarenz.get_cDateStandardFormat())) {
			oDateEndgueltigesZahlungsDatum = oDatumZahlDatNachZahlungsbedingungen;
		}

		//dafuer sorgen, dass kein Samstag oder Sonntag auf das Zahlungsziel faellt (wenn gewuenscht)
		if (bibALL.get_RECORD_MANDANT().is_KORR_ZAHLDAT_WOCHENENDE_YES()) {
			oDateEndgueltigesZahlungsDatum.set_Date_to_Monday_if_Sat_or_Sun();
		}
		
		
		hmErgebnisse.clear();
		hmErgebnisse.put(_DB.VKOPF_RG$DRUCKDATUM,  oDatumNeuestesLeistungsdatum);
		hmErgebnisse.put(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM, oDateEndgueltigesZahlungsDatum);
		
		return oMV;
	}

	




	
	
	
//	/**
//	 * prueft die situation betreffs verlaengerter fakturierungsfrist 
//	 * @author martin
//	 * @date 28.05.2019
//	 *
//	 * @param rlVPOS_RG
//	 * @return eindeutige Frist oder null (dann liegt fehler vor)
//	 * @throws myException 
//	 */
//	private Long checkFakturierungsFrist(RECLIST_VPOS_RG  rlVPOS_RG) throws myException {
//		Long ret = null;
//		
//		//kontrollvektoren,darf jeder innerhalb des durchlaufs einer rechnung nur einen wert enthalten, sonst gelten fuer EINE rechnung MEHRERE kreditversicherungen
//		VEK<Long>     	 fakturierungsFristen = 			new VEK<Long>();
//		VEK<Long>     	 idsFromKredidPos = 				new VEK<Long>();    //kontrollliste, stellt sicher, dass sich alles nur in einer versicherung abspielt
//		
//		for (Entry<String, RECORD_VPOS_RG> oEntry: rlVPOS_RG.entrySet()) {
//			RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();
//			
//			if (S.isEmpty(recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""))) {
//				throw new myException("Error: Position does not contain AUSFUEHRUNGSDATUM!!");
//			}
//			
//			//jetzt nachsehen, ob das leistungsdatum einer verlaengerten fakturierungsfrist unterliegt; dies muss fuer alle gleich sein,
//			//der VEK wird nur mit nicht vorhandenen gefuellt, so dass eine absicherung bezueglich falsch zusammengestellter fakturierungsfristen 
//			//besteht: Es darf nur innerhalb der gleichen kreditversicherung faktueriert werden 
//			Date leistDat = new MyDate(recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN("")).getDate();  
//			Rec21_adresse recAdresse = new Rec21_adresse()._fill_id(this.get_ID_ADRESSE_cUF());
//			VEK<Rec21> kreditPositionen = recAdresse.getKreditVersicherungsPositionen(leistDat).getVEK();
//			Long minTage = null;
//			Long foundId = null;
//			for (Rec21 kredPos: kreditPositionen) {
//				Rec21 kredKopf = kredPos.get_up_Rec21(KREDITVERS_KOPF.id_kreditvers_kopf);
//				Long tage = O.NN(kredKopf.getLongDbValue(KREDITVERS_KOPF.fakturierungsfrist),0l);
//				if (tage > 0l) { 			// es werden nur kreditversicherungen mit einer fakturierungsfrist betrachtet
//					if (minTage==null) {
//						minTage = tage;
//						foundId = kredPos.getId();
//					}
//					if (minTage.longValue()>tage.longValue()) {
//						minTage=tage;
//						foundId = kredPos.getId();
//					}
//				}
//			}
//			
//			//fuegt auf jeden fall in beide vektoren was ein, wenn keine frist vorliegt, dann 0
//			fakturierungsFristen._addIfNotIn(O.NN(minTage, 0l));
//			idsFromKredidPos._addIfNotIn(O.NN(foundId, 0l));
//			
//		}
//		
//		if (fakturierungsFristen.size()==1 && idsFromKredidPos.size()==1) {
//			ret = fakturierungsFristen.get(0);
//		}
//		
//		return ret;
//	}
	
	
	
	/**
 	 * rueckgabe der sql-statements fuer das schreiben der datumswerte in kopf/positionssatz 
	 * @throws myException
	 */
	public MyE2_MessageVector ErzeugeStatements_fuer_RechnungsDatum_AND_ZahlungsZiel_Alternativ(	Vector<String> 			vSQL_Stack, 
																									HashMap<String, MyDate> hmErgebnisse) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (hmErgebnisse.get(_DB.VKOPF_RG$DRUCKDATUM)==null || !hmErgebnisse.get(_DB.VKOPF_RG$DRUCKDATUM).get_bOK()) {
			throw new myException(this,"Error: NO correckt <DRUCKDATUM> in HashMAP");
		}
		
		if (hmErgebnisse.get(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM)==null || !hmErgebnisse.get(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM).get_bOK()) {
			throw new myException(this,"Error: NO correckt <ZAHLUNGSBED_CALC_DATUM> in HashMAP");
		}
		
		
		//zusatzstatement fuer die definition des waehrungsimprints
		String cBasisWaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
		String cFremdWaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
		
		if (this.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd()!=null)
		{
			cFremdWaehrung = this.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd().get_WAEHRUNGSSYMBOL_cUF_NN(bibE2.get_cBASISWAEHRUNG_SYMBOL());
		}
		
		this.set_NEW_VALUE_DRUCKDATUM(hmErgebnisse.get(_DB.VKOPF_RG$DRUCKDATUM).get_cDateStandardFormat());
		this.set_NEW_VALUE_WAEHRUNG_BASIS(cBasisWaehrung);
		this.set_NEW_VALUE_WAEHRUNG_FREMD(cFremdWaehrung);
		
		vSQL_Stack.add(this.get_SQL_UPDATE_STATEMENT(null, true));
		
		//jetzt in jede position das gleiche zahlungsdatum schreiben
		RECLIST_VPOS_RG  				rlVPOS_RG = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,true);
		for (Entry<String, RECORD_VPOS_RG> oEntry: rlVPOS_RG.entrySet()) {
			RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();
			
			recVPOS_RG.set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(hmErgebnisse.get(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM).get_cDateStandardFormat());
			vSQL_Stack.add(recVPOS_RG.get_SQL_UPDATE_STATEMENT(null,true));
		}
		
		return oMV;
	}


	
	
//	/**
//	 * rechnungs- und zahlungsdatum nach der Methode: Rechnungsdatum ist das neueste Leistungsdatum,
//	 * Zahlungsdatum wird nach dem Rechnungsdatum ermittelt, wenn es > aktuelles Datum+Karenzzeit ist,
//	 * sonst aktuelles Datum+Karenzzeit
//	 * @throws myException
//	 * @Deprecated
//	 */
//	public MyE2_MessageVector ErzeugeStatements_fuer_RechnungsDatum_AND_ZahlungsZiel_Alternativ(Vector<String> vSQL_Stack) throws myException {
//		MyE2_MessageVector oMV = new MyE2_MessageVector();
//		
//		int iKarenzZeit = bibALL.get_RECORD_MANDANT().get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(0l).intValue();
//
//		MyDate  oDatumNeuestesLeistungsdatum = 			new MyDate("01.01.1900");
//		MyDate  oDatumJetztPlusKarenz = 				new MyDate(bibALL.get_cDateNOW()).get_DatePlusDaysAsMyDate(iKarenzZeit);
//		MyDate  oDatumZahlDatNachZahlungsbedingungen = 	null;
//		
//		
//		
//		RECLIST_VPOS_RG  				rlVPOS_RG = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,true);
//		
//		Vector<MyDate>   			    vZahlungsZiele = new Vector<MyDate>();
//		
//		
//		//jetzt das neueste Leistungsdatum suchen, gleichzeitig die Zahlungsbedingungen (die ersten gefundene)
//		for (Entry<String, RECORD_VPOS_RG> oEntry: rlVPOS_RG.entrySet()) {
//			RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();
//			
//			if (S.isEmpty(recVPOS_RG.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()) || S.isEmpty(recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""))) {  //sollte nicht vorkommen
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(
//						new MyE2_String("Eine Position hat keine Zahlungsbedingung oder kein Leistungsdatum: ",true,this.get___KETTE(bibALL.get_Vector("NAME1", "ORT")),false)));
//				return oMV;
//			} else {
//				MyDate oDatePos = new MyDate(recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""));
//				if (myDateHelper.get_Date1_GreaterEqual_Date2(oDatePos.get_cDateStandardFormat(), oDatumNeuestesLeistungsdatum.get_cDateStandardFormat())) {
//					oDatumNeuestesLeistungsdatum = oDatePos;
//				}
//			}
//			
//			//jetzt die inneren positionen mit zahlungsdatum durchkalkulieren
//			BL_CalcZahlungsDatum oCalZDat = new BL_CalcZahlungsDatum(
//									recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""),
//									recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_ZAHLTAGE_lValue(null),
//									recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXMONAT_lValue(null),
//									recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXTAG_lValue(null));
//
//			//hier wird erstmal das innere zahlungsziel geschrieben fuer jede einzelne position
//			//damit ein Schreibzugriff fuer die rechnungsposition im datenbanktrigger auftaucht. diese werte werden unten nochmals global ueberschrieben
//			//ueber alle positionen einer rechnung
//			recVPOS_RG.set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(oCalZDat.get_cDateForMask());
//			vSQL_Stack.add(recVPOS_RG.get_SQL_UPDATE_STATEMENT(null,true));
//
//			
//			vZahlungsZiele.add(new MyDate(oCalZDat.get_cDateForMask()));
//		}
//
//		//jetzt steht das neueste Leistungsdatum fest
//		//jetzt sortieren
//		Collections.sort(vZahlungsZiele, new Comparator<MyDate>() 
//		{
//			@Override
//			public int compare(MyDate o1, MyDate o2) 
//			{
//				return o1.get_cDBFormatErgebnis().compareTo(o2.get_cDBFormatErgebnis());
//			}
//		});
//
//		//und jetzt das neueste Zahlungsziel, das dann fuer alle gilt, ausser es ist kleiner als das aktuelle datum + karenzzeit
//		if (vZahlungsZiele.size()==0) {
//			oMV.add_MESSAGE(new MyE2_Alarm_Message(
//					new MyE2_String("Es wurde keine Positionen gefunden: Rechnung-ID: "+this.get_ID_VKOPF_RG_cUF())));
//			return oMV;
//		}
//		
//		oDatumZahlDatNachZahlungsbedingungen = vZahlungsZiele.get(vZahlungsZiele.size()-1);
//		
//		MyDate oDateEndgueltigesZahlungsDatum = oDatumJetztPlusKarenz;
//		
//		//jetzt vergleichen, welches Zahlungsziel genommen wird
//		if (myDateHelper.get_Date1_GreaterEqual_Date2(	oDatumZahlDatNachZahlungsbedingungen.get_cDateStandardFormat(), 
//														oDatumJetztPlusKarenz.get_cDateStandardFormat())) {
//			oDateEndgueltigesZahlungsDatum = oDatumZahlDatNachZahlungsbedingungen;
//		}
//
//		//dafuer sorgen, dass kein Samstag oder Sonntag auf das Zahlungsziel faellt
//		oDateEndgueltigesZahlungsDatum.set_Date_to_Monday_if_Sat_or_Sun();
//		
//		//jetzt statements sammeln
//		vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_RG SET DRUCKDATUM="+
//									oDatumNeuestesLeistungsdatum.get_cDBFormatErgebnis_4_SQLString()+" WHERE ID_VKOPF_RG="+this.get_ID_VKOPF_RG_cUF());
//		vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_VPOS_RG SET ZAHLUNGSBED_CALC_DATUM ="+ oDateEndgueltigesZahlungsDatum.get_cDBFormatErgebnis_4_SQLString()+
//									" WHERE JT_VPOS_RG.ID_VKOPF_RG="+this.get_ID_VKOPF_RG_cUF());
//		
//		//zusatzstatement fuer die definition des waehrungsimprints
//		String cBasisWaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
//		String cFremdWaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
//		
//		if (this.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd()!=null)
//		{
//			cFremdWaehrung = this.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd().get_WAEHRUNGSSYMBOL_cUF_NN(bibE2.get_cBASISWAEHRUNG_SYMBOL());
//		}
//		
//		this.set_NEW_VALUE_WAEHRUNG_BASIS(cBasisWaehrung);
//		this.set_NEW_VALUE_WAEHRUNG_FREMD(cFremdWaehrung);
//		
//		vSQL_Stack.add(this.get_SQL_UPDATE_STATEMENT(null, true));
//
//		
//		//oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("soso")));
//		
//		return oMV;
//	}

	
	
}
