package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;

public class __FS_MASK_COMPONENT_SEARCH_EAK_CODES extends MASK_COMPONENT_SEARCH_EAK_CODES {

	public __FS_MASK_COMPONENT_SEARCH_EAK_CODES(SQLField osqlField) throws myException {
		super(osqlField, null, null);
		
		this.get_oTextForAnzeige().set_iWidthPixel(600);
		
		
	}

}
