package panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED;

import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES.RANGE_KEY;
import panter.gmbh.indep.enumtools.IF_enum_4_db;

public class VALID_ENUM_AUTH_VALIDATOR extends E2_ButtonAUTHValidator {

	private IF_enum_4_db 					Button = null;
	private VALID_ENUM_MODULES.RANGE_KEY  	RangeKey = null;
	
	public VALID_ENUM_AUTH_VALIDATOR(RANGE_KEY rangeKey, IF_enum_4_db button) {
		super(rangeKey.db_val(), button.db_val());
		this.Button = button;
		this.RangeKey = rangeKey;
	}

	public IF_enum_4_db get_Button() {
		return Button;
	}

	public VALID_ENUM_MODULES.RANGE_KEY get_RangeKey() {
		return RangeKey;
	}
	   

}
