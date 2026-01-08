package calledByName.maskRenderer;

import echopointng.Separator;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class RB_EXT_Separator extends Separator implements IF_external_comp<RB_EXT_Separator> {

	public RB_EXT_Separator() {
		super();
	}
	
	@Override
	public RB_EXT_Separator _setRec20(Rec20 rec, IF_Field key) throws myException {
		return this;
	}

	@Override
	public RB_EXT_Separator _set_definition_rec20(Rec20 rec) throws myException {
		return this;
	}

}
