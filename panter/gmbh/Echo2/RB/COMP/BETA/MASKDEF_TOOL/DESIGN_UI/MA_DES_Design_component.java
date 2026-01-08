package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.DESIGN_UI;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.Std_action_popup_frage_yes_no;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.BETA.E2_PopUpMenue;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_CellModel;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_Design_component extends E2_Grid {

	private MA_DES_bt_ui_edit_cell 	mask_calling_bt 	= null;
	private RB_TextField 			display_field 		= null;
	private MC_DES_CellModel 		zelle_definition 	= null;
	private E2_Button				loeschen_bt 		= null;

	private E2_PopUpMenue			popUp 				= null;

	private MA_DES_design_ui		dui 				= null;

	public MA_DES_Design_component(MA_DES_design_ui p_dui, MC_DES_CellModel zelle) throws myException {
		super();	

		this.zelle_definition = zelle;

		this.dui = p_dui;

		this.popUp = new E2_PopUpMenue(
				E2_ResourceIcon.get_RI("tiny_right.png"), 
				E2_ResourceIcon.get_RI("tiny_right.png"), 
				true,
				new Extent(150), new Extent(50),
				2, 42
				);

		this.mask_calling_bt = new MA_DES_bt_ui_edit_cell(dui, zelle_definition.get_id());
		this.mask_calling_bt
		._t("Zelle bearbeiten")
		._aaa(new own_call_edit_mask());

		this.loeschen_bt = new delete_linie_bt();
				

		int colSpan = 	this.zelle_definition.get_zelle_record().get_myLong_dbVal(MASK_DEF_CELL.colspan).get_iValue();
		int pix_width = this.zelle_definition.get_mask_record().get_myLong_dbVal(MASK_DEF.pixel_width).get_iValue();

		int max_field_width = (colSpan*pix_width) - 16;

		this.display_field = new RB_TextField(max_field_width)._bord_ddd()._col_back_d();
		this.display_field.set_bEnabled_For_Edit(false);

		this.fill_component();

		this.popUp.addButton(mask_calling_bt, 	true);
		this.popUp.addButton(loeschen_bt, 		true);

		this._a_lt(display_field)._a_lm(popUp);
	}

	private void fill_component() throws myException {
		String 	usrText 	= 	zelle_definition.get_usertext();
		String 	fieldname 	= 	zelle_definition.get_fieldname();
		int 	field_lgth	= 	zelle_definition.get_zelle_record().get_myLong_dbVal(MASK_DEF_CELL.field_length, new MyLong(0)).get_iValue();

		boolean is_lbl = false;

		if(S.isAllFull(usrText, fieldname)) {
			display_field.setText(fieldname);
			display_field._ttt(fieldname);
		}else if(S.isFull(usrText) && S.isEmpty(fieldname)) {
			display_field.setText( usrText );
			display_field.setDisabledBackground(new E2_ColorBase());
			is_lbl = true;
		}else if(S.isEmpty(usrText) && S.isFull(fieldname)) {
			display_field.setText(fieldname);
			display_field._ttt(fieldname);
		}else {
			display_field.setText("ERROR !");
		}
		
		if(!is_lbl) {
			if(field_lgth == 0) {
				display_field.setWidth(new Extent(dui.get_record_mask_definition().get_pixel_width()-16));
			}else {
				display_field.setWidth(new Extent(field_lgth));
			}
		}
	}

	public MC_DES_CellModel get_zelle_definition() {
		return zelle_definition;
	}

	private class delete_linie_bt extends E2_Button implements IF_components_4_decision{
		private Vector<XX_ActionAgent> 		v_storage_vector_4_action_agents = new Vector<>();
		private Vector<XX_ActionAgent> 		v_storage_vector_4_status_at_start = new Vector<>();

		public delete_linie_bt() throws myException {
			super();
			this._t("Zelle loeschen");
			this._aaa(new owndeleteconfirmation(this));
			this._aaa(new own_loeschen_aa());
		}

		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_action_agents() throws myException {
			return v_storage_vector_4_action_agents;
		}

		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_status_at_start() throws myException {
			return v_storage_vector_4_status_at_start;
		}
	}

	private class own_call_edit_mask extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

		}
	}

	private class own_loeschen_aa extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			dui.zelle_loeschen(MA_DES_Design_component.this.zelle_definition.get_id());

			dui.zelle_loeschen(MA_DES_Design_component.this.zelle_definition.get_id());
		}

	}
	
	
	
	private class owndeleteconfirmation extends Std_action_popup_frage_yes_no{

		public owndeleteconfirmation(IF_components_4_decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		protected void define_buttons_and_fill_grid_in_info_popup(MyE2_Button bt_ok, MyE2_Button bt_cancel,
				MyE2_Grid grid_4_info) throws myException {

			bt_ok.setText(new MyE2_String("Ja").CTrans());;
			bt_cancel.setText(new MyE2_String("Nein").CTrans());
			int[] i_breite = {200,200};
			grid_4_info.set_Spalten(i_breite);
			grid_4_info.add(new E2_Grid4MaskSimple()
					.def_(new Alignment(Alignment.CENTER, Alignment.CENTER))
					.def_(E2_INSETS.I(2,5,2,5))
					.def_(2,1)
					.add_(new MyE2_Label(new MyE2_String("Wollen sie diese Linie loeschen? ")))
					.def_(E2_INSETS.I(2,15,2,5))
					.def_(1, 1)
					.add_(bt_ok)
					.add_(bt_cancel)
					.setSize_(i_breite)
					);			

		}

		@Override
		protected MyE2_String get_popup_titel_string() throws myException {
			return new MyE2_String("Sind Sie sicher ?");
		}

		@Override
		protected Extent get_width_of_popup() {
			return new Extent(370);
		}

		@Override
		protected Extent get_height_of_popup() {
			return new Extent(180);
		}



	}
	
}
