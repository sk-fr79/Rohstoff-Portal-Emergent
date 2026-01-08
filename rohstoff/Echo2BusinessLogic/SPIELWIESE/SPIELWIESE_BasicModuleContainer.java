package rohstoff.Echo2BusinessLogic.SPIELWIESE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.SPIELWIESE.list.SPIELWIESE_LIST_ComponentMap;

public class SPIELWIESE_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public SPIELWIESE_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.SPIELWIESE_LIST);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList e2nl = new E2_NavigationList();
		e2nl.INIT_WITH_ComponentMAP(new SPIELWIESE_LIST_ComponentMap(), E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		//SPIELWIESE_LIST_BedienPanel oPanel = new SPIELWIESE_LIST_BedienPanel(e2nl,new SPIELWIESE_MASK_BasicModuleContainer());
		
		//this.add(oPanel);
		this.add(e2nl, E2_INSETS.I_2_2_2_2);
		
		//oPanel.get_oSPIELWIESE_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
