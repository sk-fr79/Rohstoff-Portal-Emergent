package calledByName.maskRenderer;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_FIELD_TYPE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;

public class RB_EXT_NumericField extends RB_TextAnzeige implements IF_external_comp<RB_TextAnzeige> {

	private Rec20_MASK_DEF_CELL definition_record;

	public RB_EXT_NumericField() throws myException {
		super();
		this._bord(new Border(new Extent(0), new E2_ColorMaskHighlight(), Border.STYLE_NONE));
		this.setDisabledBackground(new E2_ColorMaskHighlight());
		this.set_bEnabled_For_Edit(false);
		this.set_Alignment(E2_ALIGN.RIGHT_BOTTOM);

	}
	
	@Override
	public RB_TextAnzeige _setRec20(Rec20 rec, IF_Field key) throws myException {
	
		String tooltip = definition_record.get_usertext();
		if(S.isFull(tooltip)) {
			this._ttt(ENUM_FIELD_TYPE.process_vartext(definition_record, rec));
		}
		
		this.rb_set_db_value_manual(rec.get_myLong_dbVal(key, new MyLong(0)).get_cF_LongString());
		
		return this;
	}

	@Override
	public RB_TextAnzeige _set_definition_rec20(Rec20 rec) throws myException {
		this.definition_record = new Rec20_MASK_DEF_CELL(rec);
		this.setWidth(new Extent(definition_record.get_field_length()));
		
		this._bord_d();
		
		this.setDisabledBackground(new E2_ColorBase());
		
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
