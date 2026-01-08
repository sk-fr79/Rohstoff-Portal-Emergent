package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;

public class TR__MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	

	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{
		
//		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) || STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY) )
//		{
//			new TR_MASK_ACTION_ART_INFO(oMap);
//		}
		
	}
	
	private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
	{
//		for (int i=0;i<cFieldList.length;i++)
//		{
//			oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
//		}
	}

}
