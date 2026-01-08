package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class BGL_LIST_bt_BelegGrenzUbertritt extends MyE2_Button {

	private E2_NavigationList naviList = null;

	private String module_text = "Beleg inner-europäischer Grenzübertritt";
	
	public BGL_LIST_bt_BelegGrenzUbertritt(E2_NavigationList  p_naviList) throws myException {
		super();

		this.naviList = p_naviList;

		
		this.add_GlobalValidator(new BGL_LIST_Selection_validator(naviList));
		this.setText(module_text);
		this.setToolTipText(module_text);
		this._aaa(()-> call_popup());
	}

	private void call_popup() throws myException {
		BGL_LIST_popup_BelegGrenzUbertritt pop = new BGL_LIST_popup_BelegGrenzUbertritt(naviList);
		pop.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(750), S.ms(module_text));
	}
}
