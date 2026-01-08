package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES.RANGE_KEY;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;

public class __FINDER_MODULE_KEY {
	
	private RANGE_KEY key = null;

	public __FINDER_MODULE_KEY(String TABLENAME) {
		super();
		

		//2015-05-20: neue definition der validierung: via enums, nicht mehr ueber den modul-identifier
		//jetzt nachsehen, ob hier das allgemeine oder das 
		String cTablenameBase = new Archiver_Normalized_Tablename(TABLENAME).get_TableBaseName();
		String CallingName = VALID_ENUM_MODULES.PREFIX_4_ATTACHMENT_POPUPS+cTablenameBase;
		
		//jetzt nachsehen, ob es einen solchen VALID_ENUM_MODULES gibt
		this.key = VALID_ENUM_MODULES.find_RANGE_KEY(CallingName);
		
		//falls kein explizites modul hinterlegt ist, dann das allgemein-Upload-Modul nutzt
		if (key == null) {
			key = VALID_ENUM_MODULES.RANGE_KEY.ATTACHMENT_ALL_OTHERS;
		}
		
	}

	public RANGE_KEY get_Key() {
		return key;
	}

}
