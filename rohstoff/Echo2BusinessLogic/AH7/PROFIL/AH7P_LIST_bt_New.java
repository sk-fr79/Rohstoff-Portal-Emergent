package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V2;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_CONST.AH7P_BUTTONS;

public class AH7P_LIST_bt_New extends RB_bt_New_V2 {
    private E2_NavigationList  naviList = null;
    
    public AH7P_LIST_bt_New(E2_NavigationList p_naviList) {
        super();
        this.naviList = p_naviList;
        this.add_GlobalValidator(ENUM_VALIDATION.AH7_PROFIL_NEW.getValidatorWithoutSupervisorPersilschein());
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new AH7P_MASK_MaskModulContainer();
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new AH7P_MASK_DataObjectCollector();
    }
    @Override
    public void define_Actions_4_saveButton(RB_bt_New_V2 btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshNavilist(maskPopup));
    }
    @Override
    public void define_Actions_4_CloseButton(RB_bt_New_V2 btNewInList,    RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)    throws myException {
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
            E2_NavigationList  f_naviList = AH7P_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(new AH7P_KEY()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.ah7_profil.n());
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
    
}
 
