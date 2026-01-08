package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_MASK_MaskGrid extends E2_Grid {
    
	public MA_DES_MASK_MaskGrid(MA_DES_MASK_ComponentMapCollector  mapColl) throws myException {
    
		super();
        this._setSize(160,600)._bo_no();
        
        MA_DES_MASK_ComponentMap  map1 = (MA_DES_MASK_ComponentMap) mapColl.get(new MA_DES_KEY());
        
        this
        ._a(new RB_lab("Tabellename") ,				new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(map1.getComp(MASK_DEF.tablename), 		new RB_gld()._ins(2,5,2,5)._al(E2_ALIGN.LEFT_TOP))
        ._a(new RB_lab("Maskname") 				,	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(map1.getComp(MASK_DEF.maskname), 		new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(new RB_lab("Maskname (lang)") ,			new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(map1.getComp(MASK_DEF.maskname_long), 	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
       
        ._a(new RB_lab("Spaltenzahl") ,				new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(map1.getComp(MASK_DEF.nb_of_cols), 		new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(new RB_lab("Spaltenbreite (in px)") ,	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(map1.getComp(MASK_DEF.pixel_width), 	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        
        ._a(new RB_lab("Offset links") 			,	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        ._a(map1.getComp(MASK_DEF.left_offset)	,	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        
        
        ;
    }
  
    
}
 
