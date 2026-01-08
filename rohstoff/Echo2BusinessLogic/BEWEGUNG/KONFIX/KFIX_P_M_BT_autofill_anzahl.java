package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_P_M_BT_autofill_anzahl extends E2_Button implements IF_RB_Component {



	public KFIX_P_M_BT_autofill_anzahl(boolean miniIcon) {
		super();

		E2_ResourceIcon icon = 			miniIcon? E2_ResourceIcon.get_RI("wizard_mini.png"): E2_ResourceIcon.get_RI("wizard.png");
		E2_ResourceIcon icon_disabled = miniIcon? E2_ResourceIcon.get_RI("wizard_mini__.png"): E2_ResourceIcon.get_RI("wizard__.png");

		this.setIcon(icon);
		this.setDisabledIcon(icon_disabled);

		this._aaa(new ownActionAgent());
	}

	private void update_anzahl_rbTf(String get_FormatedRoundedNumber) throws myException {
		IF_RB_Component anzahl_tf = this.rb_ComponentMap_this_belongsTo()._find_component_in_neighborhood(VPOS_KON.anzahl);
		if(S.isEmpty(anzahl_tf.get__actual_maskstring_in_view())){
			this.rb_ComponentMap_this_belongsTo()._find_component_in_neighborhood(VPOS_KON.anzahl).rb_set_db_value_manual(get_FormatedRoundedNumber);	
		}
	}


	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			RB_ComponentMap compMap = KFIX_P_M_BT_autofill_anzahl.this.rb_ComponentMap_this_belongsTo();

			String id_vpos_kon = ((RB_TextField)compMap.getComp(VPOS_KON.id_vpos_kon)).rb_readValue_4_dataobject();

			String id_vkopf_kon = ((RB_TextField)compMap.getComp(VPOS_KON.id_vkopf_kon)).rb_readValue_4_dataobject();

			Rec20_VKOPF_KON ext_rec20_vkopf = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(id_vkopf_kon);

			MyBigDecimal rest_to_fix = new MyBigDecimal("0");

			if(ext_rec20_vkopf.is_fixierungsKontrakte()){

				MyBigDecimal gesamt_fix_menge = ext_rec20_vkopf.get_fixierung_gesamt_menge();

				MyBigDecimal schon_fixiert_mge = ext_rec20_vkopf.get_aktuelle_fixiert_menge();

				if(S.isEmpty(id_vpos_kon)){
					rest_to_fix = gesamt_fix_menge.subtract_from_me(schon_fixiert_mge);
				}else{
					MyBigDecimal this_mge = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon).get_myBigDecimal_dbVal(VPOS_KON.anzahl, new MyBigDecimal("0"));

					rest_to_fix = gesamt_fix_menge.subtract_from_me(schon_fixiert_mge.subtract_from_me(this_mge));
				}
				update_anzahl_rbTf(rest_to_fix.get_FormatedRoundedNumber(0));

			}
		}
	}


}
