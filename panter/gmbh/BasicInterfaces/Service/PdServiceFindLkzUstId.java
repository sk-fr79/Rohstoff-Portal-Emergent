/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 09.03.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.TODO;
import panter.gmbh.indep.O;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 09.03.2020
 *{@link TODO} mit findungsverfahren ausprogrammieren
 */
public class PdServiceFindLkzUstId {

	public PdServiceFindLkzUstId() {
	}
	
	public Pair<String> getUstLkzUstID(Rec21 adresseRelevant, Rec21 adresseStart, Rec21 adresseZiel) {
		Pair<String> ret = null;
		if (O.isNoOneNull(adresseRelevant,  adresseStart,  adresseZiel)) {
			try {
				Rec21_adresse firma = new Rec21_adresse(adresseRelevant)._getMainAdresse();
				
				Rec21_adresse firmaStart = new Rec21_adresse(adresseStart)._getMainAdresse();
				Rec21_adresse firmaZiel =  new Rec21_adresse(adresseZiel)._getMainAdresse();
				
				Rec21 land = null;
				
				if (firma.getId()==firmaStart.getId()) {
					land = adresseStart.get_up_Rec21(LAND.id_land);
				} else {
					land = adresseZiel.get_up_Rec21(LAND.id_land);
				}
				
				if (land != null) {
					//jetzt die hashmap aus <laenderkuerzel> und <pair ustlkz,ustid> aufbauen
					HMAP<String, Pair<String>> ustids = new HMAP<>();
					if (firma.get_up_Rec21(LAND.id_land)!=null && firma.getUstLkzUstIdBasis()!=null) {
						ustids._put(firma.get_up_Rec21(LAND.id_land).getUfs(LAND.laendercode).toUpperCase(), firma.getUstLkzUstIdBasis());
					}
		
					RecList21 reclistUstids = firma.get_down_reclist21(ADRESSE_UST_ID.id_adresse);
					for (Rec21 ustid: reclistUstids) {
						if (ustid.get_up_Rec21(LAND.id_land)!=null) {
							ustids._put(ustid.get_up_Rec21(LAND.id_land).getFs(LAND.laendercode).toUpperCase(), 
										new Pair<String>()	._setVal1(ustid.getUfs(ADRESSE_UST_ID.umsatzsteuerlkz, ""))
															._setVal2(ustid.getUfs(ADRESSE_UST_ID.umsatzsteuerid, ""))
															);
						}
					}
	
					ret = ustids.get(land.getUfs(LAND.laendercode).toUpperCase());
				}
				
				
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	

}
