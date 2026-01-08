 
package rohstoff.businesslogic.bewegung2.mask;
 
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.list.B2_ConstEnumNumValues;
   
public class B2_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private B2_MaskGridV2 maskGrid = null;
    
    public B2_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(B2_ConstEnumNumValues.MASKPOPUP_WIDTH.getValue())._setHeight(B2_ConstEnumNumValues.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        B2_MaskCompMapCollector compMapCollector = new B2_MaskCompMapCollector(this.params) ; 
        this.registerComponent(_TAB.bg_vektor.rb_km(), compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.B_TRANSPORT_MASK, this.maskGrid=new B2_MaskGridV2(this.params), true);
        
        this.set_oResizeHelper(new ownResizer());
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
}
 
 
