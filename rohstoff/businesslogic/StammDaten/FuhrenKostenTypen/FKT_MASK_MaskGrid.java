 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_MASK_MaskGrid extends E2_Grid4Mask {
    public FKT_MASK_MaskGrid(FKT_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(200,400)._bo_no();
        
        FKT_MASK_ComponentMap  map1 = (FKT_MASK_ComponentMap) mapColl.get(_TAB.fuhren_kosten_typ.rb_km());
        
        this._add(new RB_lab("ID") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("Kurztext") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        E2_Grid g = new E2_Grid()._s(2)
        				._a(map1.getComp(FUHREN_KOSTEN_TYP.kurztext_uebersicht), new RB_gld()._ins(0, 0, 5, 0))
        				._a(map1.getComp(new RB_KF(FKT_CONST.FKT_BUTTONS.POPUP_TYPEN.dbVal())), new RB_gld()._ins(0, 0, 0, 0))
        				;
        this._add(g, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("Bezeichnung") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(FUHREN_KOSTEN_TYP.text4benutzer), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("Zoll ?") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(FUHREN_KOSTEN_TYP.betrifft_zoll), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        
        this._add(new RB_lab("Kostenneutral ?") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(FUHREN_KOSTEN_TYP.neutral), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Speditionsrechnung ?") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(FUHREN_KOSTEN_TYP.speditionsrechnung), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
    
    }
  
    
}
 
