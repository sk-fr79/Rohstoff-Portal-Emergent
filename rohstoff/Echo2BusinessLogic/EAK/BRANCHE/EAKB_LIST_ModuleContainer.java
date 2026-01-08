package rohstoff.Echo2BusinessLogic.EAK.BRANCHE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;

public class EAKB_LIST_ModuleContainer extends E2_BasicModuleContainer
{

	private MyE2_Row oBedienPanel = new MyE2_Row(MyE2_Row.STYLE_3D_BORDER());
	
	
	public EAKB_LIST_ModuleContainer(EAK_BasicModuleContainer oTopContainer) throws myException
	{
		super();
		//this.set_MODUL_IDENTIFIER(E2_MODULNAMES.NAME_MODUL_EAK+"_BRANCHE");
		//2015-10-08: neue benennung
		this.set_MODUL_IDENTIFIER(E2_MODULNAME_ENUM.MODUL.MODUL_EAK_BRANCHE.get_callKey());
		this.set_bVisible_Row_For_Messages(false);

		
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		
		EAKB_LIST_COMPONENT_MAP oMap = new EAKB_LIST_COMPONENT_MAP(oTopContainer, oNaviList);
	
		oNaviList.INIT_WITH_ComponentMAP(oMap,null, null);
		
		this.oBedienPanel.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), E2_INSETS.I_0_2_5_2);
		
		this.oBedienPanel.add(
				new E2_BASIC_EditListButtonPanel(oNaviList, true,true,true,null,null,this.get_MODUL_IDENTIFIER(),"", null, null, null), 
				E2_INSETS.I_0_2_5_2);
		
		this.oBedienPanel.add(new EAKB_ListSearch(oMap,oNaviList));
		
		this.add(this.oBedienPanel,E2_INSETS.I_5_5_5_5);
		this.add(oNaviList,E2_INSETS.I_5_5_5_5);
		
		
		oNaviList._REBUILD_COMPLETE_LIST("");
		
		
	}
	
	
	
}
