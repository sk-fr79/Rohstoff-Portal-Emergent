package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MANDANT_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public MANDANT_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_MANDANTENVERWALTUNG_LISTE.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new MANDANT_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		MANDANT_LIST_BedienPanel oPanel = new MANDANT_LIST_BedienPanel(oNaviList,new MANDANT_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		this.add_CloseActions(new ownCloseAction(this));
		
		
		oNaviList._REBUILD_COMPLETE_LIST("");
		
		
		
	}
		
	
	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow {

		public ownCloseAction(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			bibALL.get_RECORD_MANDANT().REBUILD();
		}
		
	}
	
}
