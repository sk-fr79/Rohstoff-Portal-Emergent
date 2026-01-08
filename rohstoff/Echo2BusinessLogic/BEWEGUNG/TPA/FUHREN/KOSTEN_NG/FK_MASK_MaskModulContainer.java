
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG.FK_CONST.TRANSLATOR;



public class FK_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	private E2_NavigationList oNaviList;

	public FK_MASK_MaskModulContainer(E2_NavigationList c_naviList) {
		this.oNaviList = c_naviList;
	}

	public FK_MASK_MaskModulContainer(FK_MASK_bt_MULTI_FUHREN_KOSTEN oParentButton) throws myException {
		super();
		
		this.oNaviList = oParentButton.getNavigationList();
		
		FK_MASK_ComponentMapCollector compMapCollector = new FK_MASK_ComponentMapCollector();
		this.registerComponent(new RB_KM(_TAB.vpos_tpa_fuhre_kosten), compMapCollector);

		this.rb_INIT(TRANSLATOR.MASK.get_modul(), new FK_MASK_MaskGrid(compMapCollector, oParentButton ), true);

		this.add_CloseActions(new ownCloseAgent(this));

	}

	private class ownCloseAgent extends XX_ActionAgentWhenCloseWindow{

		public ownCloseAgent(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(bibMSG.get_bIsOK()){
				FK_MASK_MaskModulContainer.this.oNaviList.refresh_pageinfo_in_navigator(oNaviList.get_iActualPage());
				FK_MASK_MaskModulContainer.this.oNaviList.Mark_ID_IF_IN_Page(oNaviList.get_vSelectedIDs_Unformated());
				FK_MASK_MaskModulContainer.this.oNaviList.Refresh_ComponentMAP(oNaviList.get_vSelectedIDs_Unformated(), E2_ComponentMAP.STATUS_VIEW);

			}
		}

	}


}
