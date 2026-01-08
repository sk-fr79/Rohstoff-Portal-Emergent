/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print;

public class FU_MASK_BT_PRINT_MAIL_BELEG extends ___BUTTON_LIST_BT_Mail_and_Print
{

	public FU_MASK_BT_PRINT_MAIL_BELEG(FU_MASK_ModulContainer oContainerMASK,boolean bViewOnly)
	{
		super(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_MASK(oContainerMASK,bViewOnly),"TRANSPORTAUFTRAGSMODUL");
	}
}