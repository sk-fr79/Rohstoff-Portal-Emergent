/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 27.03.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_cb_ro;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 27.03.2019
 *
 */
public class RB_cb_KadenzAbschluss extends RB_cb_ro {
    
	public static RB_KF key = new RB_KF()._setHASHKEY("8309f471-1a8b-4aef-b6ad-9f6a37115acc")._setREALNAME("Kadenz nach Abschluss");
	
	/**
	 * @author manfred
	 * @date 27.03.2019
	 *
	 */
	public RB_cb_KadenzAbschluss(String sText) {
		super();
		this.setText(sText);
		this.setSelected(true);
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_cb#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
//		super.rb_Datacontent_to_Component(dataObject);
	}
	
	
	
	

}
