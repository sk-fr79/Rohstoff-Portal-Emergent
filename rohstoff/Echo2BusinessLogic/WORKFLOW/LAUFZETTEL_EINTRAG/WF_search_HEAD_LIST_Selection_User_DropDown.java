package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.indep.bibALL;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_Selection_User_DropDown;

public class WF_search_HEAD_LIST_Selection_User_DropDown {

	private WF_HEAD_LIST_Selection_User_DropDown  super_selektor = null;
	
	public WF_search_HEAD_LIST_Selection_User_DropDown() {
		super();
		this.super_selektor = this.find_user_from_Superselection();
	}
	
	private WF_HEAD_LIST_Selection_User_DropDown find_user_from_Superselection() {
		WF_HEAD_LIST_Selection_User_DropDown ret = null;
		
		// suchen nach der User-Selektion WF_HEAD_LIST_Selection_User_DropDown
		E2_RecursiveSearch_AB_Basis oSearchComps = new E2_RecursiveSearch_AB_Basis(bibALL.get_Vector(WF_HEAD_LIST_Selection_User_DropDown.class.getName()));
		Vector<Component> vResult = oSearchComps.get_vAllComponents();
		if (vResult.size() > 0){
			ret = (WF_HEAD_LIST_Selection_User_DropDown)vResult.get(0);
		}
		
		return ret;
	}

	
	public WF_HEAD_LIST_Selection_User_DropDown get_HEAD_LIST_Selection_User_DropDown() {
		return super_selektor;
	}

}
