package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class __TR_SETTING_TYP_USERINFO extends XX_MAP_Set_And_Valid
{

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this._setting(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this._setting(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this._setting(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return null;
	}
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return null;
	}
	
	
	private MyE2_MessageVector _setting(E2_ComponentMAP oMAP, int ActionType) throws myException
	{		
		MyE2IF__DB_Component  compTypMeldung = oMAP.get__DBComp("TYP_MELDUNG");
	
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION )
		if (S.isEmpty(compTypMeldung.get_cActualDBValueFormated()) || compTypMeldung.get_cActualDBValueFormated().equals(TAX_CONST.MELDUNG_KEINE))
		{
			oMAP.set_ActiveADHOC("MELDUNG_FUER_USER", false, true);
		}
		else
		{
			oMAP.set_ActiveADHOC("MELDUNG_FUER_USER", true, true);
		}
		
		return new MyE2_MessageVector();
		
	}
	
}
