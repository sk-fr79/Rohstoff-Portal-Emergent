 
package rohstoff.Echo2BusinessLogic._TaxOld;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class TO_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public TO_LIST_BasicModuleContainer() throws myException    {
    	
    	super(E2_MODULNAME_ENUM.MODUL.MWSTSCHLUESSEL_OLD_LIST.get_callKey());
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList naviList = new E2_NavigationList();
        
        naviList.set_bSaveSortStatus(true);
        
        //formatierungsoption bei inaktiven oder geloeschten
        naviList.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(naviList));
        
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(naviList);
        this.tpHashMap._setLeadingMaskKey(TO_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(TO_CONST.getLeadingTable());
        
        naviList.INIT_WITH_ComponentMAP(new TO_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        TO_LIST_BedienPanel oPanel = new TO_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(naviList, E2_INSETS.I_2_2_2_2);
        
        // oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
        naviList._REBUILD_COMPLETE_LIST("");
    }
        
}
 
 
