package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
   
	public MC_DES_MASK_ComponentMapCollector(Rec20 mask_def) throws myException {
        super();
        
        this.registerComponent(new MC_DES_KEY(), new MC_DES_MASK_ComponentMap(mask_def));
    }
    
    public MC_DES_MASK_ComponentMapCollector(Rec20 mask_def, int i_x_coor, int i_y_coor) throws myException {
        super();
        
        MC_DES_MASK_ComponentMap compMap =  new MC_DES_MASK_ComponentMap( mask_def);
        compMap.set_row_coordinate(		i_y_coor);
        compMap.set_column_coordinate(	i_x_coor);
        
        this.registerComponent(new MC_DES_KEY(), compMap);
    }
}
 
