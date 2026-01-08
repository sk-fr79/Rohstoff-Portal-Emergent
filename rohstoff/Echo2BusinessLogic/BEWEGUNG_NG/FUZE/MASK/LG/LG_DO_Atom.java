 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
 
public class LG_DO_Atom extends RB_Dataobject_V2 {
 
    public LG_DO_Atom(Rec20 recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public LG_DO_Atom() throws myException {
        super(_TAB.bewegung_atom);
    }
 
 
}
 
