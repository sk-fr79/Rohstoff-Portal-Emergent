package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_MASK_DataObject extends RB_Dataobject_V2 {

	public SCAN_MASK_DataObject(Rec20 record, MASK_STATUS status) throws myException {
		super(record, status);
	}

	public SCAN_MASK_DataObject() throws myException {
		super(_TAB.scanner_settings);
	}


}
