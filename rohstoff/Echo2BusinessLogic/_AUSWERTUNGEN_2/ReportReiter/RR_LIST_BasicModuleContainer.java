 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2__BasicContainer_RohstoffAuswertungen;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter.RR_CONST.TRANSLATOR;
public class RR_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        RR_CONST.RR_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        RR_CONST.RR_NAMES.MARKER_LISTE.db_val();
    
	private AW2__BasicContainer_RohstoffAuswertungen 	callingTabContainer = null;

    
    public RR_LIST_BasicModuleContainer(AW2__BasicContainer_RohstoffAuswertungen 	p_callingTabContainer) throws myException
    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.callingTabContainer = p_callingTabContainer;
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        oNaviList.INIT_WITH_ComponentMAP(new RR_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        RR_LIST_BedienPanel oPanel = new RR_LIST_BedienPanel(oNaviList);
        this.add(oPanel);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oNaviList._REBUILD_COMPLETE_LIST(null);
        
        this.add_CloseActions(new ownActionOnclose(this));
    }
        
    
    private class ownActionOnclose extends XX_ActionAgentWhenCloseWindow {
    	
    	
		public ownActionOnclose(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RR_LIST_BasicModuleContainer.this.callingTabContainer.rebuild_tab_container();
		}
    }
    
}
 
