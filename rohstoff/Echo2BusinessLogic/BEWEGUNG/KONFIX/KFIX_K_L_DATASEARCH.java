package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_DATASEARCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class KFIX_K_L_DATASEARCH extends BS_K_LIST_DATASEARCH
{

	public KFIX_K_L_DATASEARCH(	E2_NavigationList 	oNaviList, 
										VORGANGSART	oBelegTyp,
										String MODULE_KENNER) throws myException 	{
		super(oNaviList, new BS__SETTING(oBelegTyp), MODULE_KENNER, null);
	}

}
