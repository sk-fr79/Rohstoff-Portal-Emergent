package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.DESIGN_UI;

import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.Std_action_popup_frage_yes_no;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_MASKNAME;
import panter.gmbh.Echo2.RB.COMP.BETA.IF_Mask_Definition;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_COMP_maskdefinition_info_grid;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_CellModel;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF;

public class MA_DES_design_ui extends E2_BasicModuleContainer {

	private Rec20_MASK_DEF 				record_mask_def 		= null;

	private E2_Grid 					mask_grid 				= new E2_Grid();

	private VEK<VEK<MC_DES_CellModel>> 	vLinieList 				= new VEK<>();

	private int 						i_linie_idx = 1;

	private boolean[][]				 is_occupied_matrix = null;

	IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> 
	{E2_Grid g = new E2_Grid()._setSizeSingleLine(); for (Component c: comps) {g._gld(new RB_gld()._ins(2,2,5,2))._a_lm(c);} return g._s(comps.length); };

	public MA_DES_design_ui(String id_mask_def__uf, boolean b_is_new) throws myException {
		super();
		this.record_mask_def = new Rec20_MASK_DEF(new Rec20(_TAB.mask_def)._fill_id(id_mask_def__uf));

		E2_Button test_bt = new E2_Button()._image(E2_ResourceIcon.get_RI("test_mask.png"));
		test_bt._ttt("Masken testen");
		test_bt._aaa(new own_test_aa());

		this.fill_list();

		this.render_grid();


		E2_Grid bedien_grid = new E2_Grid()._s(4)._ins(E2_INSETS.I(5))
				._a(test_bt, 				new RB_gld()._left_mid()._ins(2))
				//		._a(new add_linie_bt(0), 	new RB_gld()._left_mid()._ins(20,2,2,2))
				;

		MC_DES_COMP_maskdefinition_info_grid info_panel = new MC_DES_COMP_maskdefinition_info_grid();
		info_panel.set_rb_RB_K(MASK_DEF.id_mask_def.fk());
		info_panel.rb_set_db_value_manual(id_mask_def__uf);

		MyE2_ContainerEx excont = new MyE2_ContainerEx(mask_grid);
		excont.setHeight(new Extent(620));

		this.add(bedien_grid, 		E2_INSETS.I(2,2,2,5));
		this.add(info_panel, 		E2_INSETS.I(2,5,2,5));
		this.add(new Separator(),	E2_INSETS.I(2,5,2,5));
		this.add(excont, 			E2_INSETS.I(2,5,2,5));
	}

	public void fill_list() throws myException{
		vLinieList.clear();

		for(int i = 0; i<record_mask_def.get_max_row_from_list(); i++) {

			VEK<MC_DES_CellModel> vLinie = new VEK<>();
			if(this.record_mask_def.get_cell_list_by_row(i+1).size()>0) {
				for(Rec20 rec_cell : this.record_mask_def.get_cell_list_by_row(i+1)) {
					vLinie._a(new MC_DES_CellModel(rec_cell));
				}
			}
			vLinieList._a(vLinie);
		}
	}

	public void render_grid() throws myException {

		mask_grid._clear()._bo_ddd();

		MyE2_MessageVector mv = new MyE2_MessageVector();

		int imaxCols = record_mask_def.get_number_of_cols()+1 ;
		int imaxWidth = record_mask_def.get_pixel_width();
		int mask_offset = record_mask_def.get_mask_offset();

		//breite
		int[] cols = new int[imaxCols];
		for(int i= 0 ; i<imaxCols;i++){
			cols[i] = imaxWidth;
		}
		mask_grid._setSize(cols);

		for(int i=1; i<imaxCols;i++){
			RB_gld gl1 = new RB_gld(); 
			if(i<=mask_offset) {
				gl1 =  new RB_gld()._ins(2)._center_mid()._col(new E2_ColorBase());
			}else {
				gl1 = new RB_gld()._ins(2)._center_mid()._col(new E2_ColorDark());
			}
			mask_grid._a(new RB_lab(""+(i))._i()._fsa(-2), gl1);
		}
		mask_grid._a("", new RB_gld()._ins(2)._span(3)._center_mid());

		if(vLinieList.size()==0) {
			add_new_linie();
		}else {
			fill_grid(mv);
		}

		bibMSG.add_MESSAGE(mv);	
	}

	private void fill_grid(MyE2_MessageVector mv) throws myException {

		virtual_representation();

		int mask_offset = this.record_mask_def.get_mask_offset();
		
		i_linie_idx = 1;
		int y = 0;

		for(VEK<MC_DES_CellModel> vLinie : vLinieList) {
			if(mask_offset>0) {
				mask_grid._a(new RB_lab(), new RB_gld()._span(mask_offset));
			}
			for(int x=0; x<(this.is_occupied_matrix[y].length);x++) {
				for(MC_DES_CellModel zelle : vLinie) {
					if(zelle.get_start_col()==(x+1) ){

						int i_col_span = zelle.get_colspan();
						int i_row_span = zelle.get_rowspan();

						RB_gld grid_layout = new RB_gld()._left_top()
								._ins(2)
								._span(i_col_span)
								._span_r(i_row_span)
								;

						MA_DES_Design_component comp = new MA_DES_Design_component(this,zelle);

						this.mask_grid._a(comp, grid_layout);
					}
				}

				if(this.is_occupied_matrix[y][x]==false) {
					mask_grid._a_cm(new MA_DES_bt_ui_add_cell(this, x , i_linie_idx));		
				}

			}

			mask_grid._a(wrap.grid(new delete_linie_bt(i_linie_idx), new MA_DES_bt_add_linie(this, i_linie_idx)));

			i_linie_idx ++ ;
			y++;
		}
	}


	private void virtual_representation() throws myException{
		int imaxcols = 		this.record_mask_def.get_number_of_cols();
		int imaxrows = 		this.vLinieList.size();
		int mask_offset = 	this.record_mask_def.get_mask_offset();

		is_occupied_matrix = new boolean[this.vLinieList.size()][imaxcols];

		for(int y = 0; y<imaxrows;y++) {
			for (int x=0; x<mask_offset;x++) {
				is_occupied_matrix[y][x] = true;
			}
			for (int x=mask_offset; x<imaxcols;x++) {
				is_occupied_matrix[y][x] = false;
			}
		}

		for(int y = 0; y<imaxrows;y++) {
			RecList20 row_list = this.record_mask_def.get_cell_list_by_row(y+1);

			for (int x=0; x<imaxcols;x++) {
				for(Rec20 cell:row_list) {
					if(is_occupied_matrix[y][x]== false) {
						if(x== (cell.get_myLong_dbVal(MASK_DEF_CELL.start_col_in_mask).get_iValue()-1)) {

							is_occupied_matrix[y][x] = true;
							int colspan = cell.get_myLong_dbVal(MASK_DEF_CELL.colspan).get_iValue();
							int rowspan = cell.get_myLong_dbVal(MASK_DEF_CELL.rowspan).get_iValue();

							for(int xspan = x; xspan<(x+colspan);xspan++) {
								is_occupied_matrix[y][xspan] = true;

								if(rowspan>1) {
									int max_rowspan = (y+rowspan);
									if(max_rowspan>imaxrows) {
										max_rowspan = imaxrows;
									}

									for(int yspan = y; yspan<max_rowspan;yspan++) {
										is_occupied_matrix[yspan][xspan] = true;
									}
								}
							}

						}
					}
				}
			}
		}
	}

	public void add_new_linie_before(int idx_add_before) throws myException{
		if(idx_add_before == 0) {
			vLinieList.add(idx_add_before, new VEK<MC_DES_CellModel>());
		}else {

			vLinieList.add(idx_add_before-1, new VEK<MC_DES_CellModel>());

		}

		add_linie_index_in_db(idx_add_before, true);

		render_grid();

	}

	public void add_new_linie_after(int idx_add_after) throws myException{
		if(idx_add_after<this.vLinieList.size()) {

			vLinieList.add(idx_add_after, new VEK<MC_DES_CellModel>());

			add_linie_index_in_db(idx_add_after, false);
		}else {

			vLinieList._a(new VEK<MC_DES_CellModel>());

			render_grid();
		}

	}

	private void add_new_linie() throws myException{
		int offset = 		this.record_mask_def.get_mask_offset();
		int imaxCols = 		this.record_mask_def.get_number_of_cols();
		int new_row_index = this.record_mask_def.get_max_row_from_list() + 1;

		if(offset>0) {
			this.mask_grid._a("", new RB_gld()._span(offset));
		}
		for(int i = offset;i < imaxCols ; i++) {
			this.mask_grid._a(new MA_DES_bt_ui_add_cell(this, i+1, i_linie_idx),new RB_gld()._center_mid()._ins(0,4,0,4));
		}

		this.mask_grid._a(
				wrap.grid(new delete_linie_bt(new_row_index), new MA_DES_bt_add_linie(this, i_linie_idx))
				);

		vLinieList._a(new VEK<MC_DES_CellModel>());

		i_linie_idx ++ ;

	}

	public void zelle_loeschen(String uf_id_zelle) throws myException{

		String query_delete = "DELETE FROM "+ MASK_DEF_CELL.fullTabName() + " WHERE " +
				new vgl(MASK_DEF_CELL.id_mandant, bibALL.get_ID_MANDANT()).s() +" AND " +
				new vgl(MASK_DEF_CELL.id_mask_def, record_mask_def.get_ufs_dbVal(MASK_DEF.id_mask_def)).s() + " AND " +
				new vgl(MASK_DEF_CELL.id_mask_def_cell ,uf_id_zelle).s();

		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(!bibDB.ExecSQL(query_delete, false)) {
			mv._addAlarm("Es ist ein Fehler bei der loeschen der Zelle aufgetreten !");
		}

		if(mv.get_bIsOK()) {
			bibDB.Commit();
			mv._addInfo("Die Zelle wurde geloescht.");
			MA_DES_design_ui.this.fill_list();
			MA_DES_design_ui.this.render_grid();
		}
		if(mv.size()>0) {
			bibMSG.add_MESSAGE(mv);
		}
	}

	private class delete_linie_bt extends E2_Button implements IF_components_4_decision{
		private Vector<XX_ActionAgent> 		v_storage_vector_4_action_agents = new Vector<>();
		private Vector<XX_ActionAgent> 		v_storage_vector_4_status_at_start = new Vector<>();

		public delete_linie_bt(int i_row_index) throws myException {
			super();
			this._image( E2_ResourceIcon.get_RI("delete.png"));
			this.EXT().set_C_MERKMAL(""+i_row_index);
			this._ttt("Linie loeschen");
			this._aaa(new owndeleteconfirmation(this));
			this._aaa(new ownDeleteActionAgent(this));
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

	public Rec20_MASK_DEF get_record_mask_definition() {
		return record_mask_def;
	}

	private class ownDeleteActionAgent extends XX_ActionAgent{
		private E2_Button pbt;

		public ownDeleteActionAgent(E2_Button parent_bt) {
			super();
			this.pbt = parent_bt;
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			del_linie_index_in_db(Integer.parseInt(pbt.EXT().get_C_MERKMAL()));
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

	private class test_module_container extends E2_BasicModuleContainer{
	}

	private class own_test_aa extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			test_module_container test_cont = new test_module_container();

			IF_Mask_Definition test_mask_definition = new IF_Mask_Definition() {

				@Override
				public ENUM_MASKNAME get_maskname() throws myException {
					return ENUM_MASKNAME.valueOf(MA_DES_design_ui.this.record_mask_def.get_maskname());
				}
			};

			Rec20 test_record = new Rec20(_TAB.find_TableFromBasename(MA_DES_design_ui.this.record_mask_def.get_Tablename()));
			VEK<Rec20> testvek = new VEK<Rec20>()._a(test_record);

			test_cont.add(test_mask_definition._render(testvek)._bc(new E2_ColorDark())._bo_ddd());
			test_cont.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(600), new MyE2_String("Mask test"));
		}

	}

	private MyE2_MessageVector add_linie_index_in_db(int new_linie_index, boolean b_add_oben) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		int start_point = b_add_oben?new_linie_index:(new_linie_index+1);
		VEK<Rec20> record_2_update = new VEK<Rec20>();

		for(int i=start_point; i<vLinieList.size();i++) {
			record_2_update._a(record_mask_def.get_cell_list_by_row(i).values());
		}

		for(int i=0; i<record_2_update.size();i++) {
			Rec20 rec = record_2_update.get(i);
			int updated_idx = 	rec.get_myLong_dbVal(MASK_DEF_CELL.start_row_in_mask).get_iValue()+1;

			rec._nv(MASK_DEF_CELL.start_row_in_mask, ""+updated_idx, mv);

			rec._SAVE(true, mv);
		}

		fill_list();

		render_grid();

		return mv;
	}

	private MyE2_MessageVector del_linie_index_in_db(int linie_index) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();

		int start_point = linie_index;
		VEK<Rec20> record_2_update = new VEK<Rec20>();
		VEK<String> sql_stmt = new VEK<>();

		for(int i=start_point; i<vLinieList.size();i++) {
			record_2_update._a(record_mask_def.get_cell_list_by_row(i+1).values());
			DEBUG.System_println("*->"+i);
		}

		sql_stmt._a(record_mask_def.get_cell_list_by_row(linie_index).get_sqls_2_delete());

		for(int i=0; i<record_2_update.size();i++) {
			Rec20 rec = record_2_update.get(i);
			int updated_idx = 	rec.get_myLong_dbVal(MASK_DEF_CELL.start_row_in_mask).get_iValue()-1;

			rec._nv(MASK_DEF_CELL.start_row_in_mask, ""+updated_idx, mv);

			sql_stmt._a(rec.get_sql_4_save(true));

		}

		mv._add(bibDB.ExecMultiSQLVector(sql_stmt, false));

		if(mv.get_bIsOK()) {
			bibDB.Commit();
		}

		fill_list();

		render_grid();

		return mv;
	}
}
