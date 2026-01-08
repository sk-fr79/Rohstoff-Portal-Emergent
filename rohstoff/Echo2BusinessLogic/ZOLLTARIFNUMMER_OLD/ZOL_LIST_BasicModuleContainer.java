package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.ZOL_CONST.TRANSLATOR;;




public class ZOL_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        ZOL_CONST.ZOL_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        ZOL_CONST.ZOL_NAMES.MARKER_LISTE.db_val();
    
    ZOL_LIST_ComponentMap	oLIST_ComponentMap = null;
    E2_NavigationList 		oNaviList = null; 
    
    IMPORT_Zolltarifnummer_Handler _importHandler =null;
    
    
    public ZOL_LIST_BasicModuleContainer() throws myException
    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.set_bVisible_Row_For_Messages(true);
        
        oNaviList = new E2_NavigationList();
        

        ZOL_LIST_BedienPanel oPanel = new ZOL_LIST_BedienPanel(oNaviList);
        this.add(oPanel);
        
        _importHandler = new IMPORT_Zolltarifnummer_Handler();

        // Import-Anzeige nur für Supervisor
        if ( bibALL.get_bIST_SUPERVISOR()){
        	ZOL_LIST_Panel_Import oPanelImport = new ZOL_LIST_Panel_Import(this,oNaviList);
        	this.add(oPanelImport, E2_INSETS.I_2_2_2_2);
        }
        // Import-Navi-List nur als Supervisor
        initNaviList(_importHandler.hasEntries() && bibALL.get_bIST_SUPERVISOR() );
        
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }

    
    
    public void initNaviList(boolean bImport) throws myException{
        oLIST_ComponentMap =  new ZOL_LIST_ComponentMap(bImport);
        oNaviList.INIT_WITH_ComponentMAP(oLIST_ComponentMap ,E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
    	oNaviList._REBUILD_COMPLETE_LIST("");
    }
        
}
 
