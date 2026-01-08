 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params;
 
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params.RVP_CONST.RVP_NUM_CONST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
   
public class RVP_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   params = null;
    
    
    public RVP_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.params = p_tpHashMap;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(RVP_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(RVP_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);
        
        RVP_MASK_ComponentMapCollector compMapCollector = new RVP_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.rep_varianten_param.rb_km(), compMapCollector );
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.REP_VARIANTEN_PARAM_MASK_MASK, new RVP_MASK_MaskGrid(this.params), true);
    }
    

}
 
 
