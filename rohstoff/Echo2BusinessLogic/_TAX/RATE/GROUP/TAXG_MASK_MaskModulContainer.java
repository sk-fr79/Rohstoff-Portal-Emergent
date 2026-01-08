 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_NUM_CONST;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_PARAMS;
  
public class TAXG_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_MASK_MaskModulContainer(PARAMHASH  p_params) throws myException {
        super();
        
        this.params = p_params;
        
        //anfangsausmasse des fensterpopups
        this._setWidth(TAXG_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(TAXG_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
        
        
        if (this.params != null) {
            this.params.put(TAXG_PARAMS.TAXG_MODULCONTAINER_MASK,this);
        }     
        
        TAXG_MASK_ComponentMapCollector compMapCollector = new TAXG_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.tax_group.rb_km(), compMapCollector );
        
        this.rb_INIT(TAXG_CONST.TRANSLATOR.MASK.get_modul(), new TAXG_MASK_MaskGrid(compMapCollector,this.params), true);
        
    }
}
 
