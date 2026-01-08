package panter.gmbh.Echo2.RB.COMP.SearchField;

import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public interface IF_RB_ResultButton_can_edit_searched_record {
	
	public E2_Button  get_button_to_open_mask_to_referenced_record() throws myException;
	
}
