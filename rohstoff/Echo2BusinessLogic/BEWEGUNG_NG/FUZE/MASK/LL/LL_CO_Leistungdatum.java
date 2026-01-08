package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_CO_leistungsdatum_selektor;

public class LL_CO_Leistungdatum extends FZ_CO_leistungsdatum_selektor {

	public LL_CO_Leistungdatum(RB_ComponentMapCollector compMapCollector) throws myException {
		super(compMapCollector);
	}

	@Override
	public Component get_quelle_leistungsdatum_component() throws myException {
		LL_CM__Collector cmc = (LL_CM__Collector) this.get_component_map_collector();
		return cmc.get_start_atom().getComp(BEWEGUNG_ATOM.leistungsdatum);
	}

	@Override
	public Component get_ziel_leistungsdatum_component() throws myException {
		LL_CM__Collector cmc = (LL_CM__Collector) this.get_component_map_collector();
		return cmc.get_ziel_atom().getComp(BEWEGUNG_ATOM.leistungsdatum);
	}

	@Override
	public void quelle__do_mask_action() throws myException {
		new _LL_Controller_Leistungsdatum(this.get_component_map_collector()).fill_datum(bibMSG.MV());
	}

	@Override
	public void ziel__do_mask_action() throws myException {
		new _LL_Controller_Leistungsdatum(this.get_component_map_collector()).fill_datum(bibMSG.MV());
	}

}
