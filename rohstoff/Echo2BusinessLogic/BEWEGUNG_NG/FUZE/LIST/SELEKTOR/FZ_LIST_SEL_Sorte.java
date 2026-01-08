package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorPosHelperTop;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST_SEL_Sorte extends  E2_ListSelectorMultiDropDown2 {
	
	public FZ_LIST_SEL_Sorte() throws myException {
		super();
		
		SEL sql = new SEL("JT_ARTIKEL.ARTBEZ1||' ('||JT_ARTIKEL.ANR1||')'")
					.ADDFIELD(BEWEGUNG_ATOM.id_artikel.t())
					.FROM(_TAB.bewegung_atom)
					.LEFTOUTER(_TAB.artikel, BEWEGUNG_ATOM.id_artikel, ARTIKEL.id_artikel)
					.ADD_Distinct()
					.ORDERUP("1");
		
		//DEBUG.System_println(sql.s());
		
		MyE2_SelectField  sel4Select = new MyE2_SelectField(sql.s(), false, true, false, false);
		this.INIT(sel4Select, "ATS.ID_ARTIKEL=#WERT#", null);
		this.set_extOfSelectComponentDropDown(new Extent(200));
	}


	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {}
	
	
	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}

}
