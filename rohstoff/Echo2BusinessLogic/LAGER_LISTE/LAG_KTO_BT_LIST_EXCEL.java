package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class LAG_KTO_BT_LIST_EXCEL extends EXP_popup_menue_exporter {

	public LAG_KTO_BT_LIST_EXCEL(E2_NavigationList onavigationList)	{
		super(onavigationList,new MyE2_String("Lagerkonto-Auszug-Exporte"),E2_MODULNAMES.NAME_MODUL_LAGERLISTE_KONTO,"EXPORT_LAGERKONTO_XLS","EXPORT_LAGERKONTO_CSV");
		this.setToolTipText(new MyE2_String("Lagerkonto-Auszug-Exporte").CTrans());

	}
	
}
