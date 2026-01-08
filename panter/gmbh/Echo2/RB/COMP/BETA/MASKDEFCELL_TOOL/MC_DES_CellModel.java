package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_CellModel {
	
	private String usertext;
	private String fieldname;
	
	private String idMaskDefCell;
	
	private int colspan;
	private int rowspan;
	private int start_col=1;
	private int start_row=0;
	
	private Rec20 record_zell = null;
	
	public MC_DES_CellModel(Rec20 record_mask_def_cell) throws myException {
		this.record_zell = record_mask_def_cell;
		
		int offset = this.record_zell.get_up_Rec20(MASK_DEF_CELL.id_mask_def, MASK_DEF.id_mask_def, false).get_myLong_dbVal(MASK_DEF.left_offset).get_iValue();
		
		this.usertext 		= record_mask_def_cell.get_ufs_dbVal(MASK_DEF_CELL.usertext);
		this.fieldname 		= record_mask_def_cell.get_ufs_dbVal(MASK_DEF_CELL.fieldname);
		
		this.idMaskDefCell	= record_mask_def_cell.get_ufs_dbVal(MASK_DEF_CELL.id_mask_def_cell);
		
		this.colspan 		= 	record_mask_def_cell.get_myLong_dbVal(MASK_DEF_CELL.colspan).get_iValue();
		this.rowspan 		= 	record_mask_def_cell.get_myLong_dbVal(MASK_DEF_CELL.rowspan).get_iValue();
		this.start_col 		=  	record_mask_def_cell.get_myLong_dbVal(MASK_DEF_CELL.start_col_in_mask).get_iValue();
		this.start_row 		= 	record_mask_def_cell.get_myLong_dbVal(MASK_DEF_CELL.start_row_in_mask).get_iValue();
	}

	public String get_usertext() {
		return usertext;
	}

	public String get_fieldname() {
		return fieldname;
	}

	public int get_colspan() {
		return colspan;
	}

	public int get_rowspan() {
		return rowspan;
	}

	public int get_start_col() {
		return start_col;
	}

	public int get_start_row() {
		return start_row;
	}

	public String get_id() {
		return idMaskDefCell;
	}
	
	public Rec20 get_zelle_record() throws myException{
		return record_zell;
	}
	
	public Rec20 get_mask_record() throws myException{
		return record_zell.get_up_Rec20(MASK_DEF_CELL.id_mask_def, MASK_DEF.id_mask_def,false); 
	}
}
