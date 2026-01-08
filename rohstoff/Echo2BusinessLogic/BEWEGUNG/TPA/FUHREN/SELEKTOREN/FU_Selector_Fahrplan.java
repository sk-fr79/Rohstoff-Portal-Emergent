package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selector_Fahrplan extends XX_ListSelektor {

	private FU_Selector_Fahrplan_FP_Button  bt_FP = new FU_Selector_Fahrplan_FP_Button();
	private FU_Selector_Fahrplan_GRID  		grid_FP = null;
	
	
	public FU_Selector_Fahrplan() throws myException {
		super();
		this.grid_FP =  new FU_Selector_Fahrplan_GRID(this.bt_FP);
		this.set_oNeutralisator(new ownNeutralisator());
		//dem button noch das grid mitgeben
		this.bt_FP.set_grid_FP_Selector_Anzeige(this.grid_FP);
	}

	@Override
	public String get_WhereBlock() throws myException {
		return this.bt_FP.get_cWhereStatement();
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.grid_FP;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.grid_FP;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.bt_FP.set_oAction4Selection(oAgent);
		this.grid_FP.get_BT_Clean().add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {
		
	}

	
	private class ownNeutralisator extends XX_ListSelektor_Neutralisator {

		@Override
		public void set_to_Neutral() throws myException {
			FU_Selector_Fahrplan.this.bt_FP.ClearSelection();
		}
		
	}
	
}
