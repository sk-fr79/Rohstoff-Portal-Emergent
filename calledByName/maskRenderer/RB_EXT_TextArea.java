package calledByName.maskRenderer;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_FIELD_TYPE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;

public class RB_EXT_TextArea extends RB_TextArea implements IF_external_comp<RB_TextArea> {

	private Rec20_MASK_DEF_CELL record_definition = null;

	public RB_EXT_TextArea() throws myException {
		super();
		
		this.set_bEnabled_For_Edit(false);
	}

	@Override
	public RB_TextArea _setRec20(Rec20 rec, IF_Field key) throws myException {
		String tooltip = record_definition.get_usertext();

		if(S.isFull(tooltip)) {
			this._ttt(ENUM_FIELD_TYPE.process_vartext(record_definition, rec));
		}

		this.rb_set_db_value_manual(rec.get_fs_dbVal(key, ""));

		return this;
	}

	@Override
	public RB_TextArea _set_definition_rec20(Rec20 rec) throws myException {
		this.record_definition = new Rec20_MASK_DEF_CELL(rec); 
		this._bord_d();
		
		this.setDisabledBackground(new E2_ColorBase());
		
		this._width(new Extent(record_definition.get_field_length()));
		
		this._height(new Extent(record_definition.get_field_height(),  Extent.PC));
		
		if(this.record_definition.is_text_bold()) {
			this._fo_bold();
		}
		
		if(this.record_definition.is_text_italic()) {
			this._fo_italic();
		}		
		return this;
	}
}
