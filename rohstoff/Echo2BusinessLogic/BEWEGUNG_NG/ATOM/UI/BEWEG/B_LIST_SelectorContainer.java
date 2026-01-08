package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.indep.exceptions.myException;

public class B_LIST_SelectorContainer extends E2_ListSelectorContainer {

	private E2_NavigationList 					oNaviList = null;
	private B_LIST_SelectionComponentsVector 	oSelectionsComponentVector = null;
	

	public B_LIST_SelectorContainer(E2_NavigationList NaviList) throws myException {
		super();
		
		this.oNaviList = NaviList;
		this.oSelectionsComponentVector = new B_LIST_SelectionComponentsVector(this.oNaviList);
		
		this.add(this.oSelectionsComponentVector.get_oGridWithSelectors());
		
	}

	
	public B_LIST_SelectionComponentsVector get_oSelectionsComponentVector() {
		return oSelectionsComponentVector;
	}
	
	
}
