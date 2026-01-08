/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 16.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 16.10.2019
 *
 */
public class LH_P_MASK_Set_handbuchung extends RB_Mask_Set_And_Valid {

	/**
	 * @author sebastien
	 * @date 16.10.2019
	 *
	 * @param m_tpHashMap
	 */
	public LH_P_MASK_Set_handbuchung(RB_TransportHashMap m_tpHashMap) {
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
	 */
	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
			MASK_STATUS mskStatus = rbMASK.getRbDataObjectActual().rb_MASK_STATUS();
			if(mskStatus == MASK_STATUS.NEW || mskStatus == MASK_STATUS.NEW_COPY) {
				RB__TOOLS.find_comp(rbMASK,LAGER_PALETTE.ausbuchung_hand.fk()).set_bEnabled_For_Edit(false);
				RB__TOOLS.find_comp(rbMASK,LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fk()).set_bEnabled_For_Edit(false);	
			}
		}

		if(ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
			IF_Field source_if_field = oExecInfo.get_RB_K_of_KLICK_COMPONENT().get_data_field();
			boolean is_source_cmp_selected = ((RB_cb)oExecInfo.get_MyActionEvent().getSource()).isSelected();

			if(LAGER_PALETTE.einbuchung_hand.fk().get_data_field().equals(source_if_field)) {
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).set_bEnabled_For_Edit(is_source_cmp_selected);
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).set_bEnabled_For_Edit(is_source_cmp_selected);
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).set_bEnabled_For_Edit(!is_source_cmp_selected);
				if(is_source_cmp_selected) {

					((LH_P_MASK_Comp_fuhreSearch)RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk())).clear_content();

					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).mark_Neutral();
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).mark_MustField();
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_MustField();
				}else {
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).mark_MustField();
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).mark_Neutral();
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_Neutral();
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_Disabled();

					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).rb_set_db_value_manual("");
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).rb_set_db_value_manual("");
				}
			}else if(LAGER_PALETTE.ausbuchung_hand.fk().get_data_field().equals(source_if_field)) {
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fk()).set_bEnabled_For_Edit(!is_source_cmp_selected);
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.hand_ausbuchung_bemerkung.fk()).set_bEnabled_For_Edit(is_source_cmp_selected);
			}

		}


		return mv;
	}

}
