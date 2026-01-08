 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;
  
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.PROFIL_GRENZUBERTRITT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class BGL_PROFIL_MASK_MaskGrid extends E2_Grid4Mask {
   
	public BGL_PROFIL_MASK_MaskGrid(BGL_PROFIL_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(250,600)._bo_no();
        
        BGL_PROFIL_MASK_ComponentMap  map1 = (BGL_PROFIL_MASK_ComponentMap) mapColl.get(_TAB.profil_grenzubertritt.rb_km());
        
        RB_gld  gld = new RB_gld()._ins(2)._left_top();
        
        this._add(new RB_lab("Ist Standard") ,									gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.std), 						gld);
        this._add(new RB_lab("Profilname") ,									gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.name_profil), 				gld);
        this._add(new RB_lab("Zahlungsbedingungen") ,							gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.id_zahlungsbedingung), 	gld);
        this._add(new RB_lab("Steuer für Rechnungsbeleg") ,						gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.id_tax_gutschrift),	 	gld);
        this._add(new RB_lab("Steuer für Gutschriftsbeleg") ,					gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.id_tax_rechnung), 			gld);
        this._add(new RB_lab("Name Ansprechpartner") ,							gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.name_ansprechpartner), 	gld);
        this._add(new RB_lab("Telefonnummer Ansprechpartner") ,					gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.tel_ansprechpartner), 		gld);
        this._add(new RB_lab("Fax Ansprechpartner") ,							gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.fax_ansprechpartner), 		gld);
        this._add(new RB_lab("Name Bearbeiter intern") ,						gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.name_bearbeiter), 			gld);
        this._add(new RB_lab("Telefon Bearbeiter intern") ,						gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.tel_bearbeiter), 			gld);
        this._add(new RB_lab("Fax Bearbeiter intern") ,							gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.fax_bearbeiter), 			gld);
        this._add(new RB_lab("Formulatext Anfang") ,							gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.formulartext_anfang), 		gld);
        this._add(new RB_lab("Formulatext Ende") ,								gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.formulartext_ende),		gld);
        this._add(new RB_lab("Auslandsvertretung Text") ,						gld);
        this._add(map1.getComp(PROFIL_GRENZUBERTRITT.auslandsvertretung_text), 	gld);
    
    }
  
    
}
 
