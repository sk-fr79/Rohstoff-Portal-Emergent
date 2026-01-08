package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;

public class FZ_AA_NewWA extends XX_ActionAgent {

	private MyE2_Grid  			grid4Mask = 			new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskInternal = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskExternal = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	private E2_NavigationList   naviList =              null;
	
	public FZ_AA_NewWA(E2_NavigationList p_naviList) {
		super();
		this.naviList = p_naviList;

		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(2,2,2,2));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(10,2,2,2));
	}
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		FZ_AA_NewWA 						oThis = FZ_AA_NewWA.this;
		FZ_MASK_MaskModulContainer			mask = new FZ_MASK_MaskModulContainer(this.naviList);

		mask.add_new_WA();
		mask.rebuild_container_grid();
		
		mask.get_oRowForButtons().removeAll();
		mask.get_oRowForButtons().add(oThis.grid4Mask);
		oThis.grid4MaskInternal.removeAll();

		mask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Erfassen von Warenausgang"));	}

}
