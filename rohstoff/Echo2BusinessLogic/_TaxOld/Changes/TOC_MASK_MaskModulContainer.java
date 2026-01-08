 
package rohstoff.Echo2BusinessLogic._TaxOld.Changes;
 
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import rohstoff.Echo2BusinessLogic._TaxOld.Changes.TOC_CONST.TOC_NUM_CONST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
   
public class TOC_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private TOC_MASK_MaskGrid maskGrid = null;
    
    public TOC_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(TOC_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(TOC_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        TOC_MASK_ComponentMapCollector compMapCollector = new TOC_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.mwstschluessel_aenderungen.rb_km(), compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.MWSTSCHLUESSEL_OLD_AENDERUNGEN_MASK, this.maskGrid=new TOC_MASK_MaskGrid(this.params), true);
        
        this.set_oResizeHelper(new ownResizer());
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
}
 
 
