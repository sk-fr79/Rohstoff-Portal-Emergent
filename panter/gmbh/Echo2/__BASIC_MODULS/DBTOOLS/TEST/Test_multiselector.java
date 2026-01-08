package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import java.util.Vector;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.E2_MultiSelector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.XX_ListSelector_4Multiselect;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class Test_multiselector extends E2_MultiSelector {

	public Test_multiselector() throws myException {
		super();
	}


	private class ownBasicModulContainer extends E2_BasicModuleContainer{
		public ownBasicModulContainer() {
		}
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicModulContainer();
	}


	@Override
	public void format_selectorGridInBase(E2_Grid grid_with_selector_part) {
		grid_with_selector_part._s(4)._setSize(150,50,150,50)._bo_ddd();
	}


	@Override
	public void format_gridInPopup(E2_Grid complete_popup_grid_all_selectors_and_buttons) {
		complete_popup_grid_all_selectors_and_buttons._setSize(300,20,20);
	}


	@Override
	public XX_ListSelector_4Multiselect generate_selector_part(XX_ListSelector_4Multiselect source) throws myException {
		XX_ListSelector_4Multiselect selector = source;
		if(source==null){
			selector = new Test_complex_multiselektor();
		}
		return selector;
	}


	@Override
	public String fill_tool_tip() throws myException {
		// TODO Auto-generated method stub
		return null;
	}
}
