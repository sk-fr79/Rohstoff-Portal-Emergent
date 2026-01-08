 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class WK_RB_CHILD_ABZUG_GEB_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public WK_RB_CHILD_ABZUG_GEB_LIST_BasicModuleContainer() throws myException    {
    	
    	super(WK_RB_CHILD_ABZUG_GEB_CONST.MODUL_Identifier_LIST);
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(false);
        
        E2_NavigationList naviList = new E2_NavigationList();
        
        //formatierungsoption bei inaktiven oder geloeschten
        naviList.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(naviList));
        
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(naviList);
        this.tpHashMap._setLeadingMaskKey(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingTable());
        
        naviList.INIT_WITH_ComponentMAP(new WK_RB_CHILD_ABZUG_GEB_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        WK_RB_CHILD_ABZUG_GEB_LIST_BedienPanel oPanel = new WK_RB_CHILD_ABZUG_GEB_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(naviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
 
