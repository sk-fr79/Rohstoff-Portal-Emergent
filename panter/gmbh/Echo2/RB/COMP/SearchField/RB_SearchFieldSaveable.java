package panter.gmbh.Echo2.RB.COMP.SearchField;

import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.indep.exceptions.myException;


public abstract class RB_SearchFieldSaveable extends RB_SearchField implements IF_RB_Component_Savable {

	public RB_SearchFieldSaveable() throws myException {
		super();
	}

	public RB_SearchFieldSaveable(boolean bShowEraser) throws myException {
		super(bShowEraser);
	}

	

	
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		if (this.is_search_done_correct()) {
			return this.get_tf_search_input().getText();
		} else {
			return "";
		}
	}
	

	
}
