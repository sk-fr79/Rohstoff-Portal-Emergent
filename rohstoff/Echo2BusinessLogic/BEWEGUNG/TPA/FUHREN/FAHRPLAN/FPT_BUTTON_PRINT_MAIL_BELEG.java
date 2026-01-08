package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print;

public class FPT_BUTTON_PRINT_MAIL_BELEG extends	___BUTTON_LIST_BT_Mail_and_Print
{

	public FPT_BUTTON_PRINT_MAIL_BELEG(E2_NavigationList oNaviList)
	{
		super(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_LIST(oNaviList),"FAHRPLANMODUL");
//		this.add_IDValidator(new ownValidator());
	}

	//aenderung 2010-12-22: Validierung erfolgt in der Superklasse
//	private class ownValidator extends XX_ActionValidator
//	{
//		@Override
//		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
//		{
//			return null;
//		}
//
//		@Override
//		protected MyE2_MessageVector isValid(String unformated)	throws myException
//		{
//			MyE2_MessageVector  oMV = new MyE2_MessageVector();
//			
//			RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(unformated);
//			
//			if (recFuhre.is_FUHRE_KOMPLETT_NO())
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es koennen nur Belege von vollständigen Fuhren erzeugt werden !")));
//			}
//			
//			return oMV;
//		}
//		
//	}
	
}
