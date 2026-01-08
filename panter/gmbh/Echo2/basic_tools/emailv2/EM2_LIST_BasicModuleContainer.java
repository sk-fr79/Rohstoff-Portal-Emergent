 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.exceptions.myException;
  
  
public class EM2_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
 	
    private EM2_TransportHashMap   tpHashMap = null;
    
    
    /**
     * 
     * @author martin
     * @date 09.02.2021
     * 
     * variante mit uebergabe von table und id
     * 
     * @throws myException
     */
    public EM2_LIST_BasicModuleContainer(_TAB table, Long idTable) throws myException    {
    	
    	super(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_V2_LIST.get_callKey()+"@"+table.baseTableName());
    	this._setInitOk(false);
    	
    	
    	if (O.isOneNull(table,idTable)) {
    		throw new myException("Error: table and idTable MUST not be null !!<>");
    	}
    	
    	this.tpHashMap = new EM2_TransportHashMap();
    	
    	tpHashMap._setTable(table)._setTableId(idTable);
        
        this.set_bVisible_Row_For_Messages(true);
        
        
    }
    
    public EM2_LIST_BasicModuleContainer() throws myException    {
    	super(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_V2_LIST.get_callKey());
    	this._setInitOk(false);

    	this.tpHashMap = new EM2_TransportHashMap();
        this.set_bVisible_Row_For_Messages(true);
        
    }

    
    public EM2_LIST_BasicModuleContainer _init() throws myException {
        initialSettings();
        return this;
    }
    
    
    
    private void initialSettings() throws myException {
        E2_NavigationList naviList = new E2_NavigationList();
        this._setInitOk(true);
        
        //formatierungsoption bei inaktiven oder geloeschten
        naviList.getvActionAgentsAfterListGeneration().add(new E2_ComponentMapMarker.FormatingAgent(naviList));
        
        this.tpHashMap._setModulContainerList(this);
        this.tpHashMap._setNavigationList(naviList);
        this.tpHashMap._setLeadingMaskKey(EM2_CONST.getLeadingMaskKey());
        this.tpHashMap._setLeadingTableOnMask(EM2_CONST.getLeadingTable());
        
        naviList.INIT_WITH_ComponentMAP(new EM2_LIST_ComponentMap(this.tpHashMap),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        EM2_LIST_BedienPanel oPanel = new EM2_LIST_BedienPanel(this.tpHashMap);
        
        this.tpHashMap._setListBedienPanel(oPanel);
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(naviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
 
    }
    
        
    
    public EM2_LIST_BasicModuleContainer _setAllowDelete(boolean allow) {
    	tpHashMap._setAllowDelete(allow);
    	return this;
    }
    
    public EM2_LIST_BasicModuleContainer _setAllowEdit(boolean allow) {
    	tpHashMap._setallowEdit(allow);
    	return this;
    }
    
    public EM2_LIST_BasicModuleContainer _setAllowCopy(boolean allow) {
    	tpHashMap._setAllowCopy(allow);
    	return this;
    }
    
    public EM2_LIST_BasicModuleContainer _setAllowNew(boolean allow) {
    	tpHashMap._setAllowNew(allow);
    	return this;
    }
    
    
    public EM2_LIST_BasicModuleContainer _setAllowSendButton(boolean allow) {
    	tpHashMap._setAllowSendButton(allow);
    	return this;
    }
    
    
    public EM2_LIST_BasicModuleContainer _setAllowAddTarget(boolean allow) {
    	tpHashMap._setAllowAddTarget(allow);
    	return this;
    }
    
    
    
}
 
 
