package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.bibALL;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG.FS_BT_DruckMail_EU_VERTRAG;



/**
 * @author martin 
 * allgemeiner platz fuer alle moeglichen listenbasierten methoden und funktionen
 */
public class FS_BT_POPUP_BoundPrints extends MyE2_PopUpMenue {

	public FS_BT_POPUP_BoundPrints(E2_NavigationList  oNaviList) 
	{
		super(E2_ResourceIcon.get_RI("print_popup.png"),E2_ResourceIcon.get_RI("print_popup__.png"),false);

		this.setToolTipText(new MyE2_String("Druckvorlagen, adressengebunden").CTrans());
		// 2016-04-26: Neuen EU vertrag in Echt System
		//		this.addButton(new FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG(oNaviList), true);

		// 2016-03-26: neue modus für die EU-Vertrag 
		this.addButton(new FS_BT_DruckMail_EU_VERTRAG("Drucke EU-Vertrag", oNaviList), true);

	}


}
