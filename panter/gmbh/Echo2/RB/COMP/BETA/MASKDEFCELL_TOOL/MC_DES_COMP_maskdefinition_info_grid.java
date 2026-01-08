package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF;

public class MC_DES_COMP_maskdefinition_info_grid extends E2_Grid implements IF_RB_Component_Savable {

	private RB_KF key;

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private String id_mask_definition = "";
	
	public MC_DES_COMP_maskdefinition_info_grid() throws myException {
		super();
		this._bo_ddd()._setSize(160,220,122,122,96);
	}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(dataObject.rec20() != null) {
			
			if(!(dataObject.rb_MASK_STATUS() == MASK_STATUS.NEW)){
				Rec20 record_mask_def_cell = dataObject.rec20();

				String valueFormated 	= record_mask_def_cell.get_fs_dbVal(MASK_DEF_CELL.id_mask_def);
				this.id_mask_definition 		= valueFormated;
				fill_infogrid(valueFormated);
			}
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		fill_infogrid(valueFormated);
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;
	}

	private void fill_infogrid(String id_mask_def) throws myException {
		this.id_mask_definition = id_mask_def;
		
		this._clear();
		this
		._a(new RB_lab()._t("Maskenname")								, new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
		._a(new RB_lab()._t("Tabellename")								, new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
		._a(new RB_lab()._t("Spaltenzahl")								, new RB_gld()._ins(2)._right_top()._col(new E2_ColorDark()))
		._a(new RB_lab()._t("Spaltenbreite")							, new RB_gld()._ins(2)._right_top()._col(new E2_ColorDark()))
		._a(new RB_lab()._t("Offset")									, new RB_gld()._ins(2)._right_top()._col(new E2_ColorDark()));
		if(S.isFull(id_mask_def)) {
			Rec20_MASK_DEF record_mask_def = new Rec20_MASK_DEF(new Rec20(_TAB.mask_def)._fill_id(bibALL.convertID2UnformattedID(id_mask_def)));

			this
			._a(new RB_lab()._t(""+record_mask_def.get_maskname())			, new RB_gld()._ins(2)._left_top())
			._a(new RB_lab()._t(""+record_mask_def.get_Tablename())			, new RB_gld()._ins(2)._left_top())
			._a(new RB_lab()._t(""+record_mask_def.get_number_of_cols())	, new RB_gld()._ins(2)._right_top())
			._a(new RB_lab()._t(record_mask_def.get_pixel_width() + " px")	, new RB_gld()._ins(2)._right_top())
			._a(new RB_lab()._t(""+record_mask_def.get_mask_offset())		, new RB_gld()._ins(2)._right_top())

			._setSize(160,220,120,120,96);
		}else {
			this._a(new RB_lab()._t("PROBLEM !")._fsa(3)._b(), new RB_gld()._ins(2)._center_mid()._span(5));
		}
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return id_mask_definition;
	}

}
