package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;

public class RB_lab_resizable extends RB_lab {

	private FZ_TEXT_SIZE e_size = FZ_TEXT_SIZE.NORMAL;
	
	public RB_lab_resizable() throws myException {
		super();
		this.e_size = get_selected_size();
		this._f(e_size.get_font());
	}

	public RB_lab_resizable(String text) throws myException {
		super(text);
		this.e_size = get_selected_size();
		this._f(e_size.get_font());
	}

	public RB_lab_resizable(MyE2_String text) throws myException {
		super(text);
		this.e_size = get_selected_size();
		this._f(e_size.get_font());
	}

	public RB_lab_resizable(ResourceImageReference icon) throws myException {
		super(icon);
		this.e_size = get_selected_size();
		this._f(e_size.get_font());
	}

	private static FZ_TEXT_SIZE get_selected_size() throws myException{
		return ENUM_MANDANT_DECISION.FIELDSIZE_4_NEW_BEWEGUNGSMODEL.is_YES()? FZ_TEXT_SIZE.SMALL:FZ_TEXT_SIZE.NORMAL;
	}
	
}
