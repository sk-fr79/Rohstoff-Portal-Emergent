package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class LAG_STAT_BT_LIST_EXCEL extends EXP_popup_menue_exporter {

	public LAG_STAT_BT_LIST_EXCEL(E2_NavigationList onavigationList)	{
		super(onavigationList, new MyE2_String("Lagerbewertung-Historie-Auszug"),E2_MODULNAMES.NAME_MODUL_LAGERSTATUS_LISTE,"EXPORT_LAGERSTATUS_XLS","EXPORT_LAGERSTATUS_CSV");
	}
	
}
