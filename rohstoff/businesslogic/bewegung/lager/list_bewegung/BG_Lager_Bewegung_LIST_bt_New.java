 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V2;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_CONST.BG_Lager_Bewegung_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  

@Deprecated
public class BG_Lager_Bewegung_LIST_bt_New extends RB_bt_New_V2 {
  
    private E2_NavigationList  naviList = null;
    
    public BG_Lager_Bewegung_LIST_bt_New(E2_NavigationList p_naviList) {
        super();
        this.naviList = p_naviList;
        this.add_GlobalValidator(ENUM_VALIDATION.BG_LAGER_BEWEGUNG_NEW.getValidator());
        
      
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,BG_LADUNG("BG_LADUNG-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,BG_LADUNG_EDIT( 	ENUM_VALID_THEME.BG_LADUNG, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,BG_LADUNG_VIEW( 	ENUM_VALID_THEME.BG_LADUNG, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,BG_LADUNG_NEW( 		ENUM_VALID_THEME.BG_LADUNG, 	ENUM_VALID_BASICFUNCTION.NEW)
			,BG_LADUNG_DELETE( 	ENUM_VALID_THEME.BG_LADUNG, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
        
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new BG_Lager_Bewegung_MASK_MaskModulContainer();
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new BG_Lager_Bewegung_MASK_DataObjectCollector();
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
            E2_NavigationList  f_naviList = BG_Lager_Bewegung_LIST_bt_New.this.naviList;
            
            String id_new =""; 
//            		this.maskPopup.rb_ComponentMapCollector(_TAB.bg_ladung.rb_km()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.bg_ladung.n());
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
    
}
 
