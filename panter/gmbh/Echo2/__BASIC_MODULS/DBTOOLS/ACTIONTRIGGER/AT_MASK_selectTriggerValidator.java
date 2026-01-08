package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import calledByName.triggerValidator.ENUM_TRIGGER_VALIDATOR;
import panter.gmbh.Echo2.RB.COMP.BETA.RB_selField_pop;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.indep.exceptions.myException;

public class AT_MASK_selectTriggerValidator extends RB_selField_pop implements IF_RB_Component_Savable {

	
	/**
	 * @throws myException 
	 * 
	 */
	public AT_MASK_selectTriggerValidator() throws myException {
		super();
		
		this._populate(ENUM_TRIGGER_VALIDATOR.get_array4dropdown());
		this._setTextWidth(400);
	}
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.get_hm_content().get(this.get_tf_actual_keyToShow().getText());
	}

	
	
}
