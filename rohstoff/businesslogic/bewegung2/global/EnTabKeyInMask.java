/**
 * rohstoff.businesslogic.bewegung2.list
 * @author martin
 * @date 23.11.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 23.11.2018
 * definiert die position der datensaetze innerhalb eines transports (feld pos_in_mask in BG_STATION)
 */
public enum EnTabKeyInMask implements IF_enumForDb<EnTabKeyInMask>{
	
	V(	_TAB.bg_vektor, "Bewegungsverktor", 			RecV.key)
	,A1(_TAB.bg_atom, "Bewegungsatom (Ladeseite)", 		RecA1.key)
	,A2(_TAB.bg_atom, "Bewegungsatom (Abladeseite)", 	RecA2.key)
	,S1(_TAB.bg_station, "Station Ladeseite", 			RecS1.key)
	,S2(_TAB.bg_station, "Station Zwischenlager", 		RecS2.key)
	,S3(_TAB.bg_station, "Station Abladeseite", 		RecS3.key)
	;


	private _TAB 	table = null;
	private String 	userText = null;
	private RB_KM   maskKey = null;

	private EnTabKeyInMask(_TAB table, String userText, RB_KM maskkey) {
		this.table = table;
		this.userText = userText;
		this.maskKey = maskkey;
	}

	public _TAB getTable() {
		return table;
	}


	@Override
	public EnTabKeyInMask[] getValues() {
		return EnTabKeyInMask.values();
	}

	public String userText() {
		return userText;
	}


	public RB_KM getMaskKey() {
		return maskKey;
	}

	public static EnTabKeyInMask findEnumTab(RB_KM maskKey) {
		EnTabKeyInMask ret = null;
		for (EnTabKeyInMask et: EnTabKeyInMask.values()) {
			if (et.getMaskKey().equals(maskKey)) {
				ret = et;
				break;
			}
		}
		return ret;
	}


	
}
