package panter.gmbh.Echo2.ListAndMask.List.Export;

import panter.gmbh.indep.dataTools.IF_Field;

public class EXP_Field{
	
	public IF_Field 			field = null;
	public EXP_addOnRecords 	addon = null;
	
//	private boolean       		is_right = false;
	
	public EXP_Field(IF_Field p_field, EXP_addOnRecords p_addon) {
		super();
		this.field = p_field;
		this.addon = p_addon;
	}
	
	public String get_save_key() {
		if (this.addon==null) {
			return this.field.tnfn();
		} else {
			return this.addon.get_add_tab_preFix4Export()+"."+this.field.fn();
		}
	}

//	@Override
//	public void set_right(boolean b_right) throws myException {
//		this.is_right=b_right;
//	}
//
//	@Override
//	public boolean is_right() throws myException {
//		return this.is_right;
//	}


}
