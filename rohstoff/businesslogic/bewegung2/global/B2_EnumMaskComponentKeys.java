/**
 * rohstoff.businesslogic.bewegung2.list
 * @author martin
 * @date 14.02.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * @date 14.02.2019
 * sammlung von keys fuer die einbindung von nicht db-komponeten in die maske
 */
public enum B2_EnumMaskComponentKeys  implements IF_enumForDb<B2_EnumMaskComponentKeys> {
	
	CKECK_FUHRE_BUTTON()
	,LABEL_4_EINHEIT()
	,GRID_4_KONTRAKTE_ANGEBOTE
	,GRID_4_STATION
	,GRID_4_SORTE
	,
	
	
	;
	
	private B2_EnumMaskComponentKeys() {
		
	}

	public RB_KF getKey() {
		return new RB_KF()._setHASHKEY(this.name()+"7d621cb8-7fae-40bc-bcb2-4c1a2f9a6062")._setREALNAME(this.name());
	}

	@Override
	public B2_EnumMaskComponentKeys[] getValues() {
		return B2_EnumMaskComponentKeys.values();
	}
	
	
}
