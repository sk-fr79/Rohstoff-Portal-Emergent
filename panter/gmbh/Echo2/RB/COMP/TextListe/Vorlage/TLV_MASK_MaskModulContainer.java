 
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;
 
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage.TLV_CONST.TLV_NUM_CONST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
   
public class TLV_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private TLV_MASK_MaskGrid maskGrid = null;
    
    public TLV_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(TLV_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(TLV_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        TLV_MASK_ComponentMapCollector compMapCollector = new TLV_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.text_liste_vorlage.rb_km(), compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.TEXT_LISTE_VORLAGE_MASK, this.maskGrid=new TLV_MASK_MaskGrid(this.params), true);
        
        maskGrid._w100();
        
        this.set_oResizeHelper(new ownResizer());
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
}
 
 
