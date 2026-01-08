package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print;

public class FU_LIST_BT_PRINT_MAIL_BELEG extends	___BUTTON_LIST_BT_Mail_and_Print
{

	public FU_LIST_BT_PRINT_MAIL_BELEG(E2_NavigationList oNaviList)
	{
		super(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_LIST(oNaviList),"TRANSPORTAUFTRAGSMODUL");
		
	}


}
