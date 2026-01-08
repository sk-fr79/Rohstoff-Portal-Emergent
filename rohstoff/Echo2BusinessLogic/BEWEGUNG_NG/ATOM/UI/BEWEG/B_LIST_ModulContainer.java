package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class B_LIST_ModulContainer extends Project_BasicModuleContainer {

	private E2_NavigationList  oNaviList = null;
	
	
	public B_LIST_ModulContainer() throws myException {
		super(E2_MODULNAMES.NAME_MODUL_WARENBEWEGUNG_NG_LIST);
		
		this.oNaviList = new E2_NavigationList();
		
		this.oNaviList.INIT_WITH_ComponentMAP(new B_LIST_ComponentMAP(new B_LIST_SQL_FieldMAP()), null, this.get_MODUL_IDENTIFIER());
		
		B_LIST_BedienPanel oBedienpanel = new B_LIST_BedienPanel(this.oNaviList);
		
		this.add(oBedienpanel,E2_INSETS.I_0_0_0_0);
		this.add(this.oNaviList,E2_INSETS.I_0_0_0_0);
	
		oBedienpanel.get_oLIST_SelectorContainer().get_oSelectionsComponentVector().doActionPassiv();
	}

	
	
}
