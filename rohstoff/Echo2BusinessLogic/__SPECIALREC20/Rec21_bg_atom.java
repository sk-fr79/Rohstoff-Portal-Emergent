package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class Rec21_bg_atom extends Rec21 {

	public Rec21_bg_atom() throws myException {
		super(_TAB.bg_atom);
	}
	
	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_bg_atom(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	public Rec21_BgStation getStationQuelle() throws myException {
		Rec21_BgStation ret = null;
		
		if (this.is_ExistingRecord()) {
			ret = new Rec21_BgStation(this.get_up_Rec21(BG_ATOM.id_bg_station_quelle, BG_STATION.id_bg_station, true));
		}
		return ret;
	}
	
	
	
	public Rec21_BgStation getStationZiel() throws myException {
		Rec21_BgStation ret = null;
		
		if (this.is_ExistingRecord()) {
			ret = new Rec21_BgStation(this.get_up_Rec21(BG_ATOM.id_bg_station_ziel, BG_STATION.id_bg_station, true));
		}
		return ret;
	}
	
	
	
	public Rec21_adresse getAdresseQuelle() throws myException {
		Rec21_adresse   adresseQuelle = null;
		Rec21_BgStation quelle = this.getStationQuelle();
		
		if (quelle!=null) {
			adresseQuelle = quelle.getAdresse();
		}
		
		return adresseQuelle;
	}
	
	
	
	public Rec21_adresse getAdresseZiel() throws myException {
		Rec21_adresse   adresseZiel = null;
		Rec21_BgStation ziel = this.getStationZiel();
		
		if (ziel!=null) {
			adresseZiel = ziel.getAdresse();
		}
		
		return adresseZiel;
	}


	
	
	
	
}
