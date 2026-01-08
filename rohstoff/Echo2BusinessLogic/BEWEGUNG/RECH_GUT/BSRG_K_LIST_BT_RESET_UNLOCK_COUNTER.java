package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_BT_LIST_RESET_UNLOCK_COUNTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_LIST_BT_RESET_UNLOCK_COUNTER extends BS_K_BT_LIST_RESET_UNLOCK_COUNTER
{

	public BSRG_K_LIST_BT_RESET_UNLOCK_COUNTER(E2_NavigationList onavigationList, BS__SETTING oSETTING) throws myException
	{
		super(onavigationList,oSETTING); 
	}
	
}
