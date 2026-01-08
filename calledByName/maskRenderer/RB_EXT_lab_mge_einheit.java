package calledByName.maskRenderer;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;

public class RB_EXT_lab_mge_einheit extends RB_lab implements IF_external_comp<RB_lab> {

	private Rec20_MASK_DEF_CELL record_definition = null;

	public RB_EXT_lab_mge_einheit() throws myException {
		super();
		this.set_bEnabled_For_Edit(false);
	}

	@Override
	public RB_lab _setRec20(Rec20 rec, IF_Field key_id_artikel_bez) throws myException {
		if(S.isFull(rec.get_ufs_kette("", key_id_artikel_bez))) {
			Rec20_artikel_bez rec_art =  new Rec20_artikel_bez(new Rec20(_TAB.artikel_bez)._fill_id(rec.get_ufs_kette("", key_id_artikel_bez)));

			this.rb_set_db_value_manual(rec_art.__get_rec20_artikel().get_einheit_k());
		}
		return this;
	}

	@Override
	public RB_lab _set_definition_rec20(Rec20 rec) throws myException {
		this.record_definition = new Rec20_MASK_DEF_CELL(rec);
		if(this.record_definition.is_text_bold()) {
			this._b();
		}

		if(this.record_definition.is_text_italic()) {
			this._i();
		}

		this._fsa(this.record_definition.get_text_size());
		return this;
	}
}
