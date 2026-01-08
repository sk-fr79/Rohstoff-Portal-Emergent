package rohstoff.Echo2BusinessLogic._TAX.RATE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class TAX__LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public TAX__LIST_BasicModuleContainer(String cWhereBlock) throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.MODUL_TAX_LIST.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new TAX__LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		TAX__LIST_BedienPanel oPanel = new TAX__LIST_BedienPanel(oNaviList,new TAX__MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		oNaviList._REBUILD_COMPLETE_LIST("");
		
//		oPanel.get_oTAX__LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
