package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_CO_leistungsdatum_selektor;

public class LG_CO_Leistungsdatum extends FZ_CO_leistungsdatum_selektor {

	public LG_CO_Leistungsdatum(LG_CM__Collector oCompMapCol) throws myException {
		super(oCompMapCol);
	}

	@Override
	public Component get_quelle_leistungsdatum_component() throws myException {
		LG_CM__Collector cmc = (LG_CM__Collector)this.get_component_map_collector();
		return cmc.get_start_Atom().getComp(BEWEGUNG_ATOM.leistungsdatum);
	}

	@Override
	public Component get_ziel_leistungsdatum_component() throws myException {
		LG_CM__Collector cmc = (LG_CM__Collector)this.get_component_map_collector();
		return cmc.get_ziel_Atom().getComp(BEWEGUNG_ATOM.leistungsdatum);
	}

	@Override
	public void quelle__do_mask_action() throws myException {
		new _LG_Controller_Leistungsdatum(this.get_component_map_collector()).fill_datum(bibMSG.MV());
		
//		new _LG_MASK_ControllerMap(LG_CO_Leistungsdatum.this.get_component_map_collector())._fill_lade_datum(bibMSG.MV());		
	}

	@Override
	public void ziel__do_mask_action() throws myException {
		new _LG_Controller_Leistungsdatum(this.get_component_map_collector()).fill_datum(bibMSG.MV());	

	}


}
