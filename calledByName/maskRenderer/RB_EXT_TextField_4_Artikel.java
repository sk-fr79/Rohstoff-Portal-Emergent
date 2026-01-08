package calledByName.maskRenderer;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;

public class RB_EXT_TextField_4_Artikel extends RB_TextAnzeige implements IF_external_comp<RB_TextAnzeige> {

	private Rec20_MASK_DEF_CELL definition_record;

	public RB_EXT_TextField_4_Artikel() throws myException {
		super();
	}

	@Override
	public RB_EXT_TextField_4_Artikel _setRec20(Rec20 rec, IF_Field key_id_artikelbez) throws myException {
		if(S.isFull(rec.get_ufs_kette("", key_id_artikelbez))) {
			Rec20_artikel_bez rec_adr= new Rec20_artikel_bez(new Rec20(_TAB.artikel_bez)._fill_id(rec.get_ufs_kette("", key_id_artikelbez)));

			this._ttt(rec_adr.__get_rec20_artikel().get_fs_dbVal(ARTIKEL.artbez2));

			this.rb_set_db_value_manual(rec_adr.__get_ANR1_ANR2_ARTBEZ1());
		}
		return this;
	}

	@Override
	public RB_EXT_TextField_4_Artikel _set_definition_rec20(Rec20 rec) throws myException {
		this.definition_record = new Rec20_MASK_DEF_CELL(rec);

		this.setDisabledBackground(new E2_ColorBase());

		this.set_bEnabled_For_Edit(false);
		this._bord_d();
		this.setWidth(new Extent(definition_record.get_field_length()));

		return this;
	}
}
