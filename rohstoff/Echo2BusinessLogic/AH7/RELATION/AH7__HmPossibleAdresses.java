/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_TEILNEHMER;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * ermitteln, welche adressen im anhang 7 theoretisch moeglich sind 
 */
public class AH7__HmPossibleAdresses extends HashMap<AH7__ENUM_TEILNEHMER,Rec21_adresse> {

	
	
	
	public AH7__HmPossibleAdresses(Long lAdresseGeoStart, Long lAdresseGeoZiel) throws myException{
		super();
		
		if (O.isOneNull(lAdresseGeoStart,lAdresseGeoZiel)) {
			throw new myException(this, "Null-IDs are not allowed !");
		}
		
		//alle durchgehen und vergleichen
		Rec21_adresse 	recAdresseStartGeo = (Rec21_adresse)new Rec21_adresse()._fill_id(lAdresseGeoStart);
		Rec21_adresse 	recAdresseStartJur = recAdresseStartGeo.__get_main_adresse();
		Rec21_adresse	recAdresseStartBesitzGeo = recAdresseStartGeo.getRec21LagerDrittBesitzer();
		
		Rec21_adresse 	recAdresseZielGeo =  (Rec21_adresse)new Rec21_adresse()._fill_id(lAdresseGeoZiel);
		Rec21_adresse 	recAdresseZielJur =  recAdresseZielGeo.__get_main_adresse();
		Rec21_adresse	recAdresseZielBesitzGeo = recAdresseZielGeo.getRec21LagerDrittBesitzer();
		
		
		if (recAdresseStartJur.get_myLong_dbVal(ADRESSE.id_adresse, new MyLong(0)).get_lValue()==recAdresseStartGeo.get_myLong_dbVal(ADRESSE.id_adresse, new MyLong(0)).get_lValue()) {
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_START_JUR, recAdresseStartJur);
		} else {
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_START_JUR, recAdresseStartJur);
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_START_GEO, recAdresseStartGeo);
		}
		if (recAdresseStartBesitzGeo!=null) {
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_START_GEO_DRITTBESITZ, recAdresseStartBesitzGeo);
		}
		
		if (recAdresseZielJur.get_myLong_dbVal(ADRESSE.id_adresse, new MyLong(0)).get_lValue()==recAdresseZielGeo.get_myLong_dbVal(ADRESSE.id_adresse, new MyLong(0)).get_lValue()) {
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_JUR, recAdresseZielJur);
		} else {
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_JUR, recAdresseZielJur);
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_GEO, recAdresseZielGeo);
		}
		if (recAdresseZielBesitzGeo!=null) {
			this.put(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_GEO_DRITTBESITZ, recAdresseZielBesitzGeo);
		}
		
		Rec21_adresse recAdresseMandant = new Rec21_adresse()._fill_id(bibALL.get_ID_ADRESS_MANDANT());
		if (this.get(AH7__ENUM_TEILNEHMER.ADRESSE_START_JUR)!=null && this.get(AH7__ENUM_TEILNEHMER.ADRESSE_START_JUR).get_myLong_dbVal(ADRESSE.id_adresse).get_lValue()==recAdresseMandant.get_myLong_dbVal(ADRESSE.id_adresse).get_lValue()) {
			//adresse durch adresse-mandant ersetzen
			this.remove(AH7__ENUM_TEILNEHMER.ADRESSE_START_JUR);
		}
		if (this.get(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_JUR)!=null && this.get(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_JUR).get_myLong_dbVal(ADRESSE.id_adresse).get_lValue()==recAdresseMandant.get_myLong_dbVal(ADRESSE.id_adresse).get_lValue()) {
			//adresse durch adresse-mandant ersetzen
			this.remove(AH7__ENUM_TEILNEHMER.ADRESSE_ZIEL_JUR);
		}
		this.put(AH7__ENUM_TEILNEHMER.ADRESSE_MANDANT, recAdresseMandant);
		
		//jetzt sind alle erlaubten adressen dieser start-ziel-konfiguration in der hashmap vorhanden
		
	}
	
	
	//vector mit allen moeglichen Long-Werten, die bei der adresskombination vorkommen koennen
	public VEK<Long>  getVectorAllIds() throws myException {
		VEK<Long> v = new VEK<Long>();
		
		for (AH7__ENUM_TEILNEHMER en: AH7__ENUM_TEILNEHMER.values())  {
			if (this.get(en)!=null && !v.contains(this.get(en).get_myLong_dbVal(ADRESSE.id_adresse).get_oLong())) {
				v.add(this.get(en).get_myLong_dbVal(ADRESSE.id_adresse).get_oLong());
			}
		}
		
		return v;
	}
	
	
	
	
}
