package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public enum _WE_KEYS {
//	 KOMBI_ANG_KON(BEWEGUNG_ATOM.id_vpos_kon)
	EINHEIT_PREIS(BEWEGUNG_ATOM.id_artikel_bez)
	,EINHEIT(BEWEGUNG_ATOM.id_artikel_bez)
	,LAGER_DIFF_MENGE_WE(BEWEGUNG_ATOM.menge)  			//sonderfeld, falls eine unterschiedliche Lagermenge im Wareneingang gebucht wird (Beispiel: Lieferant wiegt selbst, hat 10000 wir wiegen 9000)
	,ANR12(BEWEGUNG_ATOM.id_artikel_bez)
	
	
	;
	
	private IF_Field field = null;
	private _WE_KEYS(IF_Field f) {
		this.field = f;
	}
	public RB_KF k() throws myException {
		return new RB_KF(this.field, this.name());
	}

}
