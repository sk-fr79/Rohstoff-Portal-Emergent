package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_ALIGNEMENT;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_MASK_DEF_CELL extends Rec20 {
	
	public Rec20_MASK_DEF_CELL(_TAB p_tab) throws myException {
		super(p_tab);
	}
	
	
	public Rec20_MASK_DEF_CELL(Rec20 p_rec) throws myException{
		super(p_rec);
	}
	
	
	public int get_left_inset() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.left_insets).get_iValue();
	}

	
	public int get_top_inset() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.top_insets).get_iValue();
	}
	
	
	public int get_right_inset() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.right_insets).get_iValue();
	}
	
	
	public int get_bottom_inset() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.bottom_insets).get_iValue();
	}
	
	public Insets get_Insets()throws myException{
		return new Insets(get_left_inset(), get_top_inset(), get_right_inset(), get_bottom_inset());
	}
	
	public int get_column_span() throws myException{
		MyLong mask_def_default_value = this.get_up_Rec20(MASK_DEF_CELL.id_mask_def_cell,MASK_DEF.id_mask_def,false).get_myLong_dbVal(MASK_DEF.nb_of_cols);
		return this.get_myLong_dbVal(MASK_DEF_CELL.colspan, mask_def_default_value).get_iValue();
	}
	
	public int get_row_span() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.rowspan,new MyLong("1")).get_iValue();
	}
	
	public String get_usertext() throws myException{
		return this.get_fs_dbVal(MASK_DEF_CELL.usertext);
	}
	
	public String get_fieldname() throws myException{
		 return this.get_fs_dbVal(MASK_DEF_CELL.fieldname);
	}
	
	public int get_column_coordinate() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.start_col_in_mask).get_iValue();
	}
	
	public int get_row_coordinate() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.start_row_in_mask).get_iValue();
	}
	
	public int get_field_length() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.field_length,new MyLong(100)).get_iValue();
	}
	
	public int get_field_height() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.field_heigth,new MyLong(100)).get_iValue();
	}
	
	public String get_classname() throws myException{
		return this.get_ufs_dbVal(MASK_DEF_CELL.classname,"");
	}
	
	public boolean is_text_italic()throws myException{
		return this.is_yes_db_val(MASK_DEF_CELL.text_italic);
	}
	
	public boolean is_text_bold() throws myException{
		 return this.is_yes_db_val(MASK_DEF_CELL.text_bold);
	}
	
	public int get_text_size() throws myException{
		return this.get_myLong_dbVal(MASK_DEF_CELL.text_size, new MyLong(0)).get_iValue();
	}
	
	public Alignment get_alignment() throws myException{
		ENUM_ALIGNEMENT align = ENUM_ALIGNEMENT.valueOf(this.get_ufs_dbVal(MASK_DEF_CELL.alignment,ENUM_ALIGNEMENT.LEFT_TOP.db_val()));
		return align.get_alignment();
	}
	
	public Rec20_MASK_DEF get_mask_definition_record() throws myException {
		return new Rec20_MASK_DEF(this.get_up_Rec20(MASK_DEF_CELL.id_mask_def, MASK_DEF.id_mask_def, false));
	}
}
