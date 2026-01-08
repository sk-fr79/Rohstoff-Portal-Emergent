package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.exceptions.myException;

public class HELP2_MASK_selStatus extends RB_selField {

	public HELP2_MASK_selStatus() throws myException {
		super();
		this._populate(HELP2_CONST.TICKET_STATUS.CLOSED.get_dd_Array(false))
			._width(new Extent(100));
	}
	
}
