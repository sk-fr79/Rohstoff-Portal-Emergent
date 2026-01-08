 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
 
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde.WK_RB_CHILD_ABZUG_GEB_CONST.WK_RB_CHILD__NUM_CONST;
   
public class WK_RB_CHILD_ABZUG_GEB_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private WK_RB_CHILD_ABZUG_GEB_MASK_MaskGrid maskGrid = null;
    
    public WK_RB_CHILD_ABZUG_GEB_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.params = p_tpHashMap;
//        this.get_oRowForButtons()
//		this.get_oRowForAutomaticTools()
//		this.get_oRowForHeadline()
		
        
        this._setWidth(WK_RB_CHILD__NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(WK_RB_CHILD__NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);

        
        WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMapCollector compMapCollector = new WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.wiegekarte_abzug_geb.rb_km(), compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.WK_RB_CHILD_MASK_ABZUG_GEB, this.maskGrid=new WK_RB_CHILD_ABZUG_GEB_MASK_MaskGrid(this.params), true);
        
        this.set_oResizeHelper(new ownResizer());
        
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
  
    
}
 
 
