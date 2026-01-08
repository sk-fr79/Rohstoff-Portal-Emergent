package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.MESSAGE_PROVIDER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MES_MASK_ComponentMap extends RB_ComponentMap {
    public MES_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(new RB_KF(MESSAGE_PROVIDER.id_message_provider),    new RB_lab());
        this.registerComponent(new RB_KF(MESSAGE_PROVIDER.id_user),   			 new MES_MASK_CIdUser());
        this.registerComponent(new RB_KF(MESSAGE_PROVIDER.messagekey),   			 new MES_MASK_CMessageKey()._width(400));
        this.registerComponent(new RB_KF(MESSAGE_PROVIDER.send_email),    		 new RB_cb());
        this.registerComponent(new RB_KF(MESSAGE_PROVIDER.send_message),    		 new RB_cb());
        
        this._setWidth(500,MESSAGE_PROVIDER.messagekey, MESSAGE_PROVIDER.id_user);
        
        this.registerSetterValidators(MESSAGE_PROVIDER.id_message_provider.fk(), new ownValidatorSomethingMustBeSent());
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
	
	
	private class ownValidatorSomethingMustBeSent extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				RB_MaskController c = new RB_MaskController(rbMASK);
				
				if (c.isNo_liveVal(MESSAGE_PROVIDER.send_message)) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Die Meldung ist zwingend nötig (eMail ist optional) !")));
				}
				
				
			}
			
			return mv;
		}
		
	}
	
}
 
