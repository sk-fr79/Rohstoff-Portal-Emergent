package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_textfield_small_old extends RB_TextField_old {
	
	public RB_textfield_small_old(IF_Field sqlF, int iWidthInPixel) throws myException {
		super(sqlF, iWidthInPixel);
		this.set_size_and_font(14, new E2_FontPlain(-2));
	}

	public RB_textfield_small_old(IF_Field sqlF) throws myException {
		super(sqlF);
		this.set_size_and_font(14, new E2_FontPlain(-2));
	}

}
