package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class TR__LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public TR__LIST_BasicModuleContainer(String cWhereBlock) throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.MODUL_TAXRULES_LIST.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		TR__NaviList oNaviList = new TR__NaviList();
		
		/*
		 * 2019-02-18: navilist verbreiterbar machen 
		 */
		oNaviList.setExtendWidthWithSumOfAllRowWidth(true);

		
		
		oNaviList.INIT_WITH_ComponentMAP(new TR__LIST_ComponentMap(cWhereBlock),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		TR__LIST_BedienPanel oPanel = new TR__LIST_BedienPanel(oNaviList,new TR__MASK_BasicModuleContainer(oNaviList), this);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oTR__LIST_Selector().get_oSelVector().doActionPassiv();
		
		
	}
		
}
