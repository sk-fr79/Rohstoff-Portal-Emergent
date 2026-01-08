 
package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_MASK_MaskGrid extends E2_Grid {
    public ADR_FREIGABE_MASK_MaskGrid(ADR_FREIGABE_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(160,1040)._bo_no();
        
        ADR_FREIGABE_MASK_ComponentMap  map1 = (ADR_FREIGABE_MASK_ComponentMap) mapColl.get(_TAB.sanktion_pruefung.rb_km());
        
        this._a(new RB_lab("Adresse")._fsa(1) 							,new RB_gld()._ins(2,2,2,15)._left_top());
        
        this._a(new E2_Grid()._setSize(300,150)
        		._a(map1.getComp(SANKTION_PRUEFUNG.id_adresse))
        		._a(map1.getComp(ADR_FREIGABE_CONST.MASK_KEY.SANKTION_ADRESSE_BEARBEITEN.fk()))
        																,new RB_gld()._ins(2,2,2,15)._left_top());
        
//        this._a(new RB_lab("Sanktion wegen")._fsa(1)					,new RB_gld()._ins(2,5,2,5)._left_mid());
//        this._a(map1.getComp(SANKTION_PRUEFUNG.sanktion_wegen)			,new RB_gld()._ins(2,5,2,5)._left_mid());

        this._a(new RB_lab("Aktiv"),new RB_gld()._ins(2,5,2,5)._left_mid());
        this._a(map1.getComp(SANKTION_PRUEFUNG.aktiv)					,new RB_gld()._ins(2,5,2,5)._left_mid());		
        
        this._a(new RB_lab("Hashwert Adresse")._fsa(1) 					,new RB_gld()._ins(2,5,2,5)._left_mid());
        this._a(map1.getComp(SANKTION_PRUEFUNG.hashwert_adresse)		,new RB_gld()._ins(2,5,2,5)._left_mid());
        this._a(new RB_lab("Hashwert Sanktion")._fsa(1) 				,new RB_gld()._ins(2,2,2,15)._left_mid());
        this._a(map1.getComp(SANKTION_PRUEFUNG.hashwert_sanktion)		,new RB_gld()._ins(2,2,2,15)._left_mid());
      
        this._a(new RB_lab("Geprüft am")._fsa(1)						,new RB_gld()._ins(2,15,2,2)._left_top());
        this._a(map1.getComp(SANKTION_PRUEFUNG.geprueft_am)				,new RB_gld()._ins(2,15,2,2)._left_mid());
        
        this._a(new RB_lab("Bemerkung")._fsa(1)							,new RB_gld()._ins(2)._left_top());
        this._a(map1.getComp(SANKTION_PRUEFUNG.freigabe_bemerkung)		,new RB_gld()._ins(2)._left_top());

        this._a(new RB_lab("Freigabe von")._fsa(1)						,new RB_gld()._ins(2,15,2,2)._left_mid());
        this._a(new E2_Grid()._setSize(160,140,150,150)
        		._a(map1.getComp(SANKTION_PRUEFUNG.freigabe_user)		,new RB_gld()._ins(0,5,2,5)._left_mid())
        		._a(new RB_lab("Freigabe am")._fsa(1) 					,new RB_gld()._ins(0,5,2,5)._right_mid())
        		._a(map1.getComp(SANKTION_PRUEFUNG.freigabe_datum)		,new RB_gld()._ins(0,5,2,5)._left_mid())
        		._a(map1.getComp(SANKTION_PRUEFUNG.freigabe)			,new RB_gld()._ins(0,5,2,5)._left_mid())	
        ,new RB_gld()._ins(2,15,2,2)._left_mid());
        
        this._a(map1.getComp(ADR_FREIGABE_CONST.MASK_KEY.SANKTION_DETAIL.fk()),new RB_gld()._ins(0,15,2,5)._left_mid()._span(2));
        
        
    }
  
    
}
 
