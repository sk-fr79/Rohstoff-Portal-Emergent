package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import panter.gmbh.indep.dataTools.IF_Field;

/**
 * extended field fuer die uebergabe eines anderen alias
 * @author martin
 *
 */
public class IF_Field_ext {
	
	public IF_Field field = null;
	public String 	alias = null;
	
	public IF_Field_ext(IF_Field p_field, String p_alias) {
		super();
		this.field = p_field;
		this.alias = p_alias;
	}
	
	public IF_Field_ext(IF_Field p_field) {
		super();
		this.field = p_field;
		this.alias = this.field.tn();
	}

	public String tnfn() {
		return this.alias+"."+field.fn();
	}
	
}
