package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_EDIT_VIEW.FZ_AA_edit_view;


public class FZ_LIST_btEdit extends MyE2_Button {

	public FZ_LIST_btEdit(E2_NavigationList  naviList) {
		super(E2_ResourceIcon.get_RI("edit.png") , true);
		
		this.add_oActionAgent(new FZ_AA_edit_view(naviList, true));
	}

}
