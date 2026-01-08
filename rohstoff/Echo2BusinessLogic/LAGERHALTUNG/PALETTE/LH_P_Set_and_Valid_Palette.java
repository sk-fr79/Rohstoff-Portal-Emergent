/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 07.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST;

/**
 * @author sebastien
 * @date 07.12.2018
 *
 */
public class LH_P_Set_and_Valid_Palette extends RB_Mask_Set_And_Valid {

	private RB_TransportHashMap m_trp_hm = null;

	public LH_P_Set_and_Valid_Palette(RB_TransportHashMap oTrpHashMap) {
		m_trp_hm = oTrpHashMap;
	}

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();	

		RB_TextField taramenge_tf = (RB_TextField)RB__TOOLS.find_comp(rbMASK, 	LAGER_PALETTE.taramenge.fk());
		RB_TextField bruttomge_tf = (RB_TextField)RB__TOOLS.find_comp(rbMASK, 	LAGER_PALETTE.bruttomenge.fk());
		RB_TextField nettomge_tf = (RB_TextField)RB__TOOLS.find_comp(rbMASK, 	LAGER_PALETTE.nettomenge.fk());
		RB_selField boxSf =  (	RB_selField)RB__TOOLS.find_comp(rbMASK, 		LAGER_PALETTE.id_lager_box.fk());

		String taramge_on_load = "";
		String bruttomge_on_load = "";

		if(ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
			MASK_STATUS msk_status = m_trp_hm.getMaskDataObjectsCollector().get(LH_P_CONST.getLeadingMaskKey()).rb_MASK_STATUS();

			boolean is_einbuch_hand = RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.einbuchung_hand.fk()).get__actual_maskstring_in_view().equals("Y");
			if(is_einbuch_hand) {
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_MustField();
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).mark_MustField();
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).set_bEnabled_For_Edit(false);
			}else {
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).set_bEnabled_For_Edit(false);
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).set_bEnabled_For_Edit(false);
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).mark_MustField();
			}	
			if(msk_status == MASK_STATUS.NEW) {
				if(m_trp_hm.getLeadingTableOnMask() == _TAB.lager_palette) {
					if(m_trp_hm.getMotherTransportHashMap() != null) {
						if(m_trp_hm.getMotherKeyValue() != null) {
							boxSf.rb_set_db_value_manual(""+bibALL.convertID2FormattedID(""+m_trp_hm.getMotherKeyValue()));
						}
					}
				}

			}

			if(msk_status == MASK_STATUS.NEW_COPY) {
				if(m_trp_hm.getLeadingTableOnMask() == _TAB.lager_palette) {
					if(m_trp_hm.getMotherTransportHashMap() != null) {
						String fuhre_id = (String) m_trp_hm.getMotherTransportHashMap().getFromExtender(LH_CONST.LH_EXTENDER.LH_FUHRE_ID);

						if(S.isFull(fuhre_id)) {
							String id_artikel_bez = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(fuhre_id).getUfs(VPOS_TPA_FUHRE.id_artikel_bez_vk);

							RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).rb_set_db_value_manual(fuhre_id);

							RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).rb_set_db_value_manual(id_artikel_bez);
						}
					}

					if(m_trp_hm.getMotherKeyValue() != null) {
						boxSf.rb_set_db_value_manual(""+bibALL.convertID2FormattedID(""+m_trp_hm.getMotherKeyValue()));
					}
				}
			}

			if(msk_status == MASK_STATUS.NEW_COPY || msk_status == MASK_STATUS.NEW) {
				if(S.isEmpty(get_default_box())) {
					mv._addAlarm("Es gibt kein Standard-Box ! Bitte definieren!");
				}
			}

			taramge_on_load = taramenge_tf.get__actual_maskstring_in_view();
			bruttomge_on_load = bruttomge_tf.get__actual_maskstring_in_view();

			if(S.isEmpty(taramge_on_load)) {
				taramenge_tf.rb_set_db_value_manual("0");
				nettomge_tf.rb_set_db_value_manual(bruttomge_on_load);
			}

		}

		if(ActionType== VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
			new_buchungs_validation_rule(rbMASK, mv);
		}


		if(ActionType== VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
			if(S.isFull(bruttomge_tf.get__actual_maskstring_in_view())) {
				menge_berechnung(rbMASK, mv, taramenge_tf, bruttomge_tf, nettomge_tf);
			}

		}

		return mv;
	}

	private void new_buchungs_validation_rule(RB_ComponentMap rbMASK, MyE2_MessageVector mv) throws myException {
		RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk())			.mark_Neutral();
		RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk())			.mark_Neutral();
		RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk())	.mark_Neutral();


		boolean is_einbuch_hand = RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.einbuchung_hand.fk()).get__actual_maskstring_in_view().equals("Y");
		String buchungsnr_hand = RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).get__actual_maskstring_in_view();
		String artikelbez = RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).get__actual_maskstring_in_view();
		String id_fuhre = RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).get__actual_maskstring_in_view();

		if(is_einbuch_hand) {
			RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_MustField();
			RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).mark_MustField();

			if(! S.isAllFull(buchungsnr_hand, artikelbez)) {
				if(S.isEmpty(buchungsnr_hand)) {
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_FalseInput();
					mv._addAlarm("<"+LAGER_PALETTE.buchungsnr_hand.tnfn()+"> Fehler: Eingabe darf nicht leer sein !");
				}
				if(S.isEmpty(artikelbez)) {
					RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_artikel_bez.fk()).mark_FalseInput();
					mv._addAlarm("<"+LAGER_PALETTE.id_artikel_bez.tnfn()+"> Fehler: Eingabe darf nicht leer sein !");
				}
			}
		}else {
			RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.buchungsnr_hand.fk()).mark_MustField();
			if(S.isEmpty(id_fuhre)) {
				RB__TOOLS.find_comp(rbMASK, LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk()).mark_FalseInput();
				mv._addAlarm("<"+LAGER_PALETTE.id_vpos_tpa_fuhre_ein.tnfn()+"> Fehler: Eingabe darf nicht leer sein !");
			}
		}
	}

	private void menge_berechnung(RB_ComponentMap rbMASK, MyE2_MessageVector mv, RB_TextField taramenge_tf, RB_TextField bruttomge_tf, RB_TextField nettomge_tf) throws myException {

		RB__TOOLS.find_comp(rbMASK, new RB_KF(LAGER_PALETTE.taramenge)).mark_Neutral();
		RB__TOOLS.find_comp(rbMASK, new RB_KF(LAGER_PALETTE.bruttomenge)).mark_Neutral();
		RB__TOOLS.find_comp(rbMASK, new RB_KF(LAGER_PALETTE.nettomenge)).mark_Neutral();

		String tar_mge = taramenge_tf.get__actual_maskstring_in_view(); 
		String bru_mge = bruttomge_tf.get__actual_maskstring_in_view();
		String net_mge = nettomge_tf.get__actual_maskstring_in_view();

		if(S.isEmpty(tar_mge)){
			tar_mge="0";
		}

		BigDecimal bd_bru = 	new MyBigDecimal(bru_mge, BigDecimal.ZERO, BigDecimal.ZERO, true).get_bdWert();
		BigDecimal bd_tar = 	new MyBigDecimal(tar_mge, BigDecimal.ZERO, BigDecimal.ZERO, true).get_bdWert();
		BigDecimal bd_netto = 	new MyBigDecimal(net_mge, BigDecimal.ZERO, BigDecimal.ZERO, true).get_bdWert();

		if(S.isFull(tar_mge)) {
			bd_netto = bd_bru.subtract(bd_tar);
		}

		if(((bd_tar.longValue()>bd_bru.longValue()) || (bd_tar.longValue()>bd_netto.longValue())) && bd_bru.longValue()>0) {
			RB__TOOLS.find_comp(rbMASK, new RB_KF(LAGER_PALETTE.taramenge)).mark_FalseInput();
			mv._addAlarm("Die Taramenge ist zu gross");
		}else if(bd_bru.longValue()<0) {
			RB__TOOLS.find_comp(rbMASK, new RB_KF(LAGER_PALETTE.bruttomenge)).mark_FalseInput();
			mv._addAlarm("Die Bruttomenge ist negativ");
		}else  if(bd_netto.longValue()<0) {
			RB__TOOLS.find_comp(rbMASK, new RB_KF(LAGER_PALETTE.nettomenge)).mark_FalseInput();
			mv._addAlarm("Die Nettomenge ist negativ");
		}else {
			bruttomge_tf.rb_set_db_value_manual(new MyBigDecimal(bd_bru).get_FormatedRoundedNumber(3));
			taramenge_tf.rb_set_db_value_manual(new MyBigDecimal(bd_tar).get_FormatedRoundedNumber(3));
			nettomge_tf.rb_set_db_value_manual(new MyBigDecimal(bd_netto).get_FormatedRoundedNumber(3));

			mv._addInfo("Die Mengen wurden neu gerechnet !");
		}
	}


	private String get_default_box() throws myException{
		RecList21 recList = new RecList21(_TAB.lager_box)._fill(new SEL().FROM(_TAB.lager_box).WHERE(new vgl_YN(LAGER_BOX.is_default_box, true)).s());
		if(recList != null && recList.size()==1) {
			return recList.get(0).getUfs(LAGER_BOX.id_lager_box);
		}else return "";
	}

	//		}
}

