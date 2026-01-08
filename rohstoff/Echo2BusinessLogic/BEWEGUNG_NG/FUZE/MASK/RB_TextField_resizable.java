package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;

public class RB_TextField_resizable extends RB_TextField {

	private FZ_TEXT_SIZE e_size = null;
	
	public RB_TextField_resizable(int i_width) throws myException {
		super();
		
		this.e_size = get_selected_size();
		
		this._w(i_width);
		
		this._size_and_font(e_size.getSize()+2, e_size.get_font());
	}
	
	private static FZ_TEXT_SIZE get_selected_size() throws myException{
		return ENUM_MANDANT_DECISION.FIELDSIZE_4_NEW_BEWEGUNGSMODEL.is_YES()? FZ_TEXT_SIZE.SMALL:FZ_TEXT_SIZE.NORMAL;
	}
	
}
