package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;

public class RB_label_anr1_2 extends RB_LabelFilledWithResult {

	public RB_label_anr1_2() throws myException {
		super("...");

		this._f(get_selected_size().get_font());
		
		this._ttt("ANR1-2");
		this.setLineWrap(true);
	}

	@Override
	public String transferDbValueToVisibleText(String c_id_artikel_bez) throws myException {
		MyLong  l_artbez = new MyLong(c_id_artikel_bez);
		if (l_artbez.get_bOK()) {
			Rec20_artikel_bez r_artbez = new Rec20_artikel_bez(new Rec20(_TAB.artikel_bez)._fill_id(l_artbez.get_lValue()));
			return "(" + r_artbez.__get_ANR1_ANR2()+")";
		}
		return "";
	}

	private static FZ_TEXT_SIZE get_selected_size() throws myException{
		return ENUM_MANDANT_DECISION.FIELDSIZE_4_NEW_BEWEGUNGSMODEL.is_YES()? FZ_TEXT_SIZE.SMALL:FZ_TEXT_SIZE.NORMAL;
	}
}
