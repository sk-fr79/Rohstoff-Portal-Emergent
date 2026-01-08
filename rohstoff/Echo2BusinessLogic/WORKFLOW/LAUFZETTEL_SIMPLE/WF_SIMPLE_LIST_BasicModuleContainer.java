 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.WF_SIMPLE_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class WF_SIMPLE_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public WF_SIMPLE_LIST_BasicModuleContainer() throws myException    {
    	
    	super(TRANSLATOR.LIST.get_modul().get_callKey());
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(true);
        
//        E2_NavigationList oNaviList = new E2_NavigationList();
        WF_SIMPLE__NaviList oNaviList = new WF_SIMPLE__NaviList();
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(oNaviList);
        this.tpHashMap._setLeadingMaskKey(WF_SIMPLE_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(WF_SIMPLE_CONST.getLeadingTable());
        
        oNaviList.INIT_WITH_ComponentMAP(new WF_SIMPLE_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        WF_SIMPLE_LIST_BedienPanel oPanel = new WF_SIMPLE_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
 
