 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CON_MASK_MaskGrid extends E2_Grid4Mask {
    public CON_MASK_MaskGrid(CON_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(150,100,100,100,100,100,100)._bo_no();
        
        CON_MASK_ComponentMap  map1 = (CON_MASK_ComponentMap) mapColl.get(new CON_KEY());
        
        this._add(new RB_lab("ID Container") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.id_container), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(6));
        
        this._add(new RB_lab("Container-NR") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.container_nr), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(6));
        
        this._add(new RB_lab("Gewicht(Tara)") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.tara), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(new RB_lab("Kg") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(5));
        
        this._add(new RB_lab("Tara Korrektur") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.tara_korrektur), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(new RB_lab("Kg") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(5));
        
        this._add(new RB_lab("Korrektur-Datum") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINER.datum_korrektur), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(6));
        
        this._add(new RB_lab("Bemerkung") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(CONTAINER.bemerkung), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(6));
        
        this._add(new RB_lab("Containertyp") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.id_containertyp), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(6));
    
        this._add(new RB_lab("UVV") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.uvv_datum), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._span(6));
        
        this._add(new RB_lab("Aktiv") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        this._add(map1.getComp(CONTAINER.aktiv), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
        
        
        
    }
  
    
}
 
