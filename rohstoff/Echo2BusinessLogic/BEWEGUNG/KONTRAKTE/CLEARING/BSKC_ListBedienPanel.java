package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_ListBedienPanel extends MyE2_Grid 
{

	public BSKC_ListBedienPanel(BSKC_ModulContainerLIST modulContainerList,String cEK_VK) throws myException
	{
		super(14,MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(modulContainerList.get_oNavigationList()),
					E2_INSETS.I_0_0_10_0);
		
		this.add(new REP__POPUP_Button(modulContainerList.get_MODUL_IDENTIFIER(),modulContainerList.get_oNavigationList()),
					E2_INSETS.I_0_0_10_0);

		this.add(new BSKC_LIST_DATASEARCH(modulContainerList.get_oNavigationList(),modulContainerList.get_MODUL_IDENTIFIER(), cEK_VK),
					E2_INSETS.I_0_0_10_0);
		
		 
	}

	
	

	
	
}
