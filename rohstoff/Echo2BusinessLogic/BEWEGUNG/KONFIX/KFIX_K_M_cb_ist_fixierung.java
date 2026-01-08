package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_cb_ist_fixierung extends RB_CheckBox {

	/**
	 * @param osqlField
	 * @throws myException
	 */
	public KFIX_K_M_cb_ist_fixierung(IF_Field osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void mark_MustField() throws myException	{
	}

}
