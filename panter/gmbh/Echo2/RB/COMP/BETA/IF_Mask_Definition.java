package panter.gmbh.Echo2.RB.COMP.BETA;

import java.util.Vector;

import calledByName.maskRenderer.IF_external_comp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.reflection.bibReflect;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;

public interface IF_Mask_Definition {

	public ENUM_MASKNAME get_maskname() throws myException;

	@SuppressWarnings("unchecked")
	default E2_Grid _render(Vector<Rec20> rec20_dateien) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();

		E2_Grid mask_grid = new E2_Grid()._bo_ddd();

		//step 1: mask_definition
		if(rec20_dateien.size()>0){
			int imaxWidth 	= 0;
			int imaxCols 	= 0;

			for(Rec20 datei: rec20_dateien){
				_TAB tab  = datei.get_tab();

				SEL mask_def_query = new SEL().FROM(_TAB.mask_def)
						.WHERE(new vgl(MASK_DEF.tablename, tab.baseTableName()))
						.AND(new vgl(MASK_DEF.maskname, get_maskname().db_val()))
						.AND(new vgl(MASK_DEF.id_mandant, bibALL.get_ID_MANDANT()));

				Rec20_MASK_DEF record_mask_definition = new Rec20_MASK_DEF(new Rec20(_TAB.mask_def)._fill_sql(mask_def_query.s()) );	

				if(record_mask_definition.is_ExistingRecord()){
					if(imaxWidth<record_mask_definition.get_pixel_width()){
						imaxWidth = record_mask_definition.get_pixel_width();
					}
					if(imaxCols<record_mask_definition.get_number_of_cols()){
						imaxCols = record_mask_definition.get_number_of_cols();
					}
				}else{
					mv._addAlarm("Die Tabelle " + tab.baseTableName() + " hat keine angehängt Mask Definition.");
				}
			}

			int[] cols = new int[imaxCols];
			for(int i= 0 ; i<imaxCols;i++){
				cols[i] = imaxWidth;
			}
			mask_grid._setSize(cols);



			for(Rec20 datei: rec20_dateien){
				_TAB tab  = datei.get_tab();

				SEL mask_def_query = new SEL().FROM(_TAB.mask_def)
						.WHERE(new vgl(MASK_DEF.tablename, tab.baseTableName()))
						.AND(new vgl(MASK_DEF.id_mandant, bibALL.get_ID_MANDANT()))
						.AND(new vgl(MASK_DEF.maskname, get_maskname().db_val()));

				Rec20_MASK_DEF record_mask_definition = new Rec20_MASK_DEF(new Rec20(_TAB.mask_def)._fill_sql(mask_def_query.s()) );	

				if(S.isFull(record_mask_definition.get_fs_dbVal(MASK_DEF.id_mask_def))) {

					int imaxcols = record_mask_definition.get_number_of_cols();
					int imaxrows = record_mask_definition.get_max_row_from_list();
					int mask_offset = record_mask_definition.get_mask_offset();

					boolean[][] is_visible_representation = new boolean[imaxrows][imaxcols];

					for(int y=0;y<imaxrows; y++) {
						for(int x=0;x<imaxcols; x++) {
							if(x<record_mask_definition.get_mask_offset()) {
								is_visible_representation[y][x]	= true;
							}else {
								is_visible_representation[y][x] = false;
							}
						}
					}

					//populate visible/not visible matrix
					for(int y = 0; y<imaxrows;y++) {
						RecList20 row_list = record_mask_definition.get_cell_list_by_row(y+1);
						for (int x=0; x<imaxcols;x++) {
							for(Rec20 cell:row_list) {
								if(is_visible_representation[y][x]== false) {
									if(x== (cell.get_myLong_dbVal(MASK_DEF_CELL.start_col_in_mask).get_iValue()-1)) {

										is_visible_representation[y][x] = true;
										int colspan = cell.get_myLong_dbVal(MASK_DEF_CELL.colspan).get_iValue();
										int rowspan = cell.get_myLong_dbVal(MASK_DEF_CELL.rowspan).get_iValue();

										for(int xspan = x; xspan<(x+colspan);xspan++) {
											is_visible_representation[y][xspan] = true;

											if(rowspan>1) {
												int max_rowspan = (y+rowspan);
												if(max_rowspan>imaxrows) {
													max_rowspan = imaxrows;
												}

												for(int yspan = y; yspan<max_rowspan;yspan++) {
													is_visible_representation[yspan][xspan] = true;
												}
											}
										}
									}
								}
							}
						}
					}

					//fill grid;
					for(int row_idx=1; row_idx<imaxrows+1; row_idx++){

						mask_grid.setRowHeight(row_idx, new Extent(25));
						RecList20 rec_cell_list  = record_mask_definition.get_cell_list_by_row(row_idx);
						
						if(mask_offset>0) {
							mask_grid._a(new RB_lab(), new RB_gld()._span(mask_offset));
						}
						
						for(int x=0; x<(is_visible_representation[row_idx-1].length);x++) {
							int i = 0;
							for(Rec20 record : rec_cell_list) {
								Rec20_MASK_DEF_CELL zelle_record = new Rec20_MASK_DEF_CELL(record);

								if(zelle_record.get_column_coordinate()==(x+1)) {
									int i_col_span = zelle_record.get_column_span();
									int i_row_span = zelle_record.get_row_span();

									E2_Grid comp = null;
									Alignment cmp_align = new Alignment(Alignment.LEFT, Alignment.TOP);

									if(S.isFull(zelle_record.get_fs_dbVal(MASK_DEF_CELL.alignment))) {
										cmp_align = zelle_record.get_alignment();
									}

									String usrText 		= zelle_record.get_usertext();
									String fieldname 	= zelle_record.get_fieldname();
									String classname 	= zelle_record.get_classname();	

									if(S.isFull(classname)) {
										IF_external_comp<Component> cmp_obj = (IF_external_comp<Component>) bibReflect.createInstanceClassForName(classname);

										cmp_obj._set_definition_rec20(zelle_record);

										if(S.isFull(fieldname)) {
											if(datei.get_field(fieldname) == null) {
												mv._addAlarm("Der Feld " + fieldname +" existiert nicht in der Tabelle " + datei.get_TABLENAME() + " !" );
											}else {

												cmp_obj._setRec20(datei, 	datei.get_field(fieldname).get_field());
											}
										}else if(S.isEmpty(fieldname) && S.isFull(usrText)) {
											cmp_obj._setRec20(rec_cell_list.get(i), MASK_DEF_CELL.usertext);
										}

										comp = cmp_obj.getRenderedContainer();

									}else {

										if(S.isAllFull(usrText, fieldname) && S.isEmpty(classname)){
											RB_TextAnzeige cmp_obj = new RB_TextAnzeige(zelle_record.get_field_length());
											cmp_obj._ttt(usrText);
											cmp_obj.set_bEnabled_For_Edit(false);
											cmp_obj.rb_set_db_value_manual(datei.get_fs_dbVal(datei.get_field(fieldname).get_field()));
											comp = new E2_Grid()._s(1)._a(cmp_obj);
										}
										else if(S.isFull(fieldname) && S.isEmpty(usrText) && S.isEmpty(classname)){
											RB_TextAnzeige cmp_obj = new RB_TextAnzeige(zelle_record.get_field_length());
											cmp_obj.set_bEnabled_For_Edit(false);
											cmp_obj.rb_set_db_value_manual(datei.get_fs_dbVal(datei.get_field(fieldname).get_field()));
											comp = new E2_Grid()._s(1)._a(cmp_obj);
										}else if(S.isFull(usrText) && S.isEmpty(fieldname)){
											comp = new E2_Grid()._s(1)._a(new RB_lab()._t(usrText));
										}
										else{
											comp = new E2_Grid()._s(1)._a();
										}
									}
									i++;

									RB_gld grid_layout = new RB_gld()._left_top()
											._ins(zelle_record.get_Insets())
											._span(i_col_span)
											._span_r(i_row_span)
											._al(cmp_align)
											;

									mask_grid._a(comp, grid_layout);

								}

							}

							if(is_visible_representation[row_idx-1][x]==false) {
								mask_grid._a("", new RB_gld()._span(1)._span_r(1)._center_mid());		
							}

						}
					}
				}
			}
		}
		return mask_grid;
	}
}
