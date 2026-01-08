package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class SEL_LIST_BasicModuleContainer extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	
	
	public SEL_LIST_BasicModuleContainer(E2_BasicModuleContainer CallingModuleContainer) throws myException
	{
		super();
		
		this.set_MODUL_IDENTIFIER(E2_MODULNAMES.MODUL_SELEKTORDEF_LIST);
		this.set_bVisible_Row_For_Messages(false);
		
		E2_NavigationList oNaviList = 		new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(	new SEL_LIST_ComponentMap(CallingModuleContainer),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		SEL_LIST_BedienPanel oPanel = 		new SEL_LIST_BedienPanel(oNaviList,new SEL_MASK_BasicModuleContainer(CallingModuleContainer));
		
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oNaviList._REBUILD_COMPLETE_LIST("");
	}
		
}
