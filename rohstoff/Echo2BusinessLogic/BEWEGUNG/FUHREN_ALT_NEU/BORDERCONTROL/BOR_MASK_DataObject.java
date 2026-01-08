
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BORDERCROSSING;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING;
import panter.gmbh.indep.exceptions.myException;


public class BOR_MASK_DataObject extends RB_Dataobject_V1 {

	public BOR_MASK_DataObject(RECORD_BORDERCROSSING recORD, MASK_STATUS status) throws myException {
		super(recORD, status);
	}

	public BOR_MASK_DataObject(RECORDNEW_BORDERCROSSING recNEW) throws myException {
		super(recNEW);
	}

}
