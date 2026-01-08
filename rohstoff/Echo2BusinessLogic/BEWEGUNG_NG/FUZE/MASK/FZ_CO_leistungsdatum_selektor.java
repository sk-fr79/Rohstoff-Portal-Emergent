package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;


public abstract class FZ_CO_leistungsdatum_selektor extends E2_Grid {

	public abstract Component get_quelle_leistungsdatum_component() throws myException;
	
	public abstract Component get_ziel_leistungsdatum_component() throws myException;

	public abstract void quelle__do_mask_action() throws myException;

	public abstract void ziel__do_mask_action() throws myException;

	
	private RB_date_selektor quelle_date_selektor;
	private RB_date_selektor ziel_date_selektor;
	private RB_ComponentMapCollector comp_map_collector;
	
	public FZ_CO_leistungsdatum_selektor(RB_ComponentMapCollector compMapCollector) throws myException {
		
		super();
		
		this.set_component_map_collector(compMapCollector);
		
		this._setSize(54,96);
		
		this.quelle_date_selektor = (RB_date_selektor) get_quelle_leistungsdatum_component();
		this.quelle_date_selektor.get_oTextField().setHeight(new Extent(18));
		this.quelle_date_selektor.get_oTextField().setWidth(new Extent(80));

		this.ziel_date_selektor = (RB_date_selektor) get_ziel_leistungsdatum_component();
		this.ziel_date_selektor.get_oTextField().setHeight(new Extent(18));
		this.ziel_date_selektor.get_oTextField().setWidth(new Extent(80));
		
		this.setOrientation(ORIENTATION_HORIZONTAL);
		
		this._a(new RB_lab("Quelle")._i(), 	new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(0,0,0,2))
		._a(quelle_date_selektor, 			new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(2,0,0,0))
		._a(new RB_lab("Ziel")._i(), 		new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(0,4,0,2))
		._a(ziel_date_selektor,				new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(2,4,0,0));
		
		this.quelle_date_selektor.get_vActionAgentsZusatz().add(new ownActionFillOtherFields(true));
		
		this.ziel_date_selektor.get_vActionAgentsZusatz().add(new ownActionFillOtherFields(false));
		
		this.quelle_date_selektor.get_oTextField().focus_on();
		this.quelle_date_selektor.get_oButtonCalendar().focus_off();
	}
	
	
	public RB_ComponentMapCollector get_component_map_collector() {
		return comp_map_collector;
	}

	
	public void set_component_map_collector(RB_ComponentMapCollector comp_map_collector) {
		this.comp_map_collector = comp_map_collector;
	}

	
	/**
	 * zusatzagent, der die anderen datumsfelder mit dem gewählten wert fuellt
	 * @author martin
	 *
	 */
	private class ownActionFillOtherFields extends XX_ActionAgent {

		private boolean isQuelle = false;
		
		public ownActionFillOtherFields(boolean true_if_start) {
			super();
			
			isQuelle = true_if_start;
			
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(isQuelle){
				quelle__do_mask_action();
			}else{
				ziel__do_mask_action();
			}
		}
		
	}

	
	
}
