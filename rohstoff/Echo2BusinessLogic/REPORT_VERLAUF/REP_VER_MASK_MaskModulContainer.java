package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.REPORT_VERLAUF.REP_VER_CONST.REP_VER_NUM_CONST;

public class REP_VER_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
	
	private RB_TransportHashMap   params = null;

	private REP_VER_MASK_MaskGrid maskGrid = null;

	public REP_VER_MASK_MaskModulContainer(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		this.params = p_tpHashMap;

		//anfangsausmasse des fensterpopups
		this._setWidth(REP_VER_NUM_CONST.MASKPOPUP_WIDTH.getValue())._setHeight(REP_VER_NUM_CONST.MASKPOPUP_HEIGHT.getValue());

		this.params.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,this);

		REP_VER_MASK_ComponentMapCollector compMapCollector = new REP_VER_MASK_ComponentMapCollector(this.params) ; 
		this.registerComponent(_TAB.report_log.rb_km(), compMapCollector );

		this.rb_INIT(REP_VER_CONST.TRANSLATOR.MASK.get_modul(), this.maskGrid=new REP_VER_MASK_MaskGrid(this.params), true);

		this.set_oResizeHelper(new ownResizer());
	}


	private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			maskGrid.resize(ownContainer.get_oExtWidth().getValue(), ownContainer.get_oExtHeight().getValue());
		}

	}
}

