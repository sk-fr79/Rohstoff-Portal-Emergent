 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
 
public class KFIX_K_M_DataObject extends RB_Dataobject_V2 {

	private VORGANGSART belegTyp = null;
	
	public KFIX_K_M_DataObject(_TAB p_tab) throws myException {
		super(p_tab);
	}
 
	public KFIX_K_M_DataObject(Rec20 recORD, MASK_STATUS status, VORGANGSART oBelegTyp, boolean bFixierungsKontrakte) throws myException {
        super(recORD, status);
        
        this.belegTyp = oBelegTyp;
    }

	public VORGANGSART getBelegTyp() {
		return belegTyp;
	}


}
 
