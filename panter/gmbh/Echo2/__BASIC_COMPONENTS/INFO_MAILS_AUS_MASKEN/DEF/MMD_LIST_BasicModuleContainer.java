package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class MMD_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public MMD_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_MAIL_AUS_MASK_DEF__LIST);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new MMD_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		MMD_LIST_BedienPanel oPanel = new MMD_LIST_BedienPanel(oNaviList,new MMD_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oMMD_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
