package panter.gmbh.Echo2.RB.COMP;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class RB_TextField_small extends RB_TextField {

	public RB_TextField_small() {
		super();
	}

	public RB_TextField_small(int i_width, int i_max_input_size) {
		super(i_width, i_max_input_size);
		this._size_and_font(14, new E2_FontPlain(-2));
	}

	public RB_TextField_small(int i_width) {
		super(i_width);
		this._size_and_font(14, new E2_FontPlain(-2));
	}

	
	
	
}
