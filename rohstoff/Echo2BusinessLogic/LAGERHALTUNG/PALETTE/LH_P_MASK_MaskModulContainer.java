 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
 
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_CONST.LH_P_NUM_CONST;
   
public class LH_P_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private LH_P_MASK_MaskGrid maskGrid = null;
    
    public LH_P_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(LH_P_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(LH_P_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        LH_P_MASK_ComponentMapCollector compMapCollector = new LH_P_MASK_ComponentMapCollector(this.params) ; 
        
        this.registerComponent(_TAB.lager_palette.rb_km(), compMapCollector );
        
        this.rb_INIT(LH_P_CONST.TRANSLATOR.MASK.get_modul(), this.maskGrid=new LH_P_MASK_MaskGrid(this.params), true);
        
        this.set_oResizeHelper(new ownResizer());
        
        this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				params.getNavigationList()._REBUILD_COMPLETE_LIST("");
			}
		});
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
}
 
 
