 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4Mask_CheckWarnings4PopupAtSave;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V3;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_LIST_bt_New extends RB_bt_New_V3 {
  
    private E2_NavigationList  naviList = null;
	private String modulKenner;
	private RB_ModuleContainerMASK	ownMaskController = null;
   
    public SD_LIST_bt_New(E2_NavigationList p_naviList,String modulKenner) {
        super();
        this.naviList = p_naviList;
		this.modulKenner = modulKenner;
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return ownMaskController=new SD_MASK_MaskModulContainer(modulKenner);
    }
    
    
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new SD_MASK_DataObjectCollector();
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
            E2_NavigationList  f_naviList = SD_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(_TAB.searchdef.rb_km()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.searchdef.n());
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }

    @Override
 	public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
 		return new OwnBreakControllerSave();
 	}

 	@Override
 	public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
 		return new OwnBreakControllerCancel();
 	}
 	
 	
 	private class OwnBreakControllerSave extends E2_Break4PopupController {
 		public OwnBreakControllerSave() throws myException  {
 			super();
 			RB_ComponentMapCollector mapCol = ownMaskController.rb_FirstAndOnlyComponentMapCollector();
 			this._registerBreak(new Break4Mask_CheckWarnings4PopupAtSave(mapCol));
 		}
 	}

 	private class OwnBreakControllerCancel extends E2_Break4PopupController {
 		public OwnBreakControllerCancel() throws myException  {
 			super();
 			RB_ComponentMapCollector mapCol = ownMaskController.rb_FirstAndOnlyComponentMapCollector();
 			this._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(mapCol, null));
 		}
 	}
   
    
}
 
