 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
  
public class RV_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    public RV_LIST_BasicModuleContainer() throws myException    {
    	
    	super(E2_MODULNAME_ENUM.MODUL.REP_VARIANTEN_MASKE_LIST.get_callKey());
		this.set_bVisible_Row_For_Messages(false);

		
    	this.tpHashMap = new RB_TransportHashMap();
        
        
        E2_NavigationList naviList = new E2_NavigationList();
        
        //formatierungsoption bei inaktiven oder geloeschten
        naviList.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(naviList));
        
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(naviList);
        this.tpHashMap._setLeadingMaskKey(RV_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(RV_CONST.getLeadingTable());
        
        naviList.INIT_WITH_ComponentMAP(new RV_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        RV_LIST_BedienPanel oPanel = new RV_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(naviList, E2_INSETS.I_2_2_2_2);
        
        naviList._REBUILD_COMPLETE_LIST("");
    }
        
}
 
 
