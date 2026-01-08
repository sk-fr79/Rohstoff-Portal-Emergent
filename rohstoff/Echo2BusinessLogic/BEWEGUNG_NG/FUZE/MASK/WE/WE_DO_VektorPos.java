 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
 
public class WE_DO_VektorPos extends RB_Dataobject_V2 {
 
    public WE_DO_VektorPos(Rec20 recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public WE_DO_VektorPos() throws myException {
        super(_TAB.bewegung_vektor_pos);
    }
 
 
}
 
