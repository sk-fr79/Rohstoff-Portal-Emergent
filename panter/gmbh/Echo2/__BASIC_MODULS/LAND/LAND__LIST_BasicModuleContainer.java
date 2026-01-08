package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class LAND__LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =			"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public LAND__LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAENDER_LIST);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.set_bSaveSortStatus(true);
		oNaviList.INIT_WITH_ComponentMAP(new LAND__LIST_ComponentMap(this.get_MODUL_IDENTIFIER()),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		LAND__LIST_BedienPanel oPanel = new LAND__LIST_BedienPanel(oNaviList,new LAND__MASK_BasicModuleContainer(), this);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oLAND__LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
