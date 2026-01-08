package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX___CONST.TRANSLATOR_POS;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_P_M__MaskModulContainer extends Project_RB_ModuleContainerMASK {
    
	public KFIX_P_M__MaskModulContainer(String belegTyp, Rec20 record_kopf) throws myException {
        super();
        
        KFIX_P_M__ComponentMapCollector compMapCollector = new KFIX_P_M__ComponentMapCollector(belegTyp, record_kopf) ;
              
        this.registerComponent(new RB_KM(_TAB.vpos_kon), compMapCollector );
       
        this.set_iVerticalOffsetForTabbedPane(new Integer(220));
        
        boolean is_fix_kopf = new Rec20_VKOPF_KON(record_kopf).is_fixierungsKontrakte();
        
        if(belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)){
        	this.rb_INIT(TRANSLATOR_POS.MASK_EK.get_modul(), new KFIX_P_M_MaskGrid(compMapCollector, myCONST.VORGANGSART_EK_KONTRAKT, is_fix_kopf), true);
        }else if (belegTyp.equals(myCONST.VORGANGSART_VK_KONTRAKT)){
        	this.rb_INIT(TRANSLATOR_POS.MASK_VK.get_modul(), new KFIX_P_M_MaskGrid(compMapCollector, myCONST.VORGANGSART_VK_KONTRAKT, is_fix_kopf), true);
        }
        
    }
	
}
 
