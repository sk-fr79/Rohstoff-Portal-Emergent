package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.Echo2.ListAndMask.List.E2_ListBedienPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;

public class B_LIST_BedienPanel extends E2_ListBedienPanel {

	private  B_LIST_SelectorContainer oLIST_SelectorContainer = null;
			

	public B_LIST_BedienPanel(E2_NavigationList NaviList) throws myException {
		super();
		this.set_oListSelectorContainer(this.oLIST_SelectorContainer=new B_LIST_SelectorContainer(NaviList));
		
	}

	
	public B_LIST_SelectorContainer get_oLIST_SelectorContainer() {
		return oLIST_SelectorContainer;
	}

}
