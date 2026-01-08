package calledByName.maskRenderer;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;

public class RB_EXT_lab extends RB_lab implements IF_external_comp<RB_lab> {

	private Rec20_MASK_DEF_CELL definition_record;

	public RB_EXT_lab() {
		super();
	}
	
	@Override
	public RB_EXT_lab _setRec20(Rec20 rec, IF_Field p_iffield) throws myException {
		this._t(rec.get_fs_dbVal(p_iffield,""));
		return this;
	}

	@Override
	public RB_lab _set_definition_rec20(Rec20 rec) throws myException {
		this.definition_record = new Rec20_MASK_DEF_CELL(rec);
		this._fsa(definition_record.get_text_size());
		
		if(this.definition_record.is_text_bold()) {
			this._fo_bold();
		}
		if(this.definition_record.is_text_italic()) {
			this._fo_italic();
		}
		return this;
	}
}
