 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_Factory;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_CONST.REM_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;



public class REM_LIST_bt_New extends RB_bt_New {
    private E2_NavigationList  naviList = null;
    
	private REM__IF_getTableAndID list_container = null;

    
    public REM_LIST_bt_New(REM__IF_getTableAndID p_list_container, E2_NavigationList p_naviList) {
        super();
        this.naviList = p_naviList;
        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(REM_BUTTONS.NEW.db_val()));
		this.list_container = p_list_container;

    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new REM_MASK_MaskModulContainer(this.list_container);
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new REM_MASK_DataObjectCollector();
    }
    @Override
    public void define_Actions_4_saveButton(RB_bt_New btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshNavilist(maskPopup));
    }
    @Override
    public void define_Actions_4_CloseButton(RB_bt_New btNewInList,    RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)    throws myException {
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
            E2_NavigationList  f_naviList = REM_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(new REM__TableKey()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.reminder_def.n());

            // Nachrichten-Sofortverschickung ausführen
            REMINDER_Factory oRemFactory = new REMINDER_Factory();
            oRemFactory.doCheckAndSendReminderOnCreation(id_new);
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
    
}
 
