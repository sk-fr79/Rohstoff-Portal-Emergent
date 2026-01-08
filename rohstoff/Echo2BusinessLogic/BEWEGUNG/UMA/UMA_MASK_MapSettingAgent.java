package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class UMA_MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	

	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{
		if (oMap.get_bIs_Edit())
		{
			SQLResultMAP oUsedResultMAP = oMap.get_oInternalSQLResultMAP();
			//wenn die maske abgeschlossen ist, dann wird alles disabled geschaltet
			if (oUsedResultMAP.get_booleanActualValue("ABGESCHLOSSEN"))
			{
				oMap.set_bEnabled_For_Edit_and_set_DisabledFromInteractiveFlag_ALL(false);
			}
		}

	}
	
	private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
	{
		for (int i=0;i<cFieldList.length;i++)
		{
			oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
		}
	}

}
