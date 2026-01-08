package panter.gmbh.Echo2.ListAndMask.List.Export;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class EXP_addOnRecords {

	private	_TAB 		appendedTab = 			null;
	private	MyE2_String	infoText4FieldList = 	null; 
	private String      add_tab_preFix4Export = null;
	
	protected 	abstract MyRECORD  generate_instance_of_append_class(MyRECORD  base_record_of_list) throws myException;
	
	/**
	 * 
	 * @param p_appendedTab
	 * @param p_infoText4FieldList
	 * @param p_add_tab_preFix4Export
	 */
	public EXP_addOnRecords(_TAB p_appendedTab, MyE2_String p_infoText4FieldList, String p_add_tab_preFix4Export) {
		super();
		this.appendedTab = 			p_appendedTab;
		this.infoText4FieldList = 	p_infoText4FieldList;
		this.add_tab_preFix4Export = p_add_tab_preFix4Export;
	}

	public _TAB get_appendedTab() {
		return appendedTab;
	}

	public MyE2_String get_infoText4FieldList() {
		return infoText4FieldList;
	}

	public String get_add_tab_preFix4Export() {
		return add_tab_preFix4Export;
	}
	
	
	
	
}
