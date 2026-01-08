package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class MS_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public MS_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new MS_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		MS_LIST_BedienPanel oPanel = new MS_LIST_BedienPanel(oNaviList,new MS_MASK_BasicModuleContainer(), this.get_MODUL_IDENTIFIER());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oMS_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
