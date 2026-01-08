package panter.gmbh.Echo2.RB.BETA_MASK_DEF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class RB_Mask_Grid extends E2_Grid {

	private HashMap<String, RB_Mask_Descriptor> hmMaskDescriptor = new HashMap<String, RB_Mask_Descriptor>();

	private int 				max_cols ;
	private int 				max_breite;
	private MyE2_MessageVector 	mv;

	private static int DEFAULT_BREITE = 30;

	public RB_Mask_Grid() {
		super();
		mv = new MyE2_MessageVector();
	}

	public RB_Mask_Grid _a_mask_descriptor(RB_Mask_Descriptor p_Mask_Descriptor) throws myException{
		this.hmMaskDescriptor.put(p_Mask_Descriptor.getTabelle().baseTableName(), p_Mask_Descriptor);
		return this;
	} 

	private void init_grid() throws myException{
		VEK<Integer> vCol 	= new VEK<Integer>();
		VEK<Integer> vBreite = new VEK<Integer>();

		for(String mDescKey: hmMaskDescriptor.keySet()) {
			vCol._a(	hmMaskDescriptor.get(mDescKey).get_spaltenZahl()	);
			vBreite._a(	hmMaskDescriptor.get(mDescKey).get_spaltenBreite()	);
		}

		this.max_cols 	= Collections.max(vCol);
		this.max_breite	= Collections.max(vBreite);

		this._s(max_cols)._clear();

		int[] cols = new int[this.max_cols];
		if(max_breite != DEFAULT_BREITE && max_breite>0) {
			for(int i= 0 ; i<this.max_cols;i++){
				cols[i] = this.max_breite;
			}
		}else {
			for(int i= 0 ; i<this.max_cols;i++){
				cols[i] = DEFAULT_BREITE;
			}
		}
		this._setSize(cols);
	}


	private ArrayList<RB_Mask_Key[]> generate_key_matrix(int i_max_size, RB_Mask_Descriptor mask_descriptor) throws myException{

		ArrayList<RB_Mask_Key[]> key_grid = new ArrayList<RB_Mask_Key[]>();
		for(int row = 0; row<i_max_size;row++) {
			RB_Mask_Key[] mask_key_line = new RB_Mask_Key[this.max_cols];
			for (int col = 0; col<this.max_cols; col++) {
				mask_key_line[col] = null;
			}
			key_grid.add(mask_key_line);
		}
		for(RB_Mask_Key key: mask_descriptor.keySet()) {
			key_grid.get(key.get_row()-1)[key.get_column()-1] = key;
		}
		return key_grid;
	}


	private ArrayList<boolean[]> generate_mask_visibility_matrix(RB_Mask_Descriptor mask_descriptor) throws myException{

		ArrayList<boolean[]> rueck_grid = new ArrayList<>();

		for(int row = 0; row< mask_descriptor.get_max_row();row++) {
			boolean[] line =new boolean[this.max_cols];

			for (int col = 0; col<this.max_cols; col++) {
				if(col<mask_descriptor.get_mask_offset()) {
					line[col] = true;
				}else {
					line[col] = false;
				}
			}
			rueck_grid.add(line);
		}

		for(RB_Mask_Key key: mask_descriptor.keySet()) {
			RB_Cell cell = mask_descriptor.get(key);

			rueck_grid.get(key.get_row()-1)[key.get_column()-1] = true;

			int end_rowspan = (key.get_row()+cell.get_row_span())-1;
			int end_colspan = (key.get_column()+cell.get_column_span());

			for(int col_span=key.get_column(); col_span<end_colspan;col_span++) {
				rueck_grid.get(cell.get_row_coordinate()-1)[col_span-1] = true;
			}

			if(cell.get_row_span()>1) {
				for(int y=cell.get_row_coordinate()-1;y<(end_rowspan);y++) {
					for(int col_span=(key.get_column()-1); col_span<end_colspan-1;col_span++) {

						if(y<rueck_grid.size()) {
							rueck_grid.get(y)[col_span] = true;
						}else {
							rueck_grid.add(new boolean[this.max_cols]);
							rueck_grid.get(rueck_grid.size()-1)[col_span] = true;
						}
					}
				}
			}
		}

		/*for(int row = 0; row<rueck_grid.size();row++) {
			for(int col=0; col<max_cols; col++) {
				DEBUG.System_print(rueck_grid.get(row)[col]?"C":"-", DEBUG.get_Actual_DEBUG_FLAG());
			}
			DEBUG.System_print("\n", DEBUG.get_Actual_DEBUG_FLAG());
		}*/

		return rueck_grid;
	}


	private void build_grid(ArrayList<boolean[]> mask_visibility_matrix, ArrayList<RB_Mask_Key[]> mask_key_matrix , RB_Mask_Descriptor mask_descriptor) throws myException{
		//for test purpose
		if(bibALL.get_bDebugMode()){
			this._bo_ddd();
			for (int col = 0; col<this.max_cols; col++) {
				this._a(""+(col+1), new RB_gld()._col(new E2_ColorDDark())._center_mid());
			}
		}
		//grid filling
		for(int row = 0; row< mask_visibility_matrix.size();row++) {

			if(mask_descriptor.get_mask_offset()>0) {
				this._a(new RB_lab(), new RB_gld()._span(mask_descriptor.get_mask_offset()));
			}


			for (int col = mask_descriptor.get_mask_offset(); col<(mask_key_matrix.get(row).length); col++) {
				RB_Mask_Key key = mask_key_matrix.get(row)[col];

				if(key != null) {
					if(col == (key.get_column()-1)) {
						RB_Cell cell_desc = mask_descriptor.get(key);

						RB_gld gld = new RB_gld()
								._ins(cell_desc.get_insets())
								._span(cell_desc.get_column_span())
								._span_r(cell_desc.get_row_span())
								._al(cell_desc.getAlign());


						this._a(cell_desc.getRenderedContainer(), gld);	
					}
				}

				if(mask_visibility_matrix.get(row)[col]==false) {
					this._a("", new RB_gld()._span(1)._span_r(1)._center_mid());		
				}	
			}
			this.setRowHeight(row+1, new Extent(mask_descriptor.get_row_height()));
		}
	}


	public RB_Mask_Grid _render(VEK<Rec20> v_records) throws myException{

		this.init_grid();

		for(Rec20 rec : v_records) {
			RB_Mask_Descriptor mskDesc = hmMaskDescriptor.get(rec.get_tab().baseTableName());
			if(mskDesc == null) {
				mv._addAlarm("Die Tabelle " + rec.get_tab().baseTableName() + " hat keine angehängt Mask Definition.");
			}
		}

		for(Rec20 datei: v_records){
			RB_Mask_Descriptor mask_descriptor = hmMaskDescriptor.get(datei.get_tab().baseTableName());

			if(mask_descriptor != null) {

				ArrayList<boolean[]> 		mask_visibility_matrix 	= generate_mask_visibility_matrix(mask_descriptor);
				ArrayList<RB_Mask_Key[]> 	mask_key_matrix 		= generate_key_matrix(mask_visibility_matrix.size(),mask_descriptor);
				build_grid(mask_visibility_matrix,mask_key_matrix, mask_descriptor);

				fill_data(datei);
			}
		}
		return this;
	}

	private void fill_data(Rec20 data_record) throws myException {
		RB_Mask_Descriptor msk_desc = hmMaskDescriptor.get(data_record.get_tab().baseTableName());
		for(Entry<RB_Mask_Key, RB_Cell> cell_coor : msk_desc.entrySet()) {
			if(cell_coor.getValue().getField()!=null) {
				cell_coor.getValue().get_component().rb_set_db_value_manual(data_record.get_fs_dbVal(cell_coor.getValue().getField()));
			}
		}
	}
}

