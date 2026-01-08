/**
 * rohstoff.businesslogic.bewegung2.list
 * @author martin
 * @date 13.02.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 13.02.2019
 *
 */
public class B2_ListBtListToMask extends B2_ListBtListToMaskAbstract {

	/**
	 * @author martin
	 * @date 13.02.2019
	 *
	 * @param bEdit
	 * @param p_tpHashMap
	 */
	public B2_ListBtListToMask(boolean bEdit, RB_TransportHashMap p_tpHashMap) {
		super(bEdit, p_tpHashMap);
	}

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung2.list.B2_ListBtListToMaskAbstract#getIdsSelected()
	 */
	@Override
	public VEK<String> getIdsSelected() {
		return new VEK<String>()._a(this.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated());
	}

}
