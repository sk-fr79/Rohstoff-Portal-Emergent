 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
 
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.ServerPush.E2_ApplicationInstanceWithServerPushUpdateTask;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.WK_RB_NUM_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
   
public class WK_RB_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private WK_RB_MASK_MaskGrid maskGrid = null;
    
    
    // Serverpush um die Waagen abzufragen
	private E2_ApplicationInstanceWithServerPushUpdateTask m_ServerPushTask = null;
	
    
    
    public WK_RB_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(WK_RB_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(WK_RB_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
                
        
        WK_RB_MASK_ComponentMapCollector compMapCollector = new WK_RB_MASK_ComponentMapCollector(this.params) ; 
        
        // registrieren des ComponentMapCollectors
        this.registerComponent(WK_RB_CONST.key_componentMap_collector, compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.WK_RB_MASK, this.maskGrid=new WK_RB_MASK_MaskGrid(this.params), true);
        
        this.set_oResizeHelper(new ownResizer());
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
    
    /* (non-Javadoc)
     * @see nextapp.echo2.app.Component#dispose()
     */
    @Override
    public void dispose() {
    	WK_RB_MASK_ComponentMap_Wiegekarte comp = (WK_RB_MASK_ComponentMap_Wiegekarte) this.params.getMaskComponentMapCollector().get(RecDOWiegekarte.key);
    	comp.oMaskControllerWaageHandling.destroyPushTask();

    	super.dispose();
    }
    
}
 
 
