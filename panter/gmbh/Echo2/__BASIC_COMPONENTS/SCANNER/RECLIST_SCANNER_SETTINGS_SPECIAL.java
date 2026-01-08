package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_SCANNER_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class RECLIST_SCANNER_SETTINGS_SPECIAL extends RECLIST_SCANNER_SETTINGS {

	
	
	public RECLIST_SCANNER_SETTINGS_SPECIAL() throws myException {
		super();
	}


	public RECLIST_SCANNER_SETTINGS_SPECIAL(String id_USER_UF, MyE2_MessageVector oMV) throws myException {
		super();
		
		if (S.isEmpty(id_USER_UF)) {
			throw new myException(this,"id_user is not defined !");
		}
		
		RECLIST_SCANNER_SETTINGS  rl = new RECLIST_SCANNER_SETTINGS("SELECT SE.* FROM "+bibE2.cTO()+"."+_DB.SCANNER_SETTINGS+" SE "+
																	  " INNER JOIN "+bibE2.cTO()+"."+_DB.SCANNER_USER+" SU "+ 
											  " ON (SU."+_DB.SCANNER_USER$ID_SCANNER_SETTINGS+"=SE."+_DB.SCANNER_SETTINGS$ID_SCANNER_SETTINGS+") " +
											  " WHERE "+_DB.SCANNER_USER$ID_USER+"="+id_USER_UF);
		
		if (rl.get_vKeyValues().size()>0) {
			for (String cID: rl.get_vKeyValues()) {
				this.ADD(new RECORD_SCANNER_SETTINGS_SPECIAL(rl.get(cID)),false);
			}
		}
		
	}

	
	public boolean get_bScannerFound() {
		return this.size()>0;
	}

}
