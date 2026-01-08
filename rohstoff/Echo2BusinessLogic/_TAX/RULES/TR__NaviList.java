package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;


public class TR__NaviList extends E2_NavigationList {

	public TR__NaviList() throws myException {
		super();
		
		this.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(this));
;
	}

}
