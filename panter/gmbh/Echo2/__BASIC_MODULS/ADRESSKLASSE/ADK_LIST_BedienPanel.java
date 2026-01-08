package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class ADK_LIST_BedienPanel extends MyE2_Column {
	
	public ADK_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException {
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		Insets oInsets = new Insets(0,0,0,5);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new ADK_LIST_bt_view_edit(false,oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new ADK_LIST_bt_view_edit(true,oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new ADK_LIST_bt_New(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new ADK_LIST_bt_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);

		oRowForComponents.add(new ADK_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

}
