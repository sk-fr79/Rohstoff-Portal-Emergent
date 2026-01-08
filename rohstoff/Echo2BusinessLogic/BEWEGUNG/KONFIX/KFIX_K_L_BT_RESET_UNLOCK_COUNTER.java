package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_BT_LIST_RESET_UNLOCK_COUNTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class KFIX_K_L_BT_RESET_UNLOCK_COUNTER extends BS_K_BT_LIST_RESET_UNLOCK_COUNTER
{

	public KFIX_K_L_BT_RESET_UNLOCK_COUNTER(E2_NavigationList onavigationList,VORGANGSART belegTyp) throws myException
	{
		super(onavigationList,new BS__SETTING(belegTyp));
	}
	
	
}
