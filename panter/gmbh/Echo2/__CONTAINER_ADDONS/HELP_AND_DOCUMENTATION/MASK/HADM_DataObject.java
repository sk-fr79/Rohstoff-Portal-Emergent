package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.indep.exceptions.myException;

public class HADM_DataObject extends RB_Dataobject_V1 {

	public HADM_DataObject(RECORD_HILFETEXT recORD, MASK_STATUS status) 	throws myException {
		super(recORD, status);
	}

	public HADM_DataObject(RECORDNEW_HILFETEXT recNEW) throws myException {
		super(recNEW);
	}


}
