package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{

	public TP_KST_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_TPA_KOSTEN_LISTE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new TP_KST_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,null);
		TP_KST_LIST_BedienPanel oPanel = new TP_KST_LIST_BedienPanel(oNaviList);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oTP_KST_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
