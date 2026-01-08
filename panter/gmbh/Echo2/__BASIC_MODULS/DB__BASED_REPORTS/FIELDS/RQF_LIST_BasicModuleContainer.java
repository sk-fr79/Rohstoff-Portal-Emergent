 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS.RQF_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class RQF_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public RQF_LIST_BasicModuleContainer() throws myException    {
    	
    	super(TRANSLATOR.LIST.get_modul().get_callKey());
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(oNaviList);
        this.tpHashMap._setLeadingMaskKey(RQF_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(RQF_CONST.getLeadingTable());
        
        oNaviList.INIT_WITH_ComponentMAP(new RQF_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        RQF_LIST_BedienPanel oPanel = new RQF_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
 
