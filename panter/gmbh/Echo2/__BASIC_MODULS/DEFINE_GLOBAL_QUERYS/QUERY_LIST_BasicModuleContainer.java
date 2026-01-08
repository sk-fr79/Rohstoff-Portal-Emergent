package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_MASK_TEILNEHMER =		"NAME_OF_MASK_TEILNEHMER";

	public QUERY_LIST_BasicModuleContainer() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_DEFINEQUERYS_LIST);
		
		this.set_bVisible_Row_For_Messages(true);

		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new QUERY_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2, null);
		QUERY_LIST_BedienPanel oPanel = new QUERY_LIST_BedienPanel(oNaviList,new QUERY_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oNaviList._REBUILD_COMPLETE_LIST("");
	}
		
}
