package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;


import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX___CONST.TRANSLATOR_KOPF;

public class KFIX_K_M_MaskModulContainer extends Project_RB_ModuleContainerMASK {
   
	private boolean b_fixierungsKontrakt = false;
	
	public KFIX_K_M_MaskModulContainer(boolean bFixierungsKontrakt, VORGANGSART belegTyp, E2_NavigationList naviList) throws myException {
        super();        
        
        KFIX_K_M__ComponentMapCollector compMapCollector = new KFIX_K_M__ComponentMapCollector(bFixierungsKontrakt, belegTyp) ; 
        
        this.b_fixierungsKontrakt = bFixierungsKontrakt;
        
        compMapCollector.setFixierungKontrakte(b_fixierungsKontrakt);
        
        this.registerComponent(new RB_KM(_TAB.vkopf_kon), compMapCollector );

        //wenn modul list ek -> mask ek
        if(belegTyp==VORGANGSART.EK_KONTRAKT){
        	this.rb_INIT(TRANSLATOR_KOPF.MASK_EK.get_modul(), new KFIX_K_M_MaskGrid(this, compMapCollector, naviList), true);
        }else if (belegTyp==VORGANGSART.VK_KONTRAKT){
        	this.rb_INIT(TRANSLATOR_KOPF.MASK_VK.get_modul(), new KFIX_K_M_MaskGrid(this, compMapCollector, naviList), true);
        }
    }
}
 
