package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea_old;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_textarea_small extends RB_TextArea_old{
	
	public RB_textarea_small(IF_Field sqlF, int iWidthInPixel, int i_height_in_pixel) throws myException {
		super(sqlF, iWidthInPixel, 2);
		this.set_size_and_font(iWidthInPixel,i_height_in_pixel, new E2_FontPlain(-2));
	}

}
