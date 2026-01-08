package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_ColorSelect_Button;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class USER_MASK_ComponentMAP_SetAndValid_ColorButtons extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ColorButtons(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ColorButtons(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ColorButtons(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ColorButtons(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ColorButtons(oMAP, ActionType, oExecInfo, oInputMap);
	}

	private MyE2_MessageVector set_ColorButtons(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		E2_ColorSelect_Button  oButtonOK = 		(E2_ColorSelect_Button)oMAP.get__Comp(USER__MASK_ComponentMAP.HASHKEY_SELECT_OK_COLOR);
		E2_ColorSelect_Button  oButtonWARN = 	(E2_ColorSelect_Button)oMAP.get__Comp(USER__MASK_ComponentMAP.HASHKEY_SELECT_WARN_COLOR);
		E2_ColorSelect_Button  oButtonERROR = 	(E2_ColorSelect_Button)oMAP.get__Comp(USER__MASK_ComponentMAP.HASHKEY_SELECT_ERROR_COLOR);
		
		oButtonOK.fill_Anzeige_Mit_Color_FromMask();
		oButtonWARN.fill_Anzeige_Mit_Color_FromMask();
		oButtonERROR.fill_Anzeige_Mit_Color_FromMask();
		
		return oMV;
	}
	
}
