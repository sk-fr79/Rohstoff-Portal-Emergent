package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;

public class FU_Set_And_Valid_Alt_Lieferbedingung extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = this.pruefe_status_for_MaskLoad(oMAP, ActionType, oExecInfo, oInputMap);
		return oMV;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = this.pruefe_status_for_MaskLoad(oMAP, ActionType, oExecInfo, oInputMap);
		return oMV;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = this.pruefe_status_for_MaskLoad(oMAP, ActionType, oExecInfo, oInputMap);
		return oMV;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = this.pruefe_status_for_MaskLoad(oMAP, ActionType, oExecInfo, oInputMap);
		return oMV;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = this.pruefe_status_for_MaskLoad(oMAP, ActionType, oExecInfo, oInputMap);
		return oMV;
	}

	private MyE2_MessageVector pruefe_status_for_MaskLoad(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {

		MyE2_MessageVector oMV = new MyE2_MessageVector();

		if (ActionType == XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			FU_AL_Component oLFB_Comp_EK = (FU_AL_Component) oMAP.get__Comp(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK);
			oLFB_Comp_EK.clear_Lieferbedingungen();
			oLFB_Comp_EK.refreshLieferbedingung();
			
			FU_AL_Component oLFB_Comp_VK = (FU_AL_Component) oMAP.get__Comp(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK);
			oLFB_Comp_VK.clear_Lieferbedingungen();
			oLFB_Comp_VK.refreshLieferbedingung();

		}

		return oMV;

	}


}
