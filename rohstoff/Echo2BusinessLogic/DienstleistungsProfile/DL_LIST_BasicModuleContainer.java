 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class DL_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public DL_LIST_BasicModuleContainer() throws myException    {
    	
    	super(E2_MODULNAME_ENUM.MODUL.DIENSTLEISTUNG_PROFIL_LIST.get_callKey());
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
        
        oNaviList.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(oNaviList));
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(oNaviList);
        this.tpHashMap._setLeadingMaskKey(DL_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(DL_CONST.getLeadingTable());
        
        oNaviList.INIT_WITH_ComponentMAP(new DL_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        DL_LIST_BedienPanel oPanel = new DL_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
 
