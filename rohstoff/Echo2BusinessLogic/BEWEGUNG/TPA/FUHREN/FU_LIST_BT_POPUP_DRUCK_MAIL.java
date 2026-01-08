package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT.BGL_LIST_bt_BelegGrenzUbertritt;

public class FU_LIST_BT_POPUP_DRUCK_MAIL extends MyE2_PopUpMenue {
	
	public FU_LIST_BT_POPUP_DRUCK_MAIL(E2_NavigationList  NaviList) throws myException {
		super(E2_ResourceIcon.get_RI("print_and_mail_popup.png"), E2_ResourceIcon.get_RI("print_and_mail_popup__.png"), false, null, null, 0, 0);
	
		this.addButton(new BGL_LIST_bt_BelegGrenzUbertritt(NaviList), true);
	}
	

}
