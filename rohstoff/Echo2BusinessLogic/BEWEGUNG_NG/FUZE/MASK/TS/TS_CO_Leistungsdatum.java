package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_CO_leistungsdatum_selektor;

public class TS_CO_Leistungsdatum extends FZ_CO_leistungsdatum_selektor {

	public TS_CO_Leistungsdatum(TS_CM__Collector oCompMapCol) throws myException {
		super(oCompMapCol);
	}

	@Override
	public Component get_quelle_leistungsdatum_component() throws myException {
		TS_CM__Collector cmc = (TS_CM__Collector)this.get_component_map_collector();
		return cmc.get_atom_left().getComp(BEWEGUNG_ATOM.leistungsdatum);
	}

	@Override
	public Component get_ziel_leistungsdatum_component() throws myException {
		TS_CM__Collector cmc = (TS_CM__Collector)this.get_component_map_collector();
		return cmc.get_atom_right().getComp(BEWEGUNG_ATOM.leistungsdatum);
	}

	@Override
	public void quelle__do_mask_action() throws myException {
		new _TS_Controller_Leistungsdatum(this.get_component_map_collector()).fill_datum(bibMSG.MV());
	}

	@Override
	public void ziel__do_mask_action() throws myException {
		new _TS_Controller_Leistungsdatum(this.get_component_map_collector()).fill_datum(bibMSG.MV());	
	}
	

}
