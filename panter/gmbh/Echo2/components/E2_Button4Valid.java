package panter.gmbh.Echo2.components;

import panter.gmbh.basics4project.VALIDATOR_KEY;

/**
 * button-klasse mit implizitem validier-kenner 
 * @author martin
 *
 */
public class E2_Button4Valid extends E2_Button {
	
	private VALIDATOR_KEY key = null;

	public E2_Button4Valid(VALIDATOR_KEY p_key) {
		super();
		this.key = p_key;
		this.add_GlobalValidator(this.key.get_authValidator());
	}

	
	public VALIDATOR_KEY get_key() {
		return key;
	}
	
	
}
