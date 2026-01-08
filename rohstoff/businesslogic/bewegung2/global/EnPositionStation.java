/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 17.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

/**
 * @author martin
 * @date 17.01.2019
 *
 */
public enum EnPositionStation {
	LEFT(RecA1.key,RecS1.key)
	,MID(null,RecS2.key)
	,RIGHT(RecA2.key,RecS3.key)
	,UNDEF(null,null)

	;
	
	private RB_KM  keyAtom = null;
	private RB_KM  keyStation = null;
	
	
	private EnPositionStation(RB_KM p_keyAtom, RB_KM p_keyStation) {
		this.keyAtom = p_keyAtom;
		this.keyStation = p_keyStation;
	}


	public RB_KM getKeyAtom() {
		return keyAtom;
	}


	public RB_KM getKeyStation() {
		return keyStation;
	}

	public RB_KM getKeyAtomOtherSide() {
		if (this.keyAtom.equals(RecA1.key)) {
			return RecA2.key;
		}
		if (this.keyAtom.equals(RecA2.key)) {
			return RecA1.key;
		}
		return null;
	}


	public RB_KM getKeyStationOtherSide() {
		if (this.keyStation.equals(RecS1.key)) {
			return RecS3.key;
		}
		if (this.keyStation.equals(RecS3.key)) {
			return RecS1.key;
		}
		return null;
	}
	
	
	
}
