package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_BT_LIST_UNLOCK_VORGANG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BST_K_LIST_BT_UNLOCK_VORGANG extends BS_K_BT_LIST_UNLOCK_VORGANG
{

	
	public BST_K_LIST_BT_UNLOCK_VORGANG(E2_NavigationList onavigationList, BS__SETTING oSETTING) throws myException
	{
		super(	onavigationList, 
				oSETTING, 
				new MyE2_String("Entsperre Transportauftrag"), 
				new MyE2_String("Entsperren eines (oder mehrerer) Transportaufträge"),
				"UNLOCK_TPA");
	}

}
