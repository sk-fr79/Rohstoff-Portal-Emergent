package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class USER__LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public USER__LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_USERVERWALTUNG_LISTE.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new USER__LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		USER__LIST_BedienPanel oPanel = new USER__LIST_BedienPanel(oNaviList,new USER__MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oUSER__LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
