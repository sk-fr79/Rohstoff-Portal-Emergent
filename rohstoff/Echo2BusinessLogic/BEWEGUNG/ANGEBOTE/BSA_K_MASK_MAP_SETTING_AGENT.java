package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_KOPF_WAEHRUNGSSETTER;

public class BSA_K_MASK_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException
	{
		new BS_MASK_KOPF_WAEHRUNGSSETTER("STD",oMap);
	}

}
