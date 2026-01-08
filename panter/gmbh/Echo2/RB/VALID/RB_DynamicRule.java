package panter.gmbh.Echo2.RB.VALID;

import java.util.Vector;


public class RB_DynamicRule {
	
	private boolean AllowEdit = true;
	private boolean AllowEmpty = true;
	
	private String  RegEx = null;
	private String  Predefined = null;
	
	/**
	 * falls regex-settings greift, dann wird hier der validator in der rule abgelegt und nach den anderen Validators ausgefuehrt
	 */
	private Vector<RB_Validator_Component>  v_compVALIDATOR_4_INPUT = new Vector<RB_Validator_Component>();
	
	
	public RB_DynamicRule() {
		super();
	}
	
	
//	public RB_DynamicRule(boolean allowEdit, boolean allowEmpty, String regEx, String predefined) {
//		super();
//		AllowEdit = allowEdit;
//		AllowEmpty = allowEmpty;
//		RegEx = regEx;
//		Predefined = predefined;
//	}


	public boolean is_AllowEdit() {
		return AllowEdit;
	}


	public boolean is_AllowEmpty() {
		return this.AllowEmpty;
	}


	public String get_RegEx() {
		return RegEx;
	}


	public String get_Predefined() {
		return Predefined;
	}


	public void set_AllowEdit(boolean allowEdit) {
		AllowEdit = allowEdit;
	}


	public void set_AllowEmpty(boolean allowEmpty) {
		AllowEmpty = allowEmpty;
	}


	public void set_RegEx(String regEx) {
		RegEx = regEx;
	}


	public void set_Predefined(String predefined) {
		Predefined = predefined;
	}


	public Vector<RB_Validator_Component> get_vCompVALIDATOR_4_INPUT() {
		return this.v_compVALIDATOR_4_INPUT;
	}


			
		
		
	
	
}
