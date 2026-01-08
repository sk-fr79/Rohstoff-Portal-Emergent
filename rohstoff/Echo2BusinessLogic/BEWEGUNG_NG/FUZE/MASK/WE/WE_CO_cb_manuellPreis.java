package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class WE_CO_cb_manuellPreis extends RB_cb {

	public WE_CO_cb_manuellPreis() throws myException {
		super();
		this._t(new MyE2_String("manuell"))._fsa(-2)._ttt(new MyE2_String("Preis wird manuell für diese Warenbewegung festgelegt"));
		this._aaa(new ownAction());
	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WE_CO_cb_manuellPreis oThis = WE_CO_cb_manuellPreis.this;
			boolean b_is_editable = true;
			
    		if (S.isFull(((IF_RB_Component_Savable)oThis._find_component_in_neighborhood(BEWEGUNG_ATOM.id_vpos_kon)).rb_readValue_4_dataobject()) ||
        		S.isFull(((IF_RB_Component_Savable)oThis._find_component_in_neighborhood(BEWEGUNG_ATOM.id_vpos_std)).rb_readValue_4_dataobject())) {
    			b_is_editable=false || oThis.isSelected();
       		}
    		
			((IF_RB_Component_Savable)oThis._find_component_in_neighborhood(BEWEGUNG_ATOM.e_preis)).set_bEnabled_For_Edit(b_is_editable);
		}
	}
	
}
