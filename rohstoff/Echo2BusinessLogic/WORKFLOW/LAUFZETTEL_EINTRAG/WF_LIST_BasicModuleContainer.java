package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class WF_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	/** 
	 * 
	 */
	private static final long serialVersionUID = 7693095404234924260L;
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public WF_LIST_BasicModuleContainer(String ID_LAUFZETTEL, String ID_USER_BEARBEITER) throws myException
	{
		super(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE);
		
		boolean bStandAlone = (bibALL.isEmpty(ID_LAUFZETTEL) && bibALL.isEmpty(ID_USER_BEARBEITER));
		
		
		this.set_bVisible_Row_For_Messages(bStandAlone);
		
		
		
		WF_MASK_BasicModuleContainer oWF_MASK_Container = new WF_MASK_BasicModuleContainer(ID_LAUFZETTEL, ID_USER_BEARBEITER, null);

		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new WF_LIST_ComponentMap(ID_LAUFZETTEL, ID_USER_BEARBEITER,oNaviList,oWF_MASK_Container),
										E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		
		if (bStandAlone)
		{
			WF_LIST_BedienPanel oPanel = new WF_LIST_BedienPanel(oNaviList,oWF_MASK_Container);
			this.add(oPanel);
			oPanel.get_oWF_LIST_Selector().get_oSelVector().doActionPassiv();
		}
		else
		{
			oNaviList.get_vectorSegmentation().set_bOnlyOneSegment(true);
			oNaviList._REBUILD_COMPLETE_LIST(null);
		}
		
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

	}
		
}
