package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER;

public class BS_VL_MASK__ComponentMAP_MapSettingAgent extends XX_MAP_SettingAgent
{

	@Override
	public void __doSettings_AFTER(E2_ComponentMAP map, String STATUS_MASKE) throws myException
	{
	}

	@Override
	public void __doSettings_BEFORE(E2_ComponentMAP map, String STATUS_MASKE) throws myException
	{
		new BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER("RG_VL",map);
	}

}
