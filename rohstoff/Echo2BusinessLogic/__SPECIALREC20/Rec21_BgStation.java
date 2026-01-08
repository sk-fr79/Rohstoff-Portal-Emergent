/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 14.01.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 14.01.2019
 *
 */
public class Rec21_BgStation extends Rec21 {


	public Rec21_BgStation() throws myException {
		super(_TAB.bg_station);
	}

	public Rec21_BgStation(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	public Rec21_adresse getAdresse() throws myException {
		Rec21_adresse adresse = null;
		
		if (this.is_ExistingRecord()) {
			adresse = new Rec21_adresse(this.get_up_Rec21(ADRESSE.id_adresse));
		}
		
		return adresse;
	}
	
	
	public Rec21_adresse getAdresseBesitz() throws myException {
		Rec21_adresse besitz = null;
		
		if (this.getLongDbValue(BG_STATION.id_adresse_besitz_ldg)!=null) {
			besitz = new Rec21_adresse(this.get_up_Rec21(BG_STATION.id_adresse_besitz_ldg, ADRESSE.id_adresse, true));
			
		}
		return besitz;
	}
	
}
