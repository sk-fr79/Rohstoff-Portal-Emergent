package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_MASK_DEF  extends Rec20{


	public Rec20_MASK_DEF(_TAB p_tab) throws myException {
		super(p_tab);
	}
	
	public Rec20_MASK_DEF(Rec20 p_rec) throws myException{
		super(p_rec);
	}

	public int get_pixel_width() throws myException{
		return this.get_myLong_dbVal(MASK_DEF.pixel_width).get_iValue();
	}

	public int get_number_of_cols() throws myException{
		return this.get_myLong_dbVal(MASK_DEF.nb_of_cols).get_iValue();
	}
	
	public String get_Tablename() throws myException{
		return this.get_fs_dbVal(MASK_DEF.tablename);
	}
	
	public int get_mask_offset() throws myException{
		return this.get_myLong_dbVal(MASK_DEF.left_offset).get_iValue();
	}

	public RecList20 get_cell_list() throws myException{
		return this.get_down_reclist20(MASK_DEF_CELL.id_mask_def, 
				"", 
				MASK_DEF_CELL.start_row_in_mask.fn()+","+MASK_DEF_CELL.start_col_in_mask.fn());
	}
	
	public String get_maskname() throws myException{
		return this.get_fs_dbVal(MASK_DEF.maskname,"");
	}
	
	public String get_long_maskname() throws myException{
		return this.get_fs_dbVal(MASK_DEF.maskname_long);
	}
	
	public RecList20 get_cell_list_by_row(int p_row_number) throws myException{
		return this.get_down_reclist20(
				MASK_DEF_CELL.id_mask_def, 
				MASK_DEF_CELL.start_row_in_mask.fn()+"="+p_row_number, 
				MASK_DEF_CELL.start_row_in_mask.fn()+","+MASK_DEF_CELL.start_col_in_mask.fn()
				);
	}
	
	public int get_max_row_from_list()throws myException{
		 String erg =  bibDB.EinzelAbfrage(
				new SEL("MAX("+MASK_DEF_CELL.start_row_in_mask.fn()+")")
				.FROM(_TAB.mask_def_cell)
				.WHERE(new vgl(MASK_DEF_CELL.id_mask_def, this.get_key_value())).s());
		if(!erg.equals("null") && S.isFull(erg)) {
			return bibALL.String2Integer(erg);
		}
		
		return 0;
	}
	
	public int count_used_rowspan(int p_row_number) throws myException{
		String query = new SEL("Count(" +MASK_DEF_CELL.colspan.fn()+ ")").FROM(_TAB.mask_def_cell).WHERE(new vgl(MASK_DEF_CELL.id_mask_def,this.get_fs_dbVal(MASK_DEF.id_mask_def))).AND(new vgl(MASK_DEF_CELL.start_row_in_mask, ""+p_row_number)).s();
		return bibALL.String2Integer(bibDB.EinzelAbfrage(query));
	}
	
}
