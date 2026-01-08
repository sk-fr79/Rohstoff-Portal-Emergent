/**
 * rohstoff.businesslogic.bewegung2.mask.Setters
 * @author martin
 * @date 09.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Setters;

import java.util.HashMap;

import panter.gmbh.BasicInterfaces.Service.PdServiceWritePruefProtokol;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.EnPruefungTyp;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_McForSettingsBeforeSave extends B2_MaskController {

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_component
	 * @throws myException
	 */
	public B2_McForSettingsBeforeSave(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_componentMap
	 * @throws myException
	 */
	public B2_McForSettingsBeforeSave(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}



	
	/**
	 * routine, die jedesmal vor dem speichern aufgerufen wird. Dabei koennen felder, deren besetzung eindeutig ist
	 * oder auch verdeckte felder gefuellt werden. 
	 * Wird ein fehler in dem uebergebenen mv hinterlegt, dann scheitert der speichervorgang
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param mv
	 * @return
	 */
	public B2_McForSettingsBeforeSave _setFieldsBeforeSave(MyE2_MessageVector mv)  {
		this._setStatusBesitzer(mv);
		this._setIdAdresseMiddleStation(mv);
		
		return this;
	}
	
	
	
	
	private B2_McForSettingsBeforeSave _setStatusBesitzer(MyE2_MessageVector mv) {
		try {
			//falls nur validiert wird, pruefen, ob die vorhandenen feldwert in den besitz-feldern den vorgaben entsprechen
			VEK<Long> adressenIst = new VEK<Long>()	._a(this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse_besitz_ldg.fk()))
													._a(this.getLongLiveVal(RecS2.key, BG_STATION.id_adresse_besitz_ldg.fk()))
													._a(this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse_besitz_ldg.fk()))
													;

			//es muessen alle gesetzt sein
			if (O.isOneNull(adressenIst.toArray())) {
				this.set_maskVal(RecS1.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
				this.set_maskVal(RecS2.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
				this.set_maskVal(RecS3.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
			}
		} catch (myException e) {
			mv._add(e.get_ErrorMessage());
			
			e.printStackTrace();
		}

		
		return this;
	}
	
	
	private B2_McForSettingsBeforeSave _setIdAdresseMiddleStation(MyE2_MessageVector mv) {
		try {
			
			Rec21 	recAdresseMandant = new Rec21(_TAB.adresse)._fill_id(bibALL.get_ID_ADRESS_MANDANT());

			EnTransportTyp transportArt = this.getTransportArt();
			
			Rec21 	recAdresseMandant2 = recAdresseMandant;
			this.set_maskVal(RecS2.key, BG_STATION.name1, 		"Sonderlager", bibMSG.MV());
			this.set_maskVal(RecS2.key, BG_STATION.plz, 		"sonder", bibMSG.MV());
			this.set_maskVal(RecS2.key, BG_STATION.id_land, 	recAdresseMandant2.getFs(ADRESSE.id_land), bibMSG.MV());
			
			@SuppressWarnings("unchecked")
			HashMap<ENUM_SONDERLAGER,Rec21> sonderLager = (HMAP<ENUM_SONDERLAGER, Rec21>)ENUM_MANDANT_SESSION_STORE.ALL_SONDERLAGER_HASHMAP.getValueFromSession();
			
			
			if (transportArt!=null) {
				
				switch (transportArt) {
				case LAGER_LAGER:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, sonderLager.get(ENUM_SONDERLAGER.LL).get_key_value(),mv);
					break;

				case STRECKE:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, sonderLager.get(ENUM_SONDERLAGER.STRECKE).get_key_value(),mv);
					break;

				case WA:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, sonderLager.get(ENUM_SONDERLAGER.ZWA).get_key_value(),mv);
					break;
				
				case WE:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, sonderLager.get(ENUM_SONDERLAGER.ZWE).get_key_value(),mv);
					break;

				case AUSBUCHUNG:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, 			sonderLager.get(ENUM_SONDERLAGER.ZWA_UMB).get_key_value(),mv);
					
					this.set_maskVal(RecA1.key, BG_ATOM.e_preis_basiswaehrung,     "0",mv);
					this.set_maskVal(RecA2.key, BG_ATOM.e_preis_basiswaehrung,     "0",mv);
					this.set_maskVal(RecA1.key, BG_ATOM.e_preis_fremdwaehrung,     "0",mv);
					this.set_maskVal(RecA2.key, BG_ATOM.e_preis_fremdwaehrung,     "0",mv);

					
					this._copyMaskValue(RecA1.key, BG_ATOM.menge, 					RecA2.key, BG_ATOM.menge);
					
					if ( this.getValueJustInTime(RecA1.key, BG_ATOM.menge)!=null) {
						writePruefProtokolle(RecA1.key);
					}
					if ( this.getValueJustInTime(RecA2.key, BG_ATOM.menge)!=null) {
						writePruefProtokolle(RecA2.key);
					}

					
					
					break;
				case AUSBUCHUNG_F:
				case EINBUCHUNG:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, sonderLager.get(ENUM_SONDERLAGER.ZWE_UMB).get_key_value(),mv);
					
					this.set_maskVal(RecA1.key, BG_ATOM.e_preis_basiswaehrung,     "0",mv);
					this.set_maskVal(RecA2.key, BG_ATOM.e_preis_basiswaehrung,     "0",mv);
					this.set_maskVal(RecA1.key, BG_ATOM.e_preis_fremdwaehrung,     "0",mv);
					this.set_maskVal(RecA2.key, BG_ATOM.e_preis_fremdwaehrung,     "0",mv);

					this._copyMaskValue(RecA2.key, BG_ATOM.menge, 					RecA1.key, BG_ATOM.menge);
					this._copyMaskValue(RecA2.key, BG_ATOM.e_preis_basiswaehrung, 	RecA1.key, BG_ATOM.e_preis_basiswaehrung);
					if ( this.getValueJustInTime(RecA1.key, BG_ATOM.menge)!=null) {
						writePruefProtokolle(RecA1.key);
					}
					if ( this.getValueJustInTime(RecA2.key, BG_ATOM.menge)!=null) {
						writePruefProtokolle(RecA2.key);
					}

				
					break;
				case EINBUCHUNG_F:
				case FREMDWARENTRANSPORT:
					
				case LEERGUTRANSPORT:
				case TESTSTELLUNG:
				case UMBUCHUNG:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, 			sonderLager.get(ENUM_SONDERLAGER.ZW_UMB).get_key_value(),mv);
					break;
				case WA_L:
				case WE_L:
					this.getActionsFieldValueChangers()._a(()-> {
						this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
						this.set_maskVal(RecS2.key, BG_STATION.id_adresse, 			sonderLager.get(ENUM_SONDERLAGER.UH).get_key_value(),mv);
					});

					break;
				default:
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse_basis, 	recAdresseMandant2.getFs(ADRESSE.id_adresse), mv);
					this.set_maskVal(RecS2.key, BG_STATION.id_adresse, sonderLager.get(ENUM_SONDERLAGER.LL).get_key_value(),mv);
					break;
				}
			}
			
		} catch (Exception e) {
			mv._addAlarm(e.getMessage()+" <8ba928bc-58a6-11ea-82b4-0242ac130003>");
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	private void writePruefProtokolle(RB_KM maskKey) throws myException, Exception {
		if (this.getValueJustInTime(maskKey, BG_ATOM.id_bg_pruefprot_menge)==null) {
			Rec21 pp = new PdServiceWritePruefProtokol().getNewPruefprotRec(EnPruefungTyp.BG_ATOM_MENGENKONTROLLE, _TAB.bg_atom, null);
			this._setMaskValue(maskKey, BG_ATOM.id_bg_pruefprot_menge, pp.getIdLong().toString());
		}
		if (this.getValueJustInTime(maskKey, BG_ATOM.id_bg_pruefprot_preis)==null) {
			Rec21 pp = new PdServiceWritePruefProtokol().getNewPruefprotRec(EnPruefungTyp.BG_ATOM_PREISKONTROLLE, _TAB.bg_atom, null);
			this._setMaskValue(maskKey, BG_ATOM.id_bg_pruefprot_preis, pp.getIdLong().toString());
		}
		if (this.getValueJustInTime(maskKey, BG_ATOM.id_bg_pruefprot_abschluss)==null) {
			Rec21 pp = new PdServiceWritePruefProtokol().getNewPruefprotRec(EnPruefungTyp.BG_ATOM_ABSCHLUSS, _TAB.bg_atom, null);
			this._setMaskValue(maskKey, BG_ATOM.id_bg_pruefprot_abschluss, pp.getIdLong().toString());
		}
	}
	
	
}
