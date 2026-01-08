package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class AH7_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public AH7_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new AH7_LIST_ComponentMap(oNaviList),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		AH7_LIST_BedienPanel oPanel = new AH7_LIST_BedienPanel(oNaviList);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oAH7_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
