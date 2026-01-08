package calledByName.maskRenderer;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_FIELD_TYPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class RB_EXT_TextField_4_Adresse extends RB_TextAnzeige implements IF_external_comp<RB_TextAnzeige>{

	private Rec20_MASK_DEF_CELL definition_record = null;

	public RB_EXT_TextField_4_Adresse() throws myException {
		super();
	}

	@Override
	public RB_TextAnzeige _setRec20(Rec20 rec, IF_Field key_id_adresse) throws myException {
		if(S.isFull(rec.get_ufs_kette("", key_id_adresse))){
			Rec20_adresse rec_adr= new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(rec.get_ufs_kette("", key_id_adresse)));

			String tooltip = definition_record.get_usertext();
			if(S.isFull(tooltip)) {
				this._ttt(ENUM_FIELD_TYPE.process_vartext(definition_record, rec_adr));
			}

			this.rb_set_db_value_manual(rec_adr.__get_name1_ort());
		}
		return this;
	}

	@Override
	public RB_TextAnzeige _set_definition_rec20(Rec20 rec) throws myException {
		this.definition_record = new Rec20_MASK_DEF_CELL(rec);
		this.setDisabledBackground(new E2_ColorBase());

		this.setWidth(new Extent(definition_record.get_field_length()));

		this._bord_d();
		this.set_bEnabled_For_Edit(false);
		return this;
	}
}
