package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD__NaviList;

public class WF_HEAD_LIST__Selectioncontainer extends E2_ListSelectorContainer {
	 
	
	private WF_SelectorSwitcher             selSelektor = null;
	private E2_Grid    						grid_innerComponent = new E2_Grid()._setSize(1400,150);
	
	//dieses grid wird mit dem jeweilgen umgeschalteten selektor gefuellt 
	private E2_Grid    						grid_4_selektorSwitch = new E2_Grid();
	
	public WF_HEAD_LIST__Selectioncontainer(WF_HEAD__NaviList oNavList, String cMODULE_KENNER) throws myException
	{
		super();
		
		//diese selectfield-komponent uebernimmt die komplette umschaltung
		this.selSelektor= new WF_SelectorSwitcher(this, oNavList, cMODULE_KENNER);
		
		E2_Grid g_selectorUmschalter = new E2_Grid()._setSize(120)
												._a(new RB_lab()._tr("Selektor-Varianten")._fsa(-2))
												._a(this.selSelektor);

		
		this.set_TO_InnerComponent(this.grid_innerComponent, E2_INSETS.I(2,2,2,2));
		this.grid_innerComponent._a(this.grid_4_selektorSwitch);
		this.grid_innerComponent._a(g_selectorUmschalter, new RB_gld()._ins(E2_INSETS.I(10,0,0,0)));
		
		this.selSelektor.set_active_selektor();

	}

	
	public E2_Grid get_grid_4_selektorSwitch() {
		return this.grid_4_selektorSwitch;
	}

	
}
