package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class WK_LIST_BT_EXPORT_TO_EXCEL extends EXP_popup_menue_exporter{

	public WK_LIST_BT_EXPORT_TO_EXCEL(E2_NavigationList onavigationList)	{
		super(onavigationList, new MyE2_String("wiegekarten-Auszug"), E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE,"EXPORT_WIEGEKARTE_XLS","EXPORT_WIEGEKARTE_CSV");
	}
	
}
