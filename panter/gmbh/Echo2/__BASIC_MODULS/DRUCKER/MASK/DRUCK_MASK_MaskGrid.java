package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.MASK;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_MASK_MaskGrid extends E2_Grid4Mask {
	
    public DRUCK_MASK_MaskGrid(DRUCK_MASK_ComponentMapCollector  mapColl) throws myException {
       
    	super();
        this._setSize(160,600)._bo_no();
        
        DRUCK_MASK_ComponentMap  map1 = (DRUCK_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.drucker));

        this._add(new RB_lab("Ist Aktiv")						,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(DRUCKER.aktiv)					,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Name")							,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(DRUCKER.name)					,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Standort")						,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(DRUCKER.standort)				,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Beschreibung")					,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(DRUCKER.beschreibung)			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Direct Druck Befehl")				,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(DRUCKER.direct_druck_befehl)		,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
    }
  
    
}
 
