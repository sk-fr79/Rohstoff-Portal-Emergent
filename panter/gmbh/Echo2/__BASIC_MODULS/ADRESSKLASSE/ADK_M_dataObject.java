package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_dataObject extends RB_Dataobject_V1 {

	public ADK_M_dataObject() throws myException {
		super(new RECORDNEW_ADRESSKLASSE_DEF());
    }

	public ADK_M_dataObject(String id_adressklasse_def, MASK_STATUS status) throws myException {
		super(new RECORD_ADRESSKLASSE_DEF(id_adressklasse_def), status);
	}

	
}
