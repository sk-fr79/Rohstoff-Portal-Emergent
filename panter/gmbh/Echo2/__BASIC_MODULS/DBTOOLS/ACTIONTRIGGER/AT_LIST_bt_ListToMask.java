 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen_Rec20;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST.TRI_BUTTONS;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class AT_LIST_bt_ListToMask extends RB_bt_List2Mask {

	private E2_NavigationList  naviList = null;

    E2_Button bt_save_edit =     new E2_Button()._image(E2_ResourceIcon.get_RI("save_edit.png"), E2_ResourceIcon.get_RI("save_edit__.png"))
									._ttt(new MyE2_String("Die Maske speichern und offenhalten"))
									._aaa(new ownActionSaveAndReopen())
									;

	
    public AT_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) throws myException {
        super(bEdit);
        
        this.naviList = p_naviList;
        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit?TRI_BUTTONS.EDIT.db_val():TRI_BUTTONS.VIEW.db_val()));
        
        if (bEdit) {
        	this.get_grid4MaskExternal()._add(this.bt_save_edit, new RB_gld()._ins(10, 2, 2, 2));
        }

        
    }
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new AT_MASK_MaskModulContainer();
    }
    
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        if (v_ids.size()>0) {
	        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
	        
	        for (String id: v_ids) {
	            collector.put(id, new AT_MASK_DataObjectCollector(id,aktuellerStatus));
	        }
        } else {
        	mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben keine Trigger-Einträge ausgewählt !")));
        }
        return collector;
    }
    
    
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten von Triggern":"Anzeige von Triggern");
    }
    
    @Override
    public MyE2_String generate_Meldung4SaveRecord(    RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String("Trigger wurde gespeichert");
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
    	Vector<XX_ActionAgentWhenCloseWindow> ret = new Vector<>();
    	ret.add(new actionRefreshList(rb_ModulContainerMask));
        return ret;
    }
    
    private class actionRefreshList extends XX_ActionAgentWhenCloseWindow {
		public actionRefreshList(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String> ids = AT_LIST_bt_ListToMask.this.naviList.get_vSelectedIDs_Unformated();
			AT_LIST_bt_ListToMask.this.naviList.Refresh_ComponentMAP(ids, E2_ComponentMAP.STATUS_VIEW);
		}
    }
    
    
    
    private class ownActionSaveAndReopen extends RB_actionStandardSaveAndReopen_Rec20 {

 		public ownActionSaveAndReopen() throws myException {
 			super();
 			this._set_leading_table_on_mask(_TAB.trigger_action_def)._set_mask_key_main(new RB_KM(_TAB.trigger_action_def));
 		}

 		@Override
 		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record, RB_ComponentMapCollector componentMapCollector_actual) throws myException {
 			return new AT_MASK_DataObjectCollector(id_record,MASK_STATUS.EDIT);
 		}

 		@Override
 		public RB_ComponentMapCollector get_componentMapCollector() throws myException {
 			AT_LIST_bt_ListToMask oThis = AT_LIST_bt_ListToMask.this;
 			
 			return oThis.rb_modulContainerMASK().rb_ComponentMapCollector(new RB_KM(_TAB.trigger_action_def));
 		}
     	
     }

}
 
