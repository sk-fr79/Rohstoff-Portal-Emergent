 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
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
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
  
public class TAXG_LIST_bt_New extends RB_bt_New_V3 {
  
    private E2_NavigationList  naviList = null;
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    private TAXG_MASK_MaskModulContainer  ownMaskContainer = null;
    
    public TAXG_LIST_bt_New(E2_NavigationList p_naviList ,PARAMHASH  p_params) {
        super();
        this.naviList = p_naviList;
        this.add_GlobalValidator(ENUM_VALIDATION.TAX_GROUP_NEW.getValidator());
        this.params = p_params;        
      
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,TAX_GROUP("TAX_GROUP-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,TAX_GROUP_EDIT( 	ENUM_VALID_THEME.TAX_GROUP, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,TAX_GROUP_VIEW( 	ENUM_VALID_THEME.TAX_GROUP, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,TAX_GROUP_NEW( 		ENUM_VALID_THEME.TAX_GROUP, 	ENUM_VALID_BASICFUNCTION.NEW)
			,TAX_GROUP_DELETE( 	ENUM_VALID_THEME.TAX_GROUP, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
        
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return this.ownMaskContainer = new TAXG_MASK_MaskModulContainer(this.params);
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new TAXG_MASK_DataObjectCollector(this.params);
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
            E2_NavigationList  f_naviList = TAXG_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(_TAB.tax_group.rb_km()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.tax_group.n());
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
	
	
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(this.ownMaskContainer.rb_FirstAndOnlyComponentMapCollector(), null));
    }
    
}
 
