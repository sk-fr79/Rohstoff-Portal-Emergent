/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 08.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 08.03.2019
 *
 */
public class B2_SelectFieldTransportArt extends RB_selField {

	public B2_SelectFieldTransportArt() throws myException {
		super();
		this._populate(EnTransportTyp.STRECKE.getArray4Selfield4Mask(true));
		
		this._aaa(()-> {
			
			
			new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesWithTransportArt();
			
			
			new B2_McForValueSettingsOnMaskAction(this)._setSortenGleichIfNeeded(EnPositionStation.LEFT);
			new B2_McForValueSettingsOnMaskAction(this)._setSortenAusSortenBez();
			new B2_McForValueSettingsOnMaskAction(this)._clearKontraktAndAngebotWhenNotAllowed();
			
			new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
			new B2_McForValueSettingsOnMaskAction(this)._clearFremdAdresseIfNotNeededOrFalse();
			
			
			new B2_McForMaskShapeSettings(this)	._setAllMaskShapeSettings()
												._setFocus(RecV.key, BG_VEKTOR.planmenge);
			
			
		});
	}

}
