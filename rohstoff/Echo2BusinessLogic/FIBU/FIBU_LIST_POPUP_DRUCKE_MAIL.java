package rohstoff.Echo2BusinessLogic.FIBU;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_LIST_POPUP_DRUCKE_MAIL extends MyE2_PopUpMenue
{

	public FIBU_LIST_POPUP_DRUCKE_MAIL(E2_NavigationList  NaviList, FIBU_LIST_Selector oFibuSelector) throws myException
	{
		super(E2_ResourceIcon.get_RI("print_and_mail_popup.png"), E2_ResourceIcon.get_RI("print_and_mail_popup__.png"), false, null, null, 0, 0);

		this.addButton(new FIBU_LIST_BT_PrintMail_Buchungsblatt(NaviList), true);
		this.addButton(new FIBU_LIST_BT_PrintMail_Buchungsblock(NaviList), true);
		this.addButton(new FIBU_LIST_BT_PrintMail_Saldenliste(oFibuSelector), true);

		//2016-01-18: experimentell
		
		//2016-04-12: jetzt Live
		this.addButton(new FIBU_LIST_BT_PrintMail_Zahlungsavis(NaviList), true);
		this.addButton(new FIBU_LIST_BT_PrintMail_Verrechnungsvereinbarung(NaviList), true);

		//2016-05-23: Kontokorrentabrede
		this.addButton(new FIBU_LIST_BT_PrintMail_Kontokorrentabrede(NaviList), true);
		
	}

}
