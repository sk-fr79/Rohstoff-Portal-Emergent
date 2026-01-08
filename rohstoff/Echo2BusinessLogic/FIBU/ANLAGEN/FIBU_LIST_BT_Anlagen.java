package rohstoff.Echo2BusinessLogic.FIBU.ANLAGEN;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class FIBU_LIST_BT_Anlagen extends E2_ButtonUpDown_NavigationList_to_Archiv {
	
	private  E2_NavigationList  naviList = null;

	public FIBU_LIST_BT_Anlagen(E2_NavigationList naviList) {
		super(naviList,E2_MODULNAMES.NAME_MODUL_FIBU_LIST);
		this.naviList = naviList;
	}
	
	
	
}
