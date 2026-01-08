
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class BOR_LIST_BedienPanel extends MyE2_Column {

	private BOR_LIST_Selector bordercrossing_LIST_Selector = null;

	public BOR_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException {

		super(MyE2_Column.STYLE_NO_BORDER());

		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());

		this.bordercrossing_LIST_Selector = new BOR_LIST_Selector(oNaviList);

		Insets oInsets = new Insets(0, 0, 0, 5);

//		this.add(bordercrossing_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);

		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), new Insets(2, 2, 20, 2));
		oRowForComponents.add(new BOR_LIST_bt_ListToMask(true, oNaviList), new Insets(2, 2, 5, 2));
		oRowForComponents.add(new BOR_LIST_bt_ListToMask(false, oNaviList), new Insets(2, 2, 5, 2));
		oRowForComponents.add(new BOR_LIST_bt_New(oNaviList),  new Insets(2, 2, 5, 2));
		oRowForComponents.add(new BOR_LIST_bt_multiDelete(oNaviList), new Insets(2, 2, 5, 2));

		oRowForComponents.add(new BOR_LIST_DATASEARCH(oNaviList), new Insets(20, 2, 2, 2));
	}

	public BOR_LIST_Selector get_list_Selector() {

		return bordercrossing_LIST_Selector;
	}
}
