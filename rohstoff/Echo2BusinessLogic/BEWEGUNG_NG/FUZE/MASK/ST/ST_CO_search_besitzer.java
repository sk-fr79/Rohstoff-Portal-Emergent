package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.BasicInterfaces.IF_arrangeable;
import panter.gmbh.Echo2.RB.COMP.SearchField.IF_RB_ResultButton_can_edit_searched_record;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_search_MainADRESSE;

public class ST_CO_search_besitzer extends FZ_search_MainADRESSE {

	/**
	 * @throws myException
	 */
	public ST_CO_search_besitzer() throws myException {
		super();

		this.get_tf_search_input()._w(95);

		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		this.get_button_to_open_mask_to_referenced_record().focus_off();

		this.result_to_bottom();
		
//		this.ArrangeAdressSearchField.arange(this);

	}

//	private IF_arrangeable<RB_SearchField> ArrangeAdressSearchField = (sf) -> {
//		sf.get_tf_search_input()._w(120);
//
//		//auf eine linie ausrichten
//		RB_gld gl = new RB_gld()._ins(0,0,5,0)._left_top();
//		RB_gld gl2 = new RB_gld()._ins(0,0,2,0)._left_top();
//		sf._clear()
//		._a(sf.get_tf_search_input(), gl)
//		._a(sf.get_buttonStartSearch(), gl2)
//		._a(sf.get_buttonErase(), gl2)
//		._a(((IF_RB_ResultButton_can_edit_searched_record)sf).get_button_to_open_mask_to_referenced_record(), gl2)
//		._a(sf.get_gridContainer_to_show_searchResult(), gl._c()._span(4))
//		._setSize(125,20,20,20);
//		return sf;
//	};

}
