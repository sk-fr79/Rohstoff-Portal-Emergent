package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;


import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.indep.exceptions.myException;


public class AT_MASK_ComponentMap extends RB_ComponentMap {
    public AT_MASK_ComponentMap() throws myException {
        super();

        AT_MASK_selectField  selectField = new AT_MASK_selectField();
        AT_MASK_selectTable selectTable = new AT_MASK_selectTable(selectField);  
    
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.id_trigger_action_def),   new RB_lab());
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.trigger_name),    		new AT_MASK_triggername(400,20));
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.validation_class), 		new AT_MASK_selectTriggerValidator()._setTextWidth(400));
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.execution_class), 		new AT_MASK_selectTriggerExecuter()._setTextWidth(400));
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.table_basename),    		selectTable._setTextWidth(400)   );
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.table_id),    			new RB_TextField(100));
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.field_name),   			selectField._setTextWidth(400));
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.execution_code),    		new RB_TextArea(600,10));
        this.registerComponent(new RB_KF(TRIGGER_ACTION_DEF.execution_valid),    		new RB_TextArea(600,10));
        this.registerComponent(AT_CONST.key_InfoButtonValidation(), 					new AT_MASK_InfoButtonValidation());
        this.registerComponent(AT_CONST.key_InfoButtonExecution(), 					new AT_MASK_InfoButtonExecution());
    }
    
    
    
    
    
    
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
        return null;
    }
    
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
        return null;
    }

    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		return null;
	}
	
	
	
}
 
