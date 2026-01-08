package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V3;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_Break4Popup_checkSperrFlag;

public class AH7_LIST_bt_New extends RB_bt_New_V3 {

	private AH7_MASK_MaskModulContainer  	ah7_mask_modulContainer = null;
    
    
    public AH7_LIST_bt_New(E2_NavigationList p_naviList) {
        super();
        this.set_NaviList(p_naviList);
        this.add_GlobalValidator(ENUM_VALIDATION.AH7_STEUERDATEI_NEW.getValidatorWithoutSupervisorPersilschein());
    }
    
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
    	this.ah7_mask_modulContainer=new AH7_MASK_MaskModulContainer();
        return this.ah7_mask_modulContainer;
    }
    
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new AH7_MASK_DataObjectCollector();
    }
    
    @Override
    public void define_Actions_4_saveButton(RB_bt_New_V3 btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshNavilist(maskPopup));
    }
    @Override
    public void define_Actions_4_CloseButton(RB_bt_New_V3 btNewInList,    RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)    throws myException {
        bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
    }
    
    private class ownActionRefreshNavilist extends XX_ActionAgent {
        private RB_ModuleContainerMASK maskPopup = null;
        
        public ownActionRefreshNavilist(RB_ModuleContainerMASK p_maskPopup) {
            super();
            this.maskPopup = p_maskPopup;
        }
        @Override
        public void executeAgentCode(ExecINFO oExecInfo) throws myException {
            E2_NavigationList  f_naviList = AH7_LIST_bt_New.this.get_NaviList();
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(new AH7_KEY()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.ah7_steuerdatei.n());
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
	@Override
	public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
		//return new Break4PopupSave();
		return new AH7_Break4Popup_checkSperrFlag(ah7_mask_modulContainer);
		
	}

	@Override
	public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
		//return new Break4PopupCancel();
		//return new AH7_Break4Popup_checkSomethingChangedWhenClose(ah7_mask_modulContainer);
		
		return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(
															  				this.ah7_mask_modulContainer.rb_ComponentMapCollector(new AH7_KEY())
															  				,null)
															);

		
	}

}
 
