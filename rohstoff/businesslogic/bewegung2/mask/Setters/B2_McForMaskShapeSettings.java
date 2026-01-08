/**
 * rohstoff.businesslogic.bewegung2.mask.Setters
 * @author martin
 * @date 09.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Setters;

import nextapp.echo2.app.Font;
import panter.gmbh.BasicInterfaces.Service.PdServiceCheckIfSorteIsRC;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_labInGridNoDatabase;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.json.EnumJsonFileNames;
import panter.gmbh.json.reader.JsonMaskTransportArtSettingReader;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.global.B2_InfoBlockFremdwaehrung;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.global.EnumMaskSonderLabel;
import rohstoff.businesslogic.bewegung2.global.TK;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_SearchArtbezV2;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_McForMaskShapeSettings extends B2_MaskController {

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_component
	 * @throws myException
	 */
	public B2_McForMaskShapeSettings(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_componentMap
	 * @throws myException
	 */
	public B2_McForMaskShapeSettings(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}


	
	

	public B2_McForMaskShapeSettings(IF_RB_Component p_component, MyE2_MessageVector mv) {
		super(p_component, mv);
	}

	public B2_McForMaskShapeSettings(RB_ComponentMap p_componentMap, MyE2_MessageVector mv) {
		super(p_componentMap, mv);
	}

	/**
	 * routine fuer die einstellungen der oberflaeche (z.B.
	 * enabled/disabled-settings oder beschriftungen, die keine daten veraendern 
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @return
	 */
	public B2_McForMaskShapeSettings _setAllMaskShapeSettings() {
		
		//zuerst die calls, die felder editierbar machen
		
		try {
			
			//this._clearAllErrorMarkers();
			
			this._setAllFieldsEnabled(false,true);

			//werte auf anfang
			
			if (this.isSaveable()) {
				Rec21_bgVector recV=this.getVector();
				
				if (recV==null) {
					throw new myException("Error reading Record-Vector ");
				} else {
						
					if (recV.is_newRecordSet() || recV.isNative()) {
						this._setAllFieldsEnabled(true, true);
						
						//evtl. hightlights weg
						this._setNormalColorInGridAllComponents();
						
						this._setDisabledFieldsWithTransportArt();
						this._setDisabledLieferBedingungZusatzIfLieferbedingungIsEmpty();
						this._setDisabledZahlungsBedingungZusatzIfZahlungsbedingungIsEmpty();
						
						this._setDisabledTransportMittelFollowFieldsIfTransportmittelIsEmpty();
						this._setDisabledKontraktOrAngeboteViceVersa();
						this._disableWaehrungWhenKontraktOrAngebotIsPresent();
						this._disableSortenAngebotAndKontrakt();
						this._disableAngebotAndKontraktWhenNotAllowed();
						
						this._disableZusatzLieferbedingungen();
						this._disableZusatzZahlungsbedingungen();
						
						this._disableFieldsWhenMengeOrPreisClosed();
						this._disableFremdAdresseIfNotNeeded();
						
						this._disableTransportmittelFollowFields();
					}
				}
			}
			
			
			this._setRcMarker(EnPositionStation.LEFT);
			this._setRcMarker(EnPositionStation.RIGHT);
			this._setMengenEinheiten();
			this._setTitleLables();
			this._setFremdWahrungsInfo();
			this._setFieldLabels();
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <b32c5b94-5945-11ea-8e2d-0242ac130003-1>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <b32c5b94-5945-11ea-8e2d-0242ac130003-1>");
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 14.01.2020
	 *
	 * @param enabled
	 * @param includeTransportTyp
	 * @return
	 */
	private B2_McForMaskShapeSettings _setAllFieldsEnabled(boolean enabled, boolean includeTransportTyp) throws Exception {
		
		VEK<IF_Field> fieldsVector = new VEK<>();
		VEK<IF_Field> fieldsS1 = new VEK<>();
		VEK<IF_Field> fieldsS2 = new VEK<>();
		VEK<IF_Field> fieldsS3 = new VEK<>();
		VEK<IF_Field> fieldsA1 = new VEK<>();
		VEK<IF_Field> fieldsA2 = new VEK<>();

		if (includeTransportTyp) {
			fieldsVector._a(BG_VEKTOR.en_transport_typ);
		}

		fieldsVector._a(BG_VEKTOR.id_bg_vektor);
		fieldsVector._a(BG_VEKTOR.id_bg_storno_info);
		fieldsVector._a(BG_VEKTOR.id_bg_del_info);
		fieldsVector._a(BG_VEKTOR.posnr);
		fieldsVector._a(BG_VEKTOR.timestamp_in_mask);
		fieldsVector._a(BG_VEKTOR.lieferinfo_tpa);
		fieldsVector._a(BG_VEKTOR.transportverantwortung);

		fieldsVector._a(BG_VEKTOR.id_adresse_fremdware);
		fieldsVector._a(BG_VEKTOR.id_adresse_logi_start);
		fieldsVector._a(BG_VEKTOR.id_adresse_logi_ziel);
		fieldsVector._a(BG_VEKTOR.id_handelsdef);
		fieldsVector._a(BG_VEKTOR.kosten_transport);

		fieldsVector._a(BG_VEKTOR.buchungsnummer);
		fieldsVector._a(BG_VEKTOR.datum_planung_von);
		fieldsVector._a(BG_VEKTOR.datum_planung_bis);
		fieldsVector._a(BG_VEKTOR.ek_vk_zuord_zwang);
		fieldsVector._a(BG_VEKTOR.ah7_menge);
		fieldsVector._a(BG_VEKTOR.ah7_volumen);
		fieldsVector._a(BG_VEKTOR.ah7_ausstellung_datum);
		fieldsVector._a(BG_VEKTOR.print_anhang7);
		fieldsVector._a(BG_VEKTOR.id_adresse_spedition);
		fieldsVector._a(BG_VEKTOR.transportmittel);
		fieldsVector._a(BG_VEKTOR.id_transportmittel);
		fieldsVector._a(BG_VEKTOR.id_uma_kontrakt);
		fieldsVector._a(BG_VEKTOR.ordnungsnummer_cmr);
		fieldsVector._a(BG_VEKTOR.planmenge);
		fieldsVector._a(BG_VEKTOR.transportkennzeichen);
		fieldsVector._a(BG_VEKTOR.anhaengerkennzeichen);
		fieldsVector._a(BG_VEKTOR.bemerkung);
		fieldsVector._a(BG_VEKTOR.bemerkung_fuer_kunde);
		fieldsVector._a(BG_VEKTOR.bemerkung_sachbearbeiter);
		fieldsVector._a(BG_VEKTOR.en_vektor_quelle);
		fieldsVector._a(BG_VEKTOR.en_vektor_status);
		fieldsVector._a(BG_VEKTOR.id_bg_pruefprot_abschluss);
		fieldsVector._a(BG_VEKTOR.id_verpackungsart);
		fieldsVector._a(BG_VEKTOR.id_land_transit1);
		fieldsVector._a(BG_VEKTOR.id_land_transit2);
		fieldsVector._a(BG_VEKTOR.id_land_transit3);
		fieldsVector._a(BG_VEKTOR.ek_vk_sorte_lock);

		fieldsS1._a(BG_STATION.id_bg_station);
		fieldsS1._a(BG_STATION.id_bg_vektor);
		fieldsS1._a(BG_STATION.id_land);
		fieldsS1._a(BG_STATION.id_adresse);
		fieldsS1._a(BG_STATION.id_adresse_basis);
		fieldsS1._a(BG_STATION.id_adresse_besitz_ldg);
		fieldsS1._a(BG_STATION.id_wiegekarte);
		fieldsS1._a(BG_STATION.id_bg_storno_info);
		fieldsS1._a(BG_STATION.id_bg_del_info);
		fieldsS1._a(BG_STATION.wiegekartenkenner);
		fieldsS1._a(BG_STATION.kontrollmenge);
		fieldsS1._a(BG_STATION.name1);
		fieldsS1._a(BG_STATION.name2);
		fieldsS1._a(BG_STATION.name3);
		fieldsS1._a(BG_STATION.strasse);
		fieldsS1._a(BG_STATION.hausnummer);
		fieldsS1._a(BG_STATION.plz);
		fieldsS1._a(BG_STATION.ort);
		fieldsS1._a(BG_STATION.ortzusatz);
		fieldsS1._a(BG_STATION.oeffnungszeiten);
		fieldsS1._a(BG_STATION.telefon);
		fieldsS1._a(BG_STATION.fax);
		fieldsS1._a(BG_STATION.timestamp_in_mask);
		fieldsS1._a(BG_STATION.pos_in_mask);
		fieldsS1._a(BG_STATION.umsatzsteuerlkz);
		fieldsS1._a(BG_STATION.umsatzsteuerid);

		fieldsS2._a(BG_STATION.id_bg_station);
		fieldsS2._a(BG_STATION.id_bg_vektor);
		fieldsS2._a(BG_STATION.id_land);
		fieldsS2._a(BG_STATION.id_adresse);
		fieldsS2._a(BG_STATION.id_adresse_basis);
		fieldsS2._a(BG_STATION.id_adresse_besitz_ldg);
		fieldsS2._a(BG_STATION.id_wiegekarte);
		fieldsS2._a(BG_STATION.id_bg_storno_info);
		fieldsS2._a(BG_STATION.id_bg_del_info);
		fieldsS2._a(BG_STATION.wiegekartenkenner);
		fieldsS2._a(BG_STATION.kontrollmenge);
		fieldsS2._a(BG_STATION.name1);
		fieldsS2._a(BG_STATION.name2);
		fieldsS2._a(BG_STATION.name3);
		fieldsS2._a(BG_STATION.strasse);
		fieldsS2._a(BG_STATION.hausnummer);
		fieldsS2._a(BG_STATION.plz);
		fieldsS2._a(BG_STATION.ort);
		fieldsS2._a(BG_STATION.ortzusatz);
		fieldsS2._a(BG_STATION.oeffnungszeiten);
		fieldsS2._a(BG_STATION.telefon);
		fieldsS2._a(BG_STATION.fax);
		fieldsS2._a(BG_STATION.timestamp_in_mask);
		fieldsS2._a(BG_STATION.pos_in_mask);
		fieldsS2._a(BG_STATION.umsatzsteuerlkz);
		fieldsS2._a(BG_STATION.umsatzsteuerid);

		
		fieldsS3._a(BG_STATION.id_bg_station);
		fieldsS3._a(BG_STATION.id_bg_vektor);
		fieldsS3._a(BG_STATION.id_land);
		fieldsS3._a(BG_STATION.id_adresse);
		fieldsS3._a(BG_STATION.id_adresse_basis);
		fieldsS3._a(BG_STATION.id_adresse_besitz_ldg);
		fieldsS3._a(BG_STATION.id_wiegekarte);
		fieldsS3._a(BG_STATION.id_bg_storno_info);
		fieldsS3._a(BG_STATION.id_bg_del_info);
		fieldsS3._a(BG_STATION.wiegekartenkenner);
		fieldsS3._a(BG_STATION.kontrollmenge);
		fieldsS3._a(BG_STATION.name1);
		fieldsS3._a(BG_STATION.name2);
		fieldsS3._a(BG_STATION.name3);
		fieldsS3._a(BG_STATION.strasse);
		fieldsS3._a(BG_STATION.hausnummer);
		fieldsS3._a(BG_STATION.plz);
		fieldsS3._a(BG_STATION.ort);
		fieldsS3._a(BG_STATION.ortzusatz);
		fieldsS3._a(BG_STATION.oeffnungszeiten);
		fieldsS3._a(BG_STATION.telefon);
		fieldsS3._a(BG_STATION.fax);
		fieldsS3._a(BG_STATION.timestamp_in_mask);
		fieldsS3._a(BG_STATION.pos_in_mask);
		fieldsS3._a(BG_STATION.umsatzsteuerlkz);
		fieldsS3._a(BG_STATION.umsatzsteuerid);

		fieldsA1._a(BG_ATOM.id_bg_atom);
		fieldsA1._a(BG_ATOM.id_bg_vektor);
		fieldsA1._a(BG_ATOM.id_bg_station_quelle);
		fieldsA1._a(BG_ATOM.id_bg_station_ziel);
		fieldsA1._a(BG_ATOM.id_bg_rech_block);
		fieldsA1._a(BG_ATOM.lieferbedingungen);
		fieldsA1._a(BG_ATOM.id_lieferbedingungen);
		fieldsA1._a(BG_ATOM.zahlungsbedingungen);
		fieldsA1._a(BG_ATOM.id_zahlungsbedingungen);
		fieldsA1._a(BG_ATOM.id_zolltarifnummer);
		fieldsA1._a(BG_ATOM.id_artikel);
		fieldsA1._a(BG_ATOM.id_artikel_bez);
		fieldsA1._a(BG_ATOM.id_vpos_kon);
		fieldsA1._a(BG_ATOM.id_vpos_std);
		fieldsA1._a(BG_ATOM.id_tax);
		fieldsA1._a(BG_ATOM.id_waehrung);
		fieldsA1._a(BG_ATOM.id_bg_pruefprot_menge);
		fieldsA1._a(BG_ATOM.id_bg_pruefprot_preis);
		fieldsA1._a(BG_ATOM.id_bg_pruefprot_abschluss);
		fieldsA1._a(BG_ATOM.id_bg_storno_info);
		fieldsA1._a(BG_ATOM.id_bg_del_info);
		fieldsA1._a(BG_ATOM.waehrungskurs);
		fieldsA1._a(BG_ATOM.manuell_preis);
		fieldsA1._a(BG_ATOM.e_preis_basiswaehrung);
		fieldsA1._a(BG_ATOM.e_preis_fremdwaehrung);
		fieldsA1._a(BG_ATOM.e_preis_res_netto_mge_basis);
		fieldsA1._a(BG_ATOM.e_preis_res_netto_mge_fremd);
		fieldsA1._a(BG_ATOM.g_preis_basiswaehrung);
		fieldsA1._a(BG_ATOM.g_preis_fremdwaehrung);
		fieldsA1._a(BG_ATOM.g_preis_abzug_basis);
		fieldsA1._a(BG_ATOM.g_preis_abzug_fremd);
		fieldsA1._a(BG_ATOM.datum_ausfuehrung);
		fieldsA1._a(BG_ATOM.menge);
		fieldsA1._a(BG_ATOM.menge_abzug);
		fieldsA1._a(BG_ATOM.menge_verteilung);
		fieldsA1._a(BG_ATOM.menge_netto);
		fieldsA1._a(BG_ATOM.menge_abrech);
		fieldsA1._a(BG_ATOM.anr1);
		fieldsA1._a(BG_ATOM.anr2);
		fieldsA1._a(BG_ATOM.artbez1);
		fieldsA1._a(BG_ATOM.artbez2);
		fieldsA1._a(BG_ATOM.kontraktzwang);
		fieldsA1._a(BG_ATOM.postennummer);
		fieldsA1._a(BG_ATOM.eu_steuer_vermerk);
		fieldsA1._a(BG_ATOM.bemerkung_extern);
		fieldsA1._a(BG_ATOM.bemerkung_intern);
		fieldsA1._a(BG_ATOM.timestamp_in_mask);
		fieldsA1._a(BG_ATOM.kosten_produkt);
		fieldsA1._a(BG_ATOM.steuersatz);
		fieldsA1._a(BG_ATOM.notifikation_nr);
		fieldsA1._a(BG_ATOM.nationaler_abfall_code);
		fieldsA1._a(BG_ATOM.bestellnummer);
		fieldsA1._a(BG_ATOM.pos_in_mask);
		fieldsA1._a(BG_ATOM.id_eak_code);
		fieldsA1._a(BG_ATOM.intrastat_meld);
		fieldsA1._a(BG_ATOM.transit_meld);
		fieldsA1._a(BG_ATOM.eu_vertrag_check);
		fieldsA1._a(BG_ATOM.id_bg_pruefport_gelangensbest);

		fieldsA2._a(BG_ATOM.id_bg_atom);
		fieldsA2._a(BG_ATOM.id_bg_vektor);
		fieldsA2._a(BG_ATOM.id_bg_station_quelle);
		fieldsA2._a(BG_ATOM.id_bg_station_ziel);
		fieldsA2._a(BG_ATOM.id_bg_rech_block);
		fieldsA2._a(BG_ATOM.lieferbedingungen);
		fieldsA2._a(BG_ATOM.id_lieferbedingungen);
		fieldsA2._a(BG_ATOM.zahlungsbedingungen);
		fieldsA2._a(BG_ATOM.id_zahlungsbedingungen);
		fieldsA2._a(BG_ATOM.id_zolltarifnummer);
		fieldsA2._a(BG_ATOM.id_artikel);
		fieldsA2._a(BG_ATOM.id_artikel_bez);
		fieldsA2._a(BG_ATOM.id_vpos_kon);
		fieldsA2._a(BG_ATOM.id_vpos_std);
		fieldsA2._a(BG_ATOM.id_tax);
		fieldsA2._a(BG_ATOM.id_waehrung);
		fieldsA2._a(BG_ATOM.id_bg_pruefprot_menge);
		fieldsA2._a(BG_ATOM.id_bg_pruefprot_preis);
		fieldsA2._a(BG_ATOM.id_bg_pruefprot_abschluss);
		fieldsA2._a(BG_ATOM.id_bg_storno_info);
		fieldsA2._a(BG_ATOM.id_bg_del_info);
		fieldsA2._a(BG_ATOM.waehrungskurs);
		fieldsA2._a(BG_ATOM.manuell_preis);
		fieldsA2._a(BG_ATOM.e_preis_basiswaehrung);
		fieldsA2._a(BG_ATOM.e_preis_fremdwaehrung);
		fieldsA2._a(BG_ATOM.e_preis_res_netto_mge_basis);
		fieldsA2._a(BG_ATOM.e_preis_res_netto_mge_fremd);
		fieldsA2._a(BG_ATOM.g_preis_basiswaehrung);
		fieldsA2._a(BG_ATOM.g_preis_fremdwaehrung);
		fieldsA2._a(BG_ATOM.g_preis_abzug_basis);
		fieldsA2._a(BG_ATOM.g_preis_abzug_fremd);
		fieldsA2._a(BG_ATOM.datum_ausfuehrung);
		fieldsA2._a(BG_ATOM.menge);
		fieldsA2._a(BG_ATOM.menge_abzug);
		fieldsA2._a(BG_ATOM.menge_verteilung);
		fieldsA2._a(BG_ATOM.menge_netto);
		fieldsA2._a(BG_ATOM.menge_abrech);
		fieldsA2._a(BG_ATOM.anr1);
		fieldsA2._a(BG_ATOM.anr2);
		fieldsA2._a(BG_ATOM.artbez1);
		fieldsA2._a(BG_ATOM.artbez2);
		fieldsA2._a(BG_ATOM.kontraktzwang);
		fieldsA2._a(BG_ATOM.postennummer);
		fieldsA2._a(BG_ATOM.eu_steuer_vermerk);
		fieldsA2._a(BG_ATOM.bemerkung_extern);
		fieldsA2._a(BG_ATOM.bemerkung_intern);
		fieldsA2._a(BG_ATOM.timestamp_in_mask);
		fieldsA2._a(BG_ATOM.kosten_produkt);
		fieldsA2._a(BG_ATOM.steuersatz);
		fieldsA2._a(BG_ATOM.notifikation_nr);
		fieldsA2._a(BG_ATOM.nationaler_abfall_code);
		fieldsA2._a(BG_ATOM.bestellnummer);
		fieldsA2._a(BG_ATOM.pos_in_mask);
		fieldsA2._a(BG_ATOM.id_eak_code);
		fieldsA2._a(BG_ATOM.intrastat_meld);
		fieldsA2._a(BG_ATOM.transit_meld);
		fieldsA2._a(BG_ATOM.id_bg_pruefport_gelangensbest);

		this._setEnabledForEdit(enabled, RecV.key, fieldsVector.getArray());
		this._setEnabledForEdit(enabled, RecA1.key, fieldsA1.getArray());
		this._setEnabledForEdit(enabled, RecA2.key, fieldsA2.getArray());
		this._setEnabledForEdit(enabled, RecS1.key, fieldsS1.getArray());
		this._setEnabledForEdit(enabled, RecS2.key, fieldsS2.getArray());
		this._setEnabledForEdit(enabled, RecS3.key, fieldsS3.getArray());

		return this;
	}	
	
	
	
	private B2_McForMaskShapeSettings _setDisabledFieldsWithTransportArt()  throws Exception {
		
		VEK<RB_KM> keysIterate = new VEK<RB_KM>()._a(RecV.key,RecA1.key,RecA2.key,RecS1.key,RecS2.key,RecS3.key);
		EnTransportTyp art = EnTransportTyp.WA.getEnum(this.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ));
		JsonMaskTransportArtSettingReader helper = null;
		try {
			helper = new JsonMaskTransportArtSettingReader();
		} catch (Exception e) {
			e.printStackTrace();
			bibMSG.MV()._addWarn("Error reading mask-setting-json-file: "+EnumJsonFileNames.B2MaskTransportArtSetting.getFileName()+", "+e.getLocalizedMessage());
		}

		
		if (art==null) {
			
			this._setAllFieldsEnabled(false,false);
			
			
		} else {
			
			
			//jetzt den json-setter loslassen
			if (helper!=null) {
				for (RB_KM k: keysIterate) {
					if (helper.getFieldsToEnable(art,k)!=null) {
						this._setEnabledForEdit(true, k,helper.getFieldsToEnable(art,k));
					}
					if (helper.getFieldsToDisable(art, k)!=null) {
						this._setEnabledForEdit(false, k,helper.getFieldsToDisable(art, k));
					}
				}
			}				

			
			//jetzt evtl. textlabels aus der Vector-MAP rausziehen (ohen sonderlabels)
			RB_ComponentMap mapVektor = this.get_ComponentMapCollector().get(RecV.key);
			HMAP<TK,RB_labInGridNoDatabase>  allFieldLables = new HMAP<>();
			for (RB_KF keyField : mapVektor.getHmOnlyRbComponents().keySet()) {
				if (keyField instanceof TK) {
					TK tKeyField = (TK)keyField;
					if (!tKeyField.isSonderLabel() &&  mapVektor.getHmOnlyRbComponents().get(tKeyField)!=null &&  mapVektor.getHmOnlyRbComponents().get(tKeyField) instanceof RB_labInGridNoDatabase) {
						allFieldLables._put(tKeyField, (RB_labInGridNoDatabase)mapVektor.getHmOnlyRbComponents().get(tKeyField));
					}
				}
			}
			//dann alle style normal
			for (RB_labInGridNoDatabase lab: allFieldLables.values()) {
				lab._setStyleNormal();
			}
			//die aus der json zum in den hintergrund zu stellenden, absoften
			VEK<TK> keyOfLabelsToHide = helper.getMaskLablesToDisable(art);
			for (TK keyToHide: keyOfLabelsToHide) {
				if (allFieldLables.containsKey(keyToHide)) {
					allFieldLables.get(keyToHide)._setStyleLow();
				}
			}
			
			
			//dann sonderfaelle
			
			switch (art) {
			case WA:
				break;
				
			case WA_L:    //verkauf bereits im lager befindlicher fremdware
				break;
				
			case WE:
				break;
				
			case WE_L:
				break;
				
				
			case STRECKE:
				break;
				
			case LAGER_LAGER:
				break;

			case LEERGUTRANSPORT: 
				break;
				
				
			case TESTSTELLUNG: 
				break;
				
			case FREMDWARENTRANSPORT: 
				break;
				
				
			case EINBUCHUNG:
				break;
				
			case AUSBUCHUNG:
				break;
				
			case UMBUCHUNG:
				break;
				
			default:
				break;
			}
			

		}

		
		return this;
	}
	
	

	

	
	
	


	
	
	
	
	public B2_McForMaskShapeSettings _setDisabledLieferBedingungZusatzIfLieferbedingungIsEmpty()  throws Exception {
		Long idLieferBedingungEK = this.getLongLiveVal(RecA1.key,BG_ATOM.id_lieferbedingungen.fk());
		Long idLieferBedingungVK = this.getLongLiveVal(RecA2.key,BG_ATOM.id_lieferbedingungen.fk());
		if (idLieferBedingungEK==null) {
			this._setEnabledForEdit(false, RecA1.key, BG_ATOM.lieferbedingungen);
		}
		if (idLieferBedingungVK==null) {
			this._setEnabledForEdit(false, RecA2.key, BG_ATOM.lieferbedingungen);
		}
		return this;
	}

	
	
	public B2_McForMaskShapeSettings _setDisabledZahlungsBedingungZusatzIfZahlungsbedingungIsEmpty()  throws Exception {
		Long idZahlungsBedingungEK = this.getLongLiveVal(RecA1.key,BG_ATOM.id_zahlungsbedingungen.fk());
		Long idZahlungsBedingungVK = this.getLongLiveVal(RecA2.key,BG_ATOM.id_zahlungsbedingungen.fk());
		if (idZahlungsBedingungEK==null) {
			this._setEnabledForEdit(false, RecA1.key, BG_ATOM.zahlungsbedingungen);
		}
		if (idZahlungsBedingungVK==null) {
			this._setEnabledForEdit(false, RecA2.key, BG_ATOM.zahlungsbedingungen);
		}
		return this;
	}
	
	
	
	
	public B2_McForMaskShapeSettings _setDisabledTransportMittelFollowFieldsIfTransportmittelIsEmpty() throws Exception {
		Long idTransportmittel = this.getLongLiveVal(RecV.key, BG_VEKTOR.id_transportmittel);
		
		if (O.isNull(idTransportmittel)) {
			this._setEnabledForEdit(false, RecV.key, BG_VEKTOR.transportmittel);
			this._setEnabledForEdit(false, RecV.key, BG_VEKTOR.transportkennzeichen);
			this._setEnabledForEdit(false, RecV.key, BG_VEKTOR.anhaengerkennzeichen);
		}
		return this;
	}

	
	
	public B2_McForMaskShapeSettings _setDisabledKontraktOrAngeboteViceVersa()  throws Exception{
		Long idVposKonEk = this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_kon);
		Long idVposStdEk = this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_std);

		Long idVposKonVk = this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_kon);
		Long idVposStdVk = this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_std);

		
		if (idVposKonEk!=null) {
			this._setEnabledForEdit(false, RecA1.key, BG_ATOM.id_vpos_std);
		} 
		if (idVposStdEk!=null) {
			this._setEnabledForEdit(false, RecA1.key, BG_ATOM.id_vpos_kon);
		} 
		
		if (idVposKonVk!=null) {
			this._setEnabledForEdit(false, RecA2.key, BG_ATOM.id_vpos_std);
		} 
		if (idVposStdVk!=null) {
			this._setEnabledForEdit(false, RecA2.key, BG_ATOM.id_vpos_kon);
		} 
			
		return this;
	}

	
	
	
	
//	private B2_McForMaskShapeSettings _setDisabledSteuerFieldsOnMandantSite(EnPositionStation  position)  throws Exception{
//		RB_KM mask =  (position==EnPositionStation.LEFT?RecA1.key:RecA2.key);
//		this._setEnabledForEdit(false,mask, BG_ATOM.id_tax);
//		this._setEnabledForEdit(false,mask, BG_ATOM.eu_steuer_vermerk);
//		return this;
//	}

	
	
	private B2_McForMaskShapeSettings _setMengenEinheiten()  throws Exception{
		
		((RB_labInGridNoDatabase)this.get_comp(RecV.key, TK.gen(EnumMaskSonderLabel.einheit_0_0)))._t("-");
		((RB_labInGridNoDatabase)this.get_comp(RecV.key, TK.gen(EnumMaskSonderLabel.einheit_0_1)))._t("-");
		((RB_labInGridNoDatabase)this.get_comp(RecV.key, TK.gen(EnumMaskSonderLabel.einheit_0_2)))._t("-");
		
		Long idLadeSorte = this.getLongLiveVal(RecA1.key,BG_ATOM.id_artikel_bez);
		Long idAbLadeSorte = this.getLongLiveVal(RecA1.key,BG_ATOM.id_artikel_bez);
		
		if (O.isNoOneNull(idAbLadeSorte,idLadeSorte)) {
			Rec21 recEinheitEK = new Rec21_artikel_bez()._fill_id(idLadeSorte).getEinheit();
			Rec21 recEinheitVK = new Rec21_artikel_bez()._fill_id(idAbLadeSorte).getEinheit();
			
			if (recEinheitEK.getId()==recEinheitVK.getId()) {
				((RB_labInGridNoDatabase)this.get_comp(RecV.key, TK.gen(EnumMaskSonderLabel.einheit_0_0)))._t(recEinheitEK.getUfs(EINHEIT.einheitkurz));
				((RB_labInGridNoDatabase)this.get_comp(RecV.key, TK.gen(EnumMaskSonderLabel.einheit_0_1)))._t(recEinheitEK.getUfs(EINHEIT.einheitkurz));
				((RB_labInGridNoDatabase)this.get_comp(RecV.key, TK.gen(EnumMaskSonderLabel.einheit_0_2)))._t(recEinheitEK.getUfs(EINHEIT.einheitkurz));
			}
		}
		
		
		return this;
	}
	
	
	
	
	
	private B2_McForMaskShapeSettings _setTitleLables()  throws Exception{
		
			
		EnTransportTyp art = EnTransportTyp.WA.getEnum(this.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ));

		String mandantenLabel = "Lager von "+
				bibALL.get_RECORD_MANDANT().get_NAME1_cF_NN("Mandant-Name1")+" "+bibALL.get_RECORD_MANDANT().get_NAME2_cF_NN("Mandant-Name2");

		RB_labInGridNoDatabase einleitung1 = (RB_labInGridNoDatabase)this.get_comp(RecV.key, 	TK.gen(EnumMaskSonderLabel.titel_0_0)); 
		RB_labInGridNoDatabase einleitung2 = (RB_labInGridNoDatabase)this.get_comp(RecV.key, 	TK.gen(EnumMaskSonderLabel.titel_1_0)); 
		
		RB_labInGridNoDatabase quelle1 = (RB_labInGridNoDatabase)this.get_comp(RecV.key, 	TK.gen(EnumMaskSonderLabel.titel_0_1)); 
		RB_labInGridNoDatabase quelle2 = (RB_labInGridNoDatabase)this.get_comp(RecV.key, 	TK.gen(EnumMaskSonderLabel.titel_1_1)); 
		RB_labInGridNoDatabase ziel1 = (RB_labInGridNoDatabase)this.get_comp(RecV.key, 		TK.gen(EnumMaskSonderLabel.titel_0_2)); 
		RB_labInGridNoDatabase ziel2 = (RB_labInGridNoDatabase)this.get_comp(RecV.key, 		TK.gen(EnumMaskSonderLabel.titel_1_2));  
		
		quelle1._t(S.ms("Quelle"));
		quelle2._t(S.ms("Quelle"));
		ziel1._t(S.ms("Ziel"));
		ziel2._t(S.ms("Ziel"));

		if (this.getRbComponentMap(RecV.key).isNew()) {
			einleitung1._t(S.ms("Fuhrenspezifikation").ut(" (").t("NEU").ut(")"));
			einleitung2._t(S.ms("Zusatzangaben").ut(" (").t("NEU").ut(")"));
		} else {
			MyLong idVector = new MyLong(((RB_Dataobject_V21)this.getRbComponentMap(RecV.key).getRbDataObjectActual()).getIdLong());
			einleitung1._t(S.ms("Fuhrenspezifikation")	.ut(" (ID: "+idVector.get_cF_LongString()+")"));
			einleitung2._t(S.ms("Zusatzangaben")		.ut(" (ID: "+idVector.get_cF_LongString()+")"));
		}
		
		
		
		if (art != null) {
		
		
			switch (art) {
			case WA:
				quelle1._t(mandantenLabel);
				quelle2._t(mandantenLabel);
				ziel1._t(S.ms("Abnehmer"));
				ziel2._t(S.ms("Abnehmer"));
				
				break;
	
				
			case WA_L:    //Verkauf von Ware, bleibt im Lager (Fremdwarenlager)
				quelle1._t(mandantenLabel);
				quelle2._t(mandantenLabel);
				ziel1._t(mandantenLabel+" / Fremdware");
				ziel2._t(mandantenLabel+" / Fremdware");
				
				break;
				
				
				
			case WE:
				quelle1._t(S.ms("Lieferant"));
				quelle2._t(S.ms("Lieferant"));
				ziel1._t(mandantenLabel);
				ziel2._t(mandantenLabel);
			
				
				break;
				
				
			case WE_L:
				quelle1._t(mandantenLabel+" / Fremdware");
				quelle2._t(mandantenLabel+" / Fremdware");
				ziel1._t(mandantenLabel);
				ziel2._t(mandantenLabel);
				
				break;
				
				
			case STRECKE:
				quelle1._t(S.ms("Lieferant"));
				quelle2._t(S.ms("Lieferant"));
				ziel1._t(S.ms("Abnehmer"));
				ziel2._t(S.ms("Abnehmer"));
	
				
				break;
				
			case LAGER_LAGER:
				quelle1._t(mandantenLabel);
				quelle2._t(mandantenLabel);
				ziel1._t(mandantenLabel);
				ziel2._t(mandantenLabel);
	
				
				break;
	
			case LEERGUTRANSPORT: 
				quelle1._t(S.ms("Quelle"));
				quelle2._t(S.ms("Quelle"));
				ziel1._t(S.ms("Ziel"));
				ziel2._t(S.ms("Ziel"));
	
				break;
				
				
			case TESTSTELLUNG: 
				quelle1._t(mandantenLabel);
				quelle2._t(mandantenLabel);
				ziel1._t(S.ms("Abnehmer"));
				ziel2._t(S.ms("Abnehmer"));
				
	
				break;
				
			case FREMDWARENTRANSPORT: 
				quelle1._t(S.ms("Quelle"));
				quelle2._t(S.ms("Quelle"));
				ziel1._t(S.ms("Ziel"));
				ziel2._t(S.ms("Ziel"));
	
	
				break;
				
				
			case EINBUCHUNG:
				quelle1._t(S.ms("Quelle"));
				quelle2._t(S.ms("Quelle"));
				ziel1._t(S.ms("Ziel"));
				ziel2._t(S.ms("Ziel"));
	
				break;
			case AUSBUCHUNG:
				quelle1._t(S.ms("Quelle"));
				quelle2._t(S.ms("Quelle"));
				ziel1._t(S.ms("Ziel"));
				ziel2._t(S.ms("Ziel"));
	
				break;
			case UMBUCHUNG:
				quelle1._t(S.ms("Quelle"));
				quelle2._t(S.ms("Quelle"));
				ziel1._t(S.ms("Ziel"));
				ziel2._t(S.ms("Ziel"));
	
				break;
			default:
				break;
			}
		}
		return this;
	}

	
	
	private B2_McForMaskShapeSettings _setRcMarker(EnPositionStation pos)  throws Exception{
		

		Long idAdresse1 = 	this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
		Long idArtBez1 = 	this.getLongLiveVal(RecA1.key, BG_ATOM.id_artikel_bez);
		boolean rc1 = new PdServiceCheckIfSorteIsRC().isRC(idArtBez1, idAdresse1);
		
		Long idAdresse2 = 	this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse);
		Long idArtBez2 = 	this.getLongLiveVal(RecA2.key, BG_ATOM.id_artikel_bez);
		boolean rc2 = new PdServiceCheckIfSorteIsRC().isRC(idArtBez2, idAdresse2);
		
		((B2_SearchArtbezV2)this.getComponent(RecA1.key, BG_ATOM.id_artikel_bez))._setRC(false);
		((B2_SearchArtbezV2)this.getComponent(RecA2.key, BG_ATOM.id_artikel_bez))._setRC(false);
		((B2_SearchArtbezV2)this.getComponent(RecA1.key, BG_ATOM.id_artikel_bez))._setRC(rc1);
		((B2_SearchArtbezV2)this.getComponent(RecA2.key, BG_ATOM.id_artikel_bez))._setRC(rc2);
		

		
		return this;
	}
	
	
	private B2_McForMaskShapeSettings _disableWaehrungWhenKontraktOrAngebotIsPresent()  throws Exception{
			

		Long idKontraktPosEk   = 	this.getLongLiveVal(RecA1.key,	BG_ATOM.id_vpos_kon.fk());
		Long idKontraktPosVk   = 	this.getLongLiveVal(RecA2.key,	BG_ATOM.id_vpos_kon.fk());
		
		Long idAngebotPosEk   = 	this.getLongLiveVal(RecA1.key,	BG_ATOM.id_vpos_std.fk());
		Long idAngebotPosVk   = 	this.getLongLiveVal(RecA2.key,	BG_ATOM.id_vpos_std.fk());
		
		if (idKontraktPosEk != null) {
			Rec21 vpos = new Rec21(_TAB.vpos_kon)._fill_id(idKontraktPosEk);
			Long idWaehrung = vpos.getLongDbValue(VPOS_KON.id_waehrung_fremd);
			if (idWaehrung != null) {
				this._setEnabledForEdit(RecA1.key, BG_ATOM.id_waehrung,false);
			}
		} else if (idAngebotPosEk != null) {
			Rec21 vpos = new Rec21(_TAB.vpos_std)._fill_id(idAngebotPosEk);
			Long idWaehrung = vpos.getLongDbValue(VPOS_STD.id_waehrung_fremd);
			if (idWaehrung != null) {
				this._setEnabledForEdit(RecA1.key, BG_ATOM.id_waehrung,false);
			}

		}
		
		if (idKontraktPosVk != null) {
			Rec21 vpos = new Rec21(_TAB.vpos_kon)._fill_id(idKontraktPosVk);
			Long idWaehrung = vpos.getLongDbValue(VPOS_KON.id_waehrung_fremd);
			if (idWaehrung != null) {
				this._setEnabledForEdit(RecA2.key, BG_ATOM.id_waehrung,false);
			}
		} else if (idAngebotPosVk != null) {
			Rec21 vpos = new Rec21(_TAB.vpos_std)._fill_id(idAngebotPosVk);
			Long idWaehrung = vpos.getLongDbValue(VPOS_STD.id_waehrung_fremd);
			if (idWaehrung != null) {
				this._setEnabledForEdit(RecA2.key, BG_ATOM.id_waehrung,false);
			}
		}
		

		return this;
	}


	
	private B2_McForMaskShapeSettings _disableSortenAngebotAndKontrakt()  throws Exception{

		Long idVposKonLeft = 	this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_kon);
		Long idVposStdLeft =	this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_std);
		
		Long idVposKonRight = 	this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_kon);
		Long idVposStdRight =	this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_std);

		if (idVposKonLeft!=null || idVposStdLeft!=null) {
			if (idVposKonLeft!=null ) {
				this._setEnabledForEdit(RecA1.key, BG_ATOM.id_vpos_std,false);
			} else {
				this._setEnabledForEdit(RecA1.key, BG_ATOM.id_vpos_kon,false);
			}
			this._setEnabledForEdit(RecA1.key, BG_ATOM.id_artikel_bez,false);
		}
		
		if (idVposKonRight!=null || idVposStdRight!=null) {
			if (idVposKonRight!=null ) {
				this._setEnabledForEdit(RecA2.key, BG_ATOM.id_vpos_std,false);
			} else {
				this._setEnabledForEdit(RecA2.key, BG_ATOM.id_vpos_kon,false);
			}
			this._setEnabledForEdit(RecA2.key, BG_ATOM.id_artikel_bez,false);
		}
		

		
		return this;
	
	}
	
	
	private B2_McForMaskShapeSettings _disableAngebotAndKontraktWhenNotAllowed()  throws Exception{

		EnTransportTyp typ = this.getEnTransportTyp();
		
		if (typ != null) {
			if (!typ.isAllowKontraktAngebotLeft()) {
				this._setEnabledForEdit(RecA1.key, BG_ATOM.id_vpos_kon,false);
				this._setEnabledForEdit(RecA1.key, BG_ATOM.id_vpos_std,false);
			}
			
			if (!typ.isAllowKontraktAngebotRight()) {
				this._setEnabledForEdit(RecA2.key, BG_ATOM.id_vpos_kon,false);
				this._setEnabledForEdit(RecA2.key, BG_ATOM.id_vpos_std,false);
			}
		}
		

		
		return this;
	
	}
	
	private B2_McForMaskShapeSettings _disableZusatzLieferbedingungen()  throws Exception{
		Long idLieferbedingungLeft = this.getLongLiveVal(RecA1.key, BG_ATOM.id_lieferbedingungen);
		Long idLieferbedingungRight = this.getLongLiveVal(RecA2.key, BG_ATOM.id_lieferbedingungen);
	
		if (O.isNull(idLieferbedingungLeft)) {
			this._setEnabledForEdit(RecA1.key, BG_ATOM.lieferbedingungen,false);
		}

		if (O.isNull(idLieferbedingungRight)) {
			this._setEnabledForEdit(RecA2.key, BG_ATOM.lieferbedingungen,false);
		}
			
		return this;

	}

	private B2_McForMaskShapeSettings _disableZusatzZahlungsbedingungen()  throws Exception{
		Long idZahlungbedingungLeft = this.getLongLiveVal(RecA1.key, BG_ATOM.id_zahlungsbedingungen);
		Long idZahlungbedingungRight = this.getLongLiveVal(RecA2.key, BG_ATOM.id_zahlungsbedingungen);
	
		if (O.isNull(idZahlungbedingungLeft)) {
			this._setEnabledForEdit(RecA1.key, BG_ATOM.zahlungsbedingungen,false);
		}

		if (O.isNull(idZahlungbedingungRight)) {
			this._setEnabledForEdit(RecA2.key, BG_ATOM.zahlungsbedingungen,false);
		}
		
		return this;
	}



	private B2_McForMaskShapeSettings _disableFieldsWhenMengeOrPreisClosed()  throws Exception{

		VEK<IF_Field> fieldsClosedWhenMengeClosed = new VEK<IF_Field>()	._a(BG_ATOM.id_artikel_bez)
																		._a(BG_ATOM.id_vpos_kon)
																		._a(BG_ATOM.id_vpos_std)
																		._a(BG_ATOM.menge)
																		._a(BG_ATOM.datum_ausfuehrung)
																		._a(BG_ATOM.id_lieferbedingungen)
																		._a(BG_ATOM.lieferbedingungen)
																		._a(BG_ATOM.id_eak_code)
																		._a(BG_STATION.id_adresse)
																		._a(BG_VEKTOR.en_transport_typ)
																		._a(BG_VEKTOR.id_adresse_fremdware)
																		._a(BG_VEKTOR.planmenge)
																		;
					
		VEK<IF_Field> fieldsClosedWhenPreisClosed = new VEK<IF_Field>()	._a(BG_ATOM.e_preis_basiswaehrung)
																		._a(BG_ATOM.id_waehrung)
																		._a(BG_ATOM.waehrungskurs)
																		._a(BG_ATOM.id_bg_pruefprot_menge)
																		._a(BG_ATOM.id_zahlungsbedingungen)
																		._a(BG_ATOM.zahlungsbedingungen)
																		._a(BG_ATOM.zahlungsbedingungen)
																		._a(BG_ATOM.id_tax)
																		._a(BG_ATOM.eu_steuer_vermerk)
																		._a(BG_STATION.umsatzsteuerlkz)
																		._a(BG_STATION.umsatzsteuerid)
																		._a(BG_ATOM.id_zolltarifnummer)
																		;
	
		VEK<IF_Field> fieldsClosedWhenLadungClosed = new VEK<IF_Field>()._a(BG_ATOM.id_bg_pruefprot_preis)
																		._a(BG_STATION.telefon)
																		._a(BG_STATION.fax)
																		._a(BG_ATOM.bestellnummer)
																		._a(BG_STATION.oeffnungszeiten)
																		._a(BG_STATION.name1)
																		._a(BG_STATION.name2)
																		._a(BG_STATION.name3)
																		._a(BG_STATION.strasse)
																		._a(BG_STATION.hausnummer)
																		._a(BG_STATION.plz)
																		._a(BG_STATION.ort)
																		._a(BG_STATION.ortzusatz)
																		
																		._a(BG_ATOM.anr1)
																		._a(BG_ATOM.anr2)
																		._a(BG_ATOM.artbez1)
																		._a(BG_ATOM.artbez2)
																		
																		._a(BG_VEKTOR.id_transportmittel)
																		._a(BG_VEKTOR.transportmittel)
																		._a(BG_VEKTOR.transportkennzeichen)
																		._a(BG_VEKTOR.anhaengerkennzeichen)
																		._a(BG_VEKTOR.anhaengerkennzeichen)
																		._a(BG_VEKTOR.id_adresse_spedition)
																		._a(BG_VEKTOR.id_land_transit1)
																		._a(BG_VEKTOR.id_land_transit2)
																		._a(BG_VEKTOR.id_land_transit3)
																		._a(BG_VEKTOR.datum_planung_von)
																		._a(BG_VEKTOR.datum_planung_bis)
																		._a(BG_VEKTOR.transportverantwortung)
																		
																		;

			
		if (this.isAbschlussMengenSelected(RecA1.key)) {
			for (IF_Field f: fieldsClosedWhenMengeClosed) {
				this._setDisabledLeft(f);
			}
		}

		if (this.isAbschlussPreisSelected(RecA1.key)) {
			for (IF_Field f: fieldsClosedWhenPreisClosed) {
				this._setDisabledLeft(f);
			}
		}

		if (this.isAbschlussLadungSelected(RecA1.key)) {
			for (IF_Field f: fieldsClosedWhenLadungClosed) {
				this._setDisabledLeft(f);
			}
		}
		
		
		if (this.isAbschlussMengenSelected(RecA2.key)) {
			for (IF_Field f: fieldsClosedWhenMengeClosed) {
				this._setDisabledRight(f);
			}
		}

		if (this.isAbschlussPreisSelected(RecA2.key)) {
			for (IF_Field f: fieldsClosedWhenPreisClosed) {
				this._setDisabledRight(f);
			}
		}

		if (this.isAbschlussLadungSelected(RecA2.key)) {
			for (IF_Field f: fieldsClosedWhenLadungClosed) {
				this._setDisabledRight(f);
			}
		}
		
		return this;
	}

	
	private B2_McForMaskShapeSettings _disableFremdAdresseIfNotNeeded()  throws Exception{
		EnTransportTyp transportTyp = this.getTransportArt();

		if (transportTyp != null) {
			if (!transportTyp.needsFremdbesitzer()) {
				this._setDisabled(RecV.key, BG_VEKTOR.id_adresse_fremdware);
			}
		}
		
		return this;

	}
	
	
	
	
	
	private B2_McForMaskShapeSettings _disableTransportmittelFollowFields()  throws Exception{
		Long idTransportmittel = this.getLongLiveVal(RecV.key, BG_VEKTOR.id_transportmittel);
		
		if (O.isNull(idTransportmittel)) {
			this._setDisabled(RecV.key, BG_VEKTOR.transportmittel);
			this._setDisabled(RecV.key, BG_VEKTOR.transportkennzeichen);
			this._setDisabled(RecV.key, BG_VEKTOR.anhaengerkennzeichen);
		}
		return this;
	}

	
	
	private B2_McForMaskShapeSettings _setFremdWahrungsInfo() throws Exception {
		
		B2_InfoBlockFremdwaehrung infoLeft =  (B2_InfoBlockFremdwaehrung)this.get_comp(RecA1.key, B2_InfoBlockFremdwaehrung.key()); 
		B2_InfoBlockFremdwaehrung infoRight = (B2_InfoBlockFremdwaehrung)this.get_comp(RecA2.key, B2_InfoBlockFremdwaehrung.key());
		
		infoLeft._fill();
		infoRight._fill();
		
		
		return this;
		
	}

	
	private B2_McForMaskShapeSettings _setFieldLabels() throws Exception {
		
//		B2_fieldLabelLadestation ladeStation = 		(B2_fieldLabelLadestation)this.getComp(RecV.key, B2_fieldLabelLadestation.getKey());
//		B2_fieldLabelAbladeStation abladeStation = 	(B2_fieldLabelAbladeStation)this.getComp(RecV.key, B2_fieldLabelAbladeStation.getKey());
//		B2_fieldLabelQuellSorte quellSorte = 		(B2_fieldLabelQuellSorte)this.getComp(RecV.key, B2_fieldLabelQuellSorte.getKey());
//		B2_fieldLabelZielSorte zielSorte = 			(B2_fieldLabelZielSorte)this.getComp(RecV.key, B2_fieldLabelZielSorte.getKey());
		
		
		RB_labInGridNoDatabase   ladeStation = (RB_labInGridNoDatabase) this.getComp(RecV.key, TK.gen(EnTabKeyInMask.S1,BG_STATION.id_adresse));
		RB_labInGridNoDatabase   abladeStation = (RB_labInGridNoDatabase) this.getComp(RecV.key, TK.gen(EnTabKeyInMask.S3,BG_STATION.id_adresse));
		RB_labInGridNoDatabase   quellSorte = (RB_labInGridNoDatabase) this.getComp(RecV.key, TK.gen(EnTabKeyInMask.A1,BG_ATOM.id_artikel_bez));
		RB_labInGridNoDatabase   zielSorte = (RB_labInGridNoDatabase) this.getComp(RecV.key, TK.gen(EnTabKeyInMask.A2,BG_ATOM.id_artikel_bez));
		
		EnTransportTyp typ = O.NN(this.getEnTransportTyp(),EnTransportTyp.STRECKE);
		
		
		Font f = new E2_FontPlain()._fs(8)._i();
		
		switch (typ) {
		case AUSBUCHUNG_F:
		case EINBUCHUNG_F:
		case FREMDWARENTRANSPORT:
		case LAGER_LAGER:
		case LEERGUTRANSPORT:
		case STRECKE:
		case TESTSTELLUNG:
		case WA:
		case WA_L:
		case WE:
		case WE_L:
			f = new E2_FontPlain();
			ladeStation._t(		S.ms("Ladestation"))	._f(f);
			abladeStation._t(	S.ms("Abladestation"))	._f(f);
			quellSorte._t(		S.ms("Sorte"))			._f(f);
			zielSorte._t(		S.ms("Sorte"))			._f(f);
			break;
		case UMBUCHUNG:
			f = new E2_FontPlain();

			ladeStation._t(		S.ms("Buchung-Lag."))	._f(f);
			abladeStation._t(	S.ms("Buchung-Lag."))	._f(f);
			quellSorte._t(		S.ms("Umbuch.Sorte"))	._f(f);
			zielSorte._t(		S.ms("Ziel-Sorte"))		._f(f);
			break;
			
		case AUSBUCHUNG:
			f = new E2_FontPlain();
			ladeStation._t(		S.ms("Buchung-Lag."))	._f(f);
			abladeStation._t(	S.ms("Pseudo-Lag."))	._f(f);
			quellSorte._t(		S.ms("Ausbuch.Sorte"))	._f(f);
			zielSorte._t(		S.ms("Ausbuch.Sorte"))		._f(f);
			
			
			break;
			
		case EINBUCHUNG:
			f = new E2_FontPlain();
			ladeStation._t(		S.ms("Pseudo-Lag."))	._f(f);
			abladeStation._t(	S.ms("Buchung-Lag."))	._f(f);
			quellSorte._t(		S.ms("Einbuch.Sorte"))	._f(f);
			zielSorte._t(		S.ms("Einbuch.Sorte"))		._f(f);
			
			
			break;
		default:
			break;

		}
		
		
		
		return this;
		
	}

	
	
	
	
}
