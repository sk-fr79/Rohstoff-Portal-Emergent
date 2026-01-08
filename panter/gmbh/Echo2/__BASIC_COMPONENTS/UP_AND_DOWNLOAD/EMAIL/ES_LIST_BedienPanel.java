package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class ES_LIST_BedienPanel extends MyE2_Column {
	
	public ES_LIST_BedienPanel(ES_LIST_BasicModuleContainer listContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		Insets oInsets = new Insets(0,0,0,5);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(listContainer.get_naviList()),  new Insets(2,2,20,2));
		oRowForComponents.add(new ES_LIST_BT_VIEW(listContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new ES_LIST_BT_EDIT(listContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new ES_LIST_BT_DELETE(listContainer.get_naviList()), E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new ES_LIST_DATASEARCH(listContainer.get_naviList()), new Insets(20,2,2,2));
	}

}
