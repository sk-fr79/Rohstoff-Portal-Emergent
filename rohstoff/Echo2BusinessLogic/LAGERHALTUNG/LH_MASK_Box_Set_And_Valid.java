/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 15.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 15.10.2019
 *
 */
public class LH_MASK_Box_Set_And_Valid extends RB_Mask_Set_And_Valid {

	private RB_TransportHashMap m_trp_hm = null;
	

	public LH_MASK_Box_Set_And_Valid(RB_TransportHashMap trp_hm) {
		super();
		m_trp_hm = trp_hm;
	}
	
	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		if(ActionType== VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
			
			RB_Dataobject_V2 oDataCollector = (RB_Dataobject_V2) m_trp_hm.getMaskDataObjectsCollector().get(_TAB.lager_box.rb_km());
			
			MASK_STATUS msk_status = oDataCollector.rb_MASK_STATUS();
			
			
			//1) get the actual status if is default box
			boolean is_default_box_selected = RB__TOOLS.find_comp(rbMASK, LAGER_BOX.is_default_box.fk()).get__actual_maskstring_in_view().equals("Y");
			
			//2)get the actual default box id and the box id
			String actual_default_box_id = get_default_box();
			
			//3)if new: was the box a default box
			if((msk_status == MASK_STATUS.NEW) || (msk_status == MASK_STATUS.NEW_COPY)) {
				if(S.isFull(actual_default_box_id) && is_default_box_selected) {
					mv._addAlarm("FEHLER: Ein default Box existiert schon !");
				}
			}else {
				String actual_box_id = oDataCollector.rec21().get_ufs_dbVal(LAGER_BOX.id_lager_box,"");
				if(is_default_box_selected) {
					if(!S.isEmpty(actual_default_box_id)) {
						if(! actual_box_id.equals(actual_default_box_id)) {
							mv._addAlarm("FEHLER: Ein default Box existiert schon !");
						}
					}
				}
			}
		}
		
		return mv;
	}
	
	private String get_default_box() throws myException{
		RecList21 recList = new RecList21(_TAB.lager_box)._fill(new SEL().FROM(_TAB.lager_box).WHERE(new vgl_YN(LAGER_BOX.is_default_box, true)).s());
		if(recList != null && recList.size()==1) {
			return recList.get(0).getUfs(LAGER_BOX.id_lager_box);
		}else return "";
	}
}
