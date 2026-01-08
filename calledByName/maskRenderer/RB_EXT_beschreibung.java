package calledByName.maskRenderer;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class RB_EXT_beschreibung extends RB_lab implements IF_external_comp<RB_lab> {

	@Override
	public RB_EXT_beschreibung _setRec20(Rec20 rec, IF_Field key) throws myException {
		return this;
	}

	@Override
	public RB_lab _set_definition_rec20(Rec20 rec) throws myException {
		
		return this;
	}
}
