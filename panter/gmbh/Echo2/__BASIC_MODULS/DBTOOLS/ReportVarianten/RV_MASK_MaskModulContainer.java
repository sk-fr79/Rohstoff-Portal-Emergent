 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
 
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.RV_CONST.RV_NUM_CONST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
   
public class RV_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
 //   private RV_MASK_MaskGrid maskGrid = null;
    
    public RV_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(RV_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(RV_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        RV_MASK_ComponentMapCollector compMapCollector = new RV_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.rep_varianten.rb_km(), compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.REP_VARIANTEN_MASKE_MASK,new RV_MASK_MaskGrid(this.params), true);
        
    }
    
}
 
 
