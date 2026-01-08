package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen_Rec20;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST.TRI_BUTTONS;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;


public class AT_LIST_bt_New extends RB_bt_New {
    private E2_NavigationList  naviList = null;
    
    
    E2_Button bt_save_edit =     new E2_Button()._image(E2_ResourceIcon.get_RI("save_edit.png"), E2_ResourceIcon.get_RI("save_edit__.png"))
    											._ttt(new MyE2_String("Die Maske speichern und offenhalten"))
    											._aaa(new ownActionSaveAndReopen())
    											;

    
    public AT_LIST_bt_New(E2_NavigationList p_naviList) throws myException {
        super();
        this.naviList = p_naviList;
        this.get_grid4MaskExternal()._add(this.bt_save_edit,new RB_gld()._ins(10,2,2,2));
        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(TRI_BUTTONS.NEW.db_val()));
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new AT_MASK_MaskModulContainer();
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
        return new AT_MASK_DataObjectCollector();
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
            E2_NavigationList  f_naviList = AT_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(new RB_KM(_TAB.trigger_action_def)).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.trigger_action_def.n());
            
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
    
    private class ownActionSaveAndReopen extends RB_actionStandardSaveAndReopen_Rec20 {

		public ownActionSaveAndReopen() throws myException {
			super();
			this._set_leading_table_on_mask(_TAB.trigger_action_def)._set_mask_key_main(new RB_KM(_TAB.trigger_action_def));
		}

		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record, RB_ComponentMapCollector componentMapCollector_actual) throws myException {
			AT_LIST_bt_New.this.naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_record);

			return new AT_MASK_DataObjectCollector(id_record,MASK_STATUS.EDIT);
		}

		@Override
		public RB_ComponentMapCollector get_componentMapCollector() throws myException {
			return AT_LIST_bt_New.this.get_MaskContainer().rb_FirstAndOnlyComponentMapCollector();
		}

    }
    
}
 
