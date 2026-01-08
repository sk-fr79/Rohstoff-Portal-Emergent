package calledByName.maskRenderer;

import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class RB_EXT_cb extends RB_cb implements IF_external_comp<RB_cb> {
	
	public RB_EXT_cb() throws myException {
		super();
		this.set_bEnabled_For_Edit(false);
	}
	
	@Override
	public RB_EXT_cb _setRec20(Rec20 rec, IF_Field key) throws myException {
		this.rb_set_db_value_manual(rec.get_ufs_kette(" ",key));
		return this;
	}

	@Override
	public RB_cb _set_definition_rec20(Rec20 rec) throws myException {
		return this;
	}
}
