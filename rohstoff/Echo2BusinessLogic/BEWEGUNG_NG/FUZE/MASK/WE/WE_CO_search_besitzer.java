package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_search_MainADRESSE;

public class WE_CO_search_besitzer extends FZ_search_MainADRESSE {

	/**
	 * @throws myException
	 */
	public WE_CO_search_besitzer() throws myException {
		super();

		this.get_tf_search_input().focus_on();

//		//auf eine linie ausrichten
		this.get_tf_search_input()._w(95);
		RB_gld gl = new RB_gld()._ins(0,0,5,0)._left_top();
		RB_gld gl2 = new RB_gld()._ins(0,0,2,0)._left_top();
		this._clear()
		._a(get_tf_search_input(), gl)
		._a(this.get_buttonStartSearch(), gl2)
		._a(this.get_buttonErase(), gl2)
		._a(this.get_button_to_open_mask_to_referenced_record(), gl2)
		._a(this.get_gridContainer_to_show_searchResult(), gl._c()._span(3))
		._setSize(64,20,20,20,190);

//		FZ_MaskFormatter.ArrangeAdressSearchField.arange(this);

		
	}

}
