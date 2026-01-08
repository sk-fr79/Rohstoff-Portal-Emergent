 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.IMPORT_Zolltarifnummer_Handler;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.ZOL_LIST_ComponentMap;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.ZOL_LIST_Panel_Import;
  
  
public class ZT_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private RB_TransportHashMap   tpHashMap = null;
    
    ZT_LIST_ComponentMap	oLIST_ComponentMap = null;
    E2_NavigationList 		naviList = null; 
    
    IMPORT_Zolltarifnummer_Handler _importHandler =null;
    
    
    public ZT_LIST_BasicModuleContainer() throws myException    {
    	
    	super(E2_MODULNAME_ENUM.MODUL.ZT_NUMMER_LIST.get_callKey());
 
    	this.tpHashMap = new RB_TransportHashMap();
        
        this.set_bVisible_Row_For_Messages(true);
        
        naviList = new E2_NavigationList();
        
        //formatierungsoption bei inaktiven oder geloeschten
        naviList.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(naviList));
        
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(naviList);
        this.tpHashMap._setLeadingMaskKey(ZT_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(ZT_CONST.getLeadingTable());
        
        this.tpHashMap._putSB("ZT_KEY_AUTOCHANGE_RC_TABLE", new MapForChangeRcTables());
        
        _importHandler = new IMPORT_Zolltarifnummer_Handler();

        // Import-Anzeige nur für Supervisor
        if ( bibALL.get_bIST_SUPERVISOR()){
        	ZT_LIST_Panel_Import oPanelImport = new ZT_LIST_Panel_Import(this,naviList);
        	this.add(oPanelImport, E2_INSETS.I_2_2_2_2);
        }
        
        // Import-Navi-List nur als Supervisor
        initNaviList(_importHandler.hasEntries() && bibALL.get_bIST_SUPERVISOR() );
//        naviList.INIT_WITH_ComponentMAP(new ZT_LIST_ComponentMap(this.tpHashMap,false),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        ZT_LIST_BedienPanel oPanel = new ZT_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(naviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
    public void initNaviList(boolean bImport) throws myException{
        oLIST_ComponentMap =  new ZT_LIST_ComponentMap(this.tpHashMap, bImport);
        this.naviList.INIT_WITH_ComponentMAP(oLIST_ComponentMap ,E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        this.naviList._REBUILD_COMPLETE_LIST("");
    }
    
}
 
 
