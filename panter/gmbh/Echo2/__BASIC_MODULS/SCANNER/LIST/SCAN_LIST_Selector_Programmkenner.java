package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_CONST;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_Selector_Programmkenner extends E2_ListSelectorMultiDropDown2 {

	
	
	public SCAN_LIST_Selector_Programmkenner() throws myException {
		super();
		
		this.INIT(new OwnSelectField_ProgrammKenner(), SCANNER_SETTINGS.programm_kenner.fn()+"='#WERT#'", null);
		this.set_extOfSelectComponentDropDown(new Extent(200));
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return new E2_Grid4MaskSimple()
			    .def_(new Alignment(Alignment.LEFT, Alignment.CENTER))
				.def_(40)
				.add_(new MyE2_Label(new MyE2_String("Programm")))
				.def_(120)
				.add_(this.get_oComponentWithoutText())
				;
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {

		public ownBasicContainer() {
			super();
		}
		
	}
	
	
	private class OwnSelectField_ProgrammKenner extends MyE2_SelectField {

		public OwnSelectField_ProgrammKenner() throws myException {
			super();
			this.populateCombobox(SCAN_CONST.SCAN_PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_dd_Array(false),null, null, true, false);
		}
		
	}

	
}
