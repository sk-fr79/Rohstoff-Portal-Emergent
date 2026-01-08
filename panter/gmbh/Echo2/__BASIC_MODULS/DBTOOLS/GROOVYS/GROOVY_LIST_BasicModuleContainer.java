package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class GROOVY_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	
	
	public GROOVY_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.MODUL_GROOVYDEF_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new GROOVY_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		GROOVY_LIST_BedienPanel oPanel = new GROOVY_LIST_BedienPanel(oNaviList,new GROOVY_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oNaviList._REBUILD_COMPLETE_LIST("");
	}
		
}
