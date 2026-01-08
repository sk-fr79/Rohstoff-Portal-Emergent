package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class ATOM_SALDO_LIST_popup_export extends EXP_popup_menue_exporter {

	public ATOM_SALDO_LIST_popup_export(E2_NavigationList onavigationList) {
		super(	onavigationList,  
				new MyE2_String("Lagerbestand-Auszug exportieren ins xls- oder csv-Format"),
				E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_SALDO,
				"EXPORT_LAGERBESTAND_XLS",
				"EXPORT_LAGERBESTAND_CSV");
	}
	
}
