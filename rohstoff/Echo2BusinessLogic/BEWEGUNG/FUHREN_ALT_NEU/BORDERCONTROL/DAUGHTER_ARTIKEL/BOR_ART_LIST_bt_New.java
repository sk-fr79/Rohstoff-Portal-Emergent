 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
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
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
  
public class BOR_ART_LIST_bt_New extends RB_bt_New_V3 {
  
    private E2_NavigationList  naviList = null;
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    public BOR_ART_LIST_bt_New(E2_NavigationList p_naviList ,PARAMHASH  p_params) {
        super();
        this.naviList = p_naviList;
        this.add_GlobalValidator(ENUM_VALIDATION.BORDERCROSSING_ARTIKEL_NEW.getValidator());
        this.params = p_params;        
      
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,BORDERCROSSING_ARTIKEL("BORDERCROSSING_ARTIKEL-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,BORDERCROSSING_ARTIKEL_EDIT( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,BORDERCROSSING_ARTIKEL_VIEW( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,BORDERCROSSING_ARTIKEL_NEW( 		ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 	ENUM_VALID_BASICFUNCTION.NEW)
			,BORDERCROSSING_ARTIKEL_DELETE( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
        
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new BOR_ART_MASK_MaskModulContainer(this.params);
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new BOR_ART_MASK_DataObjectCollector(this.params);
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
            E2_NavigationList  f_naviList = BOR_ART_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(_TAB.bordercrossing_artikel.rb_km()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.bordercrossing_artikel.n());
            
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
        return null;
    }
    
}
 
