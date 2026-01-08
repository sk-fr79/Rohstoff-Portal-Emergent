package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.E2_MultiSelector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection.XX_ListSelector_4Multiselect;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selector_Land_2 extends E2_MultiSelector {

	public FU_Selector_Land_2() throws myException {
		super();
	}	

	@Override
	public String fill_tool_tip() throws myException {
		String erw_auswahl = "";

		for(XX_ListSelector_4Multiselect selector: this.get_vectorOfSelectors()){

			MyE2_SelectField selField_von 	= (MyE2_SelectField) ((FU_Selector_Land_von_nach)selector).get_select_field_von().get_oComponentForSelection();
			MyE2_SelectField selField_nach 	= (MyE2_SelectField) ((FU_Selector_Land_von_nach)selector).get_select_field_nach().get_oComponentForSelection();

			if(! selField_von.get_ActualView().equals("-")){
				erw_auswahl = erw_auswahl + "von " + 	selField_von.get_ActualView() + " ";
			}

			if(! selField_nach.get_ActualView().equals("-")){
				erw_auswahl = erw_auswahl + "nach " + 	selField_nach.get_ActualView();
			}

			erw_auswahl = erw_auswahl + "\n";
		}
		return erw_auswahl;
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicModulContainer();
	}

	@Override
	public void format_selectorGridInBase(E2_Grid grid_with_selector_part) throws myException {	
		grid_with_selector_part._setSize(458,30);
	}

	@Override
	public void format_gridInPopup(E2_Grid complete_popup_grid_all_selectors_and_buttons) {
		complete_popup_grid_all_selectors_and_buttons._setSize(300,20,20);
	}


	@Override
	public XX_ListSelector_4Multiselect generate_selector_part(XX_ListSelector_4Multiselect source) throws myException {
		XX_ListSelector_4Multiselect selector = new FU_Selector_Land_von_nach();

		if(source!=null){

			MyE2_SelectField source_von = 	(MyE2_SelectField) ((FU_Selector_Land_von_nach)source).get_select_field_von().get_oComponentForSelection();
			MyE2_SelectField source_nach = 	(MyE2_SelectField) ((FU_Selector_Land_von_nach)source).get_select_field_nach().get_oComponentForSelection();

			((MyE2_SelectField)((FU_Selector_Land_von_nach)selector).get_select_field_von().get_oComponentForSelection()).set_ActiveValue(source_von.get_ActualWert());
			((MyE2_SelectField)((FU_Selector_Land_von_nach)selector).get_select_field_nach().get_oComponentForSelection()).set_ActiveValue(source_nach.get_ActualWert());

		}

		return selector;
	}


	private class ownBasicModulContainer extends E2_BasicModuleContainer{
		public ownBasicModulContainer() {
		}
	}
}
