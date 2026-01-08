package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class _TAB_ext {
	private _TAB 	tab = 	null;
	private String 	alias = null;
	
	public _TAB_ext(_TAB p_tab, String p_alias) {
		super();
		this.tab = p_tab;
		this.alias = p_alias;
	}
	
	public _TAB_ext(_TAB p_tab) {
		super();
		this.tab = p_tab;
		this.alias = p_tab.fullTableName();
	}
	
	
	
	public IF_Field_ext[] get_fields() throws myException {
		IF_Field_ext[] f_rueck = new IF_Field_ext[this.tab.get_fields().length];
		
		int i_count=0;
		for (IF_Field f: this.tab.get_fields()) {
			f_rueck[i_count++]=new IF_Field_ext(f,this.alias);
		}
		
		return f_rueck;
	}
	
	
	
	public String getAddonKey() {
		 return this.alias+"."+this.tab.fullTableName()+"@";
	}
}
