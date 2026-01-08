package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class WK_RB_lbl_storno extends RB_LabelFilledWithResult implements IF_RB_Component_Savable{
	
	private String _storno = "";
	
	public WK_RB_lbl_storno() throws myException {
		super("");
		this._ttt("Storno-Kennzeichen");
		this._fo_s_plus(2);
		this._fo_bold();
	}

	@Override
	public String transferDbValueToVisibleText(String storno) throws myException {
		this._col_back_base();
		if(S.isFull(storno) && storno.equalsIgnoreCase("Y")) {
			_storno = "Y";
			this._col_back_alarm();
			return " STORNO ";
		}
		return "";
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return _storno;
	}
	
	
}
