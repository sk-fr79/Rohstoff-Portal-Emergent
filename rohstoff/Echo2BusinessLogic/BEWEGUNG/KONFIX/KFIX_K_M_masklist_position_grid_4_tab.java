package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import echopointng.Separator;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_masklist_position_grid_4_tab extends E2_Grid {

	public KFIX_K_M_masklist_position_grid_4_tab(KFIX_K_M_ComponentMap  map, E2_NavigationList naviList) throws myException {
		super();

		KFIX_K_M_masklist_position daughter_tabelle = (KFIX_K_M_masklist_position) map.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.DAUGHTERTABLE_POSITION.name());
		
		daughter_tabelle.set_navigation_list(naviList);
		
		RB_gld gld = new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(2);

		this._s(1)
		._a(daughter_tabelle.getSimpleSelector(),		gld)	
//		._a(new Separator(),				 			gld)
		._a(daughter_tabelle.get_me_in_container_ex(), 	gld);
	}
}
