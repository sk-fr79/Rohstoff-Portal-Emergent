 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.SEARCHDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_MASK_MaskGrid extends E2_Grid4Mask {
    public SD_MASK_MaskGrid(SD_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(260,550,20)._bo_no();
        
        SD_MASK_ComponentMap  map1 = (SD_MASK_ComponentMap) mapColl.get(_TAB.searchdef.rb_km());
        
        this._add(new RB_lab("ID") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(SEARCHDEF.id_searchdef), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span(2));

        this._add(new RB_lab("Modul") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(SEARCHDEF.modulkenner), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span(2));

        this._add(new RB_lab("Text für Suchmenü") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(SEARCHDEF.user_text), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span(2));
        
        this._add(new RB_lab("Sql-Block") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(SEARCHDEF.sqlblock), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(SD_CONST.SD_NAMES.MASK_BT_HELP.getPseudoFieldKey(_TAB.searchdef)), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._add(new RB_lab("Aktiv ?") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(SEARCHDEF.aktiv), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span(2));
        
    }
  
    
}
 
