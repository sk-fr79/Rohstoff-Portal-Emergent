/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 22.05.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SelectTaxWithGroups;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE.EnTaxSelector;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;

/**
 * @author martin
 * @date 22.05.2019
 *
 */
public class B2_SelectTaxInGroups extends RB_HL_SelectTaxWithGroups {

	/**
	 * @author martin
	 * @date 22.05.2019
	 *
	 * @throws myException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public B2_SelectTaxInGroups(EnPositionStation pos) throws myException {
		super(  ((HMAP<String,String>)((HMAP<EnTaxSelector, HMAP>)ENUM_MANDANT_SESSION_STORE.STRUCTURE_FOR_TAX_SELECT.getValueFromSession()).get(EnTaxSelector.ACTIVE))
				,((HMAP<String,String>)((HMAP<EnTaxSelector, HMAP>)ENUM_MANDANT_SESSION_STORE.STRUCTURE_FOR_TAX_SELECT.getValueFromSession()).get(EnTaxSelector.INACTIVE))
				,((HMAP<String,HMAP<String,String>>)((HMAP<EnTaxSelector, HMAP>)ENUM_MANDANT_SESSION_STORE.STRUCTURE_FOR_TAX_SELECT.getValueFromSession()).get(EnTaxSelector.SUBMENUES))
				) ;
		this._setHeightOfDropDownBlock(180);
		this._setWidthTextField(323)._render();
		this.getTfShowSelection()._fo_s_plus(-2);
		
		if (pos == EnPositionStation.RIGHT) {
			this._setHorizontalAlignRight()._render();
		}
		
	}

}
