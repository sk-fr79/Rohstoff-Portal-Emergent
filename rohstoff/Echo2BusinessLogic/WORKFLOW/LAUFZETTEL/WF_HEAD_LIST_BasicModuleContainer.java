package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;



public class WF_HEAD_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4013277603232114105L;
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public WF_HEAD_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE);
		
		this.set_bVisible_Row_For_Messages(true);

		WF_HEAD_MASK_BasicModuleContainer oMaskHead = new WF_HEAD_MASK_BasicModuleContainer();
		
		//E2_NavigationList oNaviList = new E2_NavigationList();
		WF_HEAD__NaviList oNaviList = new WF_HEAD__NaviList();
		//		oNaviList.get_vectorSegmentation().set_iSegmentGroesse(5);
		WF_HEAD_LIST_ComponentMap map = new WF_HEAD_LIST_ComponentMap(oNaviList,oMaskHead);
		
		oNaviList.INIT_WITH_ComponentMAP(map,E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		WF_HEAD_LIST_BedienPanel oPanel = new WF_HEAD_LIST_BedienPanel(oNaviList,oMaskHead,this.get_MODUL_IDENTIFIER(),this);

		this.add(oPanel);
	
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		//oPanel.get_oWF_HEAD_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
