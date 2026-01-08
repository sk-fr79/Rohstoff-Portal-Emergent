package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_BT_LIST_UNLOCK_VORGANG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_K_LIST_BT_UNLOCK_VORGANG extends BS_K_BT_LIST_UNLOCK_VORGANG
{

	public BSK_K_LIST_BT_UNLOCK_VORGANG(E2_NavigationList onavigationList,  BS__SETTING oSETTING) throws myException
	{
		super(	onavigationList, 
				oSETTING, 
				new MyE2_String("Entsperre Kontrakt"), 
				new MyE2_String("Entsperren eines (oder mehrerer) Kontrakte"),
				"UNLOCK_KONTRAKT");
	}
	
}
