package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_DATASEARCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_LIST_DATASEARCH extends BS_K_LIST_DATASEARCH
{

	public BSRG_K_LIST_DATASEARCH(	E2_NavigationList 	oNaviList, 
									BS__SETTING			SETTING,
									String	 			MODULE_KENNER) throws myException	{
		super(oNaviList,  SETTING, MODULE_KENNER, null); 
	}
}
