 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_MASK_MaskGrid extends E2_Grid4Mask {
    public CONTYP_MASK_MaskGrid(CONTYP_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(150,600)._bo_no();
        
        CONTYP_MASK_ComponentMap  map1 = (CONTYP_MASK_ComponentMap) mapColl.get(new CONTYP_KEY());

        this._add(new RB_lab("ID Containertyp") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        E2_Grid g = new E2_Grid()._setSize(100,100)
        			._a(map1.getComp(CONTAINERTYP.id_containertyp), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP))
        			._a(map1.getComp(CONTAINERTYP.aktiv),new RB_gld()._ins(2)._al(E2_ALIGN.RIGHT_TOP) );
        
//        this._add(map1.getComp(CONTAINERTYP.id_containertyp), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(g, new RB_gld()._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Kürzel") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.kuerzel), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Kurzbezeichnung") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.kurzbezeichnung), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Containerinhalt (cbm)") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.containerinhalt), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("Beschreibung") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.beschreibung), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Container-Merkmale") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.abroll), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.absetz), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.dicht), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.ablauf), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.symmetrisch), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.deckel), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINERTYP.plane), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
    
    }
  
    
}
 
