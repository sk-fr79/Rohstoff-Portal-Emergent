package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__MASK_CompDublette;

public class __TR_SETTING_RESET_DUBETTE extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			((TR__MASK_CompDublette)oMAP.get__Comp(_DB.HANDELSDEF$VERSIONSZAEHLER)).set_cActualMaskValue("0");
		}
		return oMV;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION) {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Dublettenstatus wurde entfernt ...")));
			((TR__MASK_CompDublette)oMAP.get__Comp(_DB.HANDELSDEF$VERSIONSZAEHLER)).set_cActualMaskValue("0");
		}
		return oMV;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

}
