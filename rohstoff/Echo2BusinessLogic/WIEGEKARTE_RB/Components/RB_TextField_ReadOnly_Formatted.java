package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.RB.COMP.RB_TextFieldReadOnly;
import panter.gmbh.indep.exceptions.myException;

public class RB_TextField_ReadOnly_Formatted extends RB_TextFieldReadOnly {

	public RB_TextField_ReadOnly_Formatted() {
		
	}

	public RB_TextField_ReadOnly_Formatted(int i_width, int i_max_input_size) {
		super(i_width, i_max_input_size);
		
	}

	public RB_TextField_ReadOnly_Formatted(int i_width) {
		super(i_width);
		
	}
	
	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		
		
		super.rb_set_db_value_manual(valueFormated);
	}
	
	
	
	

}
