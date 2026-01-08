package rohstoff.Echo2BusinessLogic.LAGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;

public class LAG_BT_LIST_EXCEL extends EXP_popup_menue_exporter {

	public LAG_BT_LIST_EXCEL(E2_NavigationList onavigationList)
	{
		super(onavigationList);
		this.setToolTipText(new MyE2_String("Lagerbestand-Auszug-Exporte").CTrans());
	}
	
}
