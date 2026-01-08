package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;

public interface IF_konstanten {

	
	public 			String 			db_val();
	public 			String 			base_text();
	public          boolean         isTrans();
	
	/**
	 * 
	 * @return  MyE2_String translated
	 */
	public default  MyE2_String  	my_str() {
		return new MyE2_String(this.base_text(), this.isTrans());
	}
	
	/**
	 * 
	 * @return  MyE2_String translated
	 */
	public default  RB_lab  	lab() {
		if (this.isTrans()) {
			return new RB_lab()._tr(this.base_text());
		} else {
			return new RB_lab()._t(this.base_text());
		}
	}



}
