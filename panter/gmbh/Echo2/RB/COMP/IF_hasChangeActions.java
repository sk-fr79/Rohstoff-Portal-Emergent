/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 28.12.2018
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 28.12.2018
 *
 */
public interface IF_hasChangeActions {

	public VEK<XX_ActionAgent> getChangeActions();

	
	public default MyE2_MessageVector doChangeActions() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		if (this.getChangeActions()!=null && this.getChangeActions().size()>0) {
			MyE2_MessageVector mv_save = new MyE2_MessageVector()._add(bibMSG.MV());
			bibMSG.MV().clear();
			for (XX_ActionAgent a: this.getChangeActions()) {
				a.executeAgentCode(null);
				if (bibMSG.MV().hasAlarms()) {
					break;
				}
			}
			mv._add(bibMSG.MV());		//die gesammelten msg dem lokalen sammler uebergeben
			bibMSG.MV().clear();
			bibMSG.MV()._add(mv_save);  //die alte bibMSG wiederherstellen
		}
		return mv;
	}
	
}
