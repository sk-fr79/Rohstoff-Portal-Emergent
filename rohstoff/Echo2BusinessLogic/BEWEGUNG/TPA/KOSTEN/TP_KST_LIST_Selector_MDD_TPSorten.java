package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_STD;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_Selector_MDD_TPSorten extends E2_ListSelectorMultiDropDown_STD {

	public TP_KST_LIST_Selector_MDD_TPSorten() throws myException {
		super(new TP_KST_LIST_Selector_MDD_TPSorten_DD(), "JT_VPOS_TPA.ID_ARTIKEL = #WERT#");
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {
		
	}
	

}
