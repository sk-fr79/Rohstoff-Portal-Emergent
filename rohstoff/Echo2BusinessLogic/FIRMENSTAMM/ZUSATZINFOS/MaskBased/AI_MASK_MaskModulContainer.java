 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
 
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased.AI_CONST.AI__NUM_CONST;
   
public class AI_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    private AI_MASK_MaskGrid maskGrid = null;
    
    public AI_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        AI__TYP typ = (AI__TYP)p_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);

        this.set_cADDON_TO_CLASSNAME(typ.name());   //damit unterschiedliche fenstergroessen gespeichert werden
        
        //anfangsausmasse des fensterpopups
        this._setWidth(AI__NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(AI__NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        AI_MASK_ComponentMapCollector compMapCollector = new AI_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.adresse_info.rb_km(), compMapCollector );
        
        this.rb_INIT(typ==AI__TYP.INFO?MODUL.ADRESSE_INFO_EMBEDDED_MASK:MODUL.ADRESSE_MESSAGE_EMBEDDED_MASK, this.maskGrid=new AI_MASK_MaskGrid(this.params), true);
        
        this.set_oResizeHelper(new ownResizer());
    }
    
    
    private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}
    	
    }
}
 
 
