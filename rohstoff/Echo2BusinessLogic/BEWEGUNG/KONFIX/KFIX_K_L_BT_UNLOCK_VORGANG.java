package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_BT_LIST_UNLOCK_VORGANG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class KFIX_K_L_BT_UNLOCK_VORGANG extends BS_K_BT_LIST_UNLOCK_VORGANG
{

	public KFIX_K_L_BT_UNLOCK_VORGANG(E2_NavigationList onavigationList, VORGANGSART belegTyp) throws myException
	{
		super(	onavigationList, 
				new BS__SETTING(belegTyp), 
				new MyE2_String("Entsperre Kontrakt"), 
				new MyE2_String("Entsperren eines (oder mehrerer) Kontrakte"),
				"UNLOCK_KONTRAKT");
	}
	
}
