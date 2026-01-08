/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 02.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 02.09.2019
 *
 */
public class LH_LIST_cb_einzelPalette extends RB_cb implements MyE2IF__Component {

	private RB_TransportHashMap tp_hashMap = null;

	public LH_LIST_cb_einzelPalette(RB_TransportHashMap tpHashMap) {
		super();
		
		this._t("Einzelne Paletten anzeigen");
		
		this.setSelected(false);
		this.in_border_dd();
		this.tp_hashMap = tpHashMap;
		this.tp_hashMap._setToExtender(LH_CONST.LH_EXTENDER.LH_SHOW_EINZELPALETTE, "N");

		this._aaa(()->show_einzel_palette());
	}


	private void show_einzel_palette() throws myException {
		if(this.isSelected()) {
			this.tp_hashMap._setToExtender(LH_CONST.LH_EXTENDER.LH_SHOW_EINZELPALETTE, "Y");
		}else {
			this.tp_hashMap._setToExtender(LH_CONST.LH_EXTENDER.LH_SHOW_EINZELPALETTE, "N");
		}
		this.tp_hashMap.getNavigationList()._REBUILD_ACTUAL_SITE("");
	}
	
	
	

}
