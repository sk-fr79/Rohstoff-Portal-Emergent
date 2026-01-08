package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

public class __TR_SETTING_AKTIV extends XX_MAP_Set_And_Valid
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
		return this._setting(oMAP, ActionType);
	}
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return null;
	}
	
	
	private MyE2_MessageVector _setting(E2_ComponentMAP oMAP, int ActionType) throws myException
	{		
		MyE2_DB_CheckBox  cb_aktiv = (MyE2_DB_CheckBox)oMAP.get__DBComp(HANDELSDEF.aktiv.fn());
		
		boolean is_selected = cb_aktiv.isSelected();
		
		Color  colHighlight = new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT();

		LayoutDataFactory.change_gridLayoutData(cb_aktiv, is_selected?colHighlight:new E2_ColorDDark());
		
		return new MyE2_MessageVector();
		
	}
	
}
