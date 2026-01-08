 
package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
 
public class MA_DES_MASK_DataObject extends RB_Dataobject_V2 {
 
    public MA_DES_MASK_DataObject(Rec20 recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public MA_DES_MASK_DataObject() throws myException {
        super(_TAB.mask_def);
    }
}
 
