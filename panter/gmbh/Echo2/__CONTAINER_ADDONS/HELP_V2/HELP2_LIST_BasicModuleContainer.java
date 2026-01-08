 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class HELP2_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private HELP2__TransportHashMap   tpHashMap = null;
    
    /**
     * in call-settings koennen bestimmte paramter uebergeben werden
     * @param callSettings
     * @throws myException
     */
    public HELP2_LIST_BasicModuleContainer(E2_MODULNAME_ENUM.MODUL modul) throws myException    {
    	
    	super(TRANSLATOR.LIST.get_modul().get_callKey());
 
    	this.tpHashMap = new HELP2__TransportHashMap();
        
    	this.tpHashMap.setModul(modul);
    	
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(oNaviList);
        this.tpHashMap._setLeadingMaskKey(HELP2_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(HELP2_CONST.getLeadingTable());
        
        oNaviList.INIT_WITH_ComponentMAP(new HELP2_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        HELP2_LIST_BedienPanel oPanel = new HELP2_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
 
