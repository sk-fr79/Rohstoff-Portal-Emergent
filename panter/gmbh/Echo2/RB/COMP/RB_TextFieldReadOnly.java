package panter.gmbh.Echo2.RB.COMP;

import panter.gmbh.indep.exceptions.myException;

public class RB_TextFieldReadOnly extends RB_TextField	{

	public RB_TextFieldReadOnly() {
		super();
		this.setEnabled(false);
		this.setFocusTraversalParticipant(false);
	}

	public RB_TextFieldReadOnly(int i_width, int i_max_input_size) {
		super(i_width, i_max_input_size);
		this.setEnabled(false);
		this.setFocusTraversalParticipant(false);
	}

	public RB_TextFieldReadOnly(int i_width) {
		super(i_width);
		this.setEnabled(false);
		this.setFocusTraversalParticipant(false);
	}

	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		super.set_bEnabled_For_Edit(false);
	}
	

	
	
	
}
