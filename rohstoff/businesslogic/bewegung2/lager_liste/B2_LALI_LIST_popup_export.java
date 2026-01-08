package rohstoff.businesslogic.bewegung2.lager_liste;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class B2_LALI_LIST_popup_export extends EXP_popup_menue_exporter {

	public B2_LALI_LIST_popup_export(E2_NavigationList onavigationlist) {
		super(	onavigationlist, 
				new MyE2_String("Lagerkonto-Auszug exportieren (momentane Auswahl)"),
				E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST,
				"EXPORT_LAGERKONTO_XLS",
				"EXPORT_LAGERKONTO_CSV");
	}

}
