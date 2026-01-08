 
package panter.gmbh.Echo2.basic_tools.emailv2;
   
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_GridLabelForMask;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.indep.exceptions.myException;
  
public class EM2_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private EM2_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public EM2_MASK_ComponentMap(EM2_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
         
        this.registerComponent(EMAIL_SEND.betreff,    			new RB_TextArea()._width(400)._rows(2));
        this.registerComponent(EMAIL_SEND.betreff_2_send,    	new RB_TextArea()._width(400)._rows(2));
        this.registerComponent(EMAIL_SEND.id_email_send,    	new RB_lab());
        this.registerComponent(EMAIL_SEND.id_table,    			new RB_TextField()._width(100));
        this.registerComponent(EMAIL_SEND.sender_adress,    	new RB_TextField()._width(400));
        this.registerComponent(EMAIL_SEND.send_type,    		new RB_selField()._populate(SEND_TYPE.BCC,true)._width(400));
        this.registerComponent(EMAIL_SEND.table_base_name,    new RB_TextField()._width(100));
        this.registerComponent(EMAIL_SEND.text,    new RB_TextArea()._width(400)._rows(9));
        this.registerComponent(EMAIL_SEND.text_2_send,    new RB_TextArea()._width(400)._rows(9));
        this.registerComponent(EMAIL_SEND.email_type,    new RB_TextField()._width(400));
        this.registerComponent(EMAIL_SEND.email_verification_key,    new RB_TextField()._width(400));
        
        this.registerComponent(new EM2_MASK_DaughterAttachmentsList(m_tpHashMap));
        this.registerComponent(new EM2_MASK_DaughterTargetList(p_tpHashMap));
        
       //beschriftungskomponenten 
        this.registerComponent(EMAIL_SEND.betreff.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.BETREFF));
        this.registerComponent(EMAIL_SEND.betreff_2_send.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.BETREFF_2_SEND));
        this.registerComponent(EMAIL_SEND.id_email_send.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.ID_EMAIL_SEND));
        this.registerComponent(EMAIL_SEND.id_table.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.ID_TABLE));
        this.registerComponent(EMAIL_SEND.sender_adress.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.SENDER_ADRESS));
        this.registerComponent(EMAIL_SEND.send_type.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.SEND_TYPE));
        this.registerComponent(EMAIL_SEND.table_base_name.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.TABLE_BASE_NAME));
        this.registerComponent(EMAIL_SEND.text.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.TEXT));
        this.registerComponent(EMAIL_SEND.text_2_send.fk().getCpAdd("@EM2@"), new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.TEXT_2_SEND));
        this.registerComponent(EMAIL_SEND.email_type.fk().getCpAdd("@EM2@"),    new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.EMAIL_TYPE));;
        this.registerComponent(EMAIL_SEND.email_verification_key.fk().getCpAdd("@EM2@"),    new RB_GridLabelForMask(EM2_READABLE_FIELD_NAME.EMAIL_VERIFICATION_KEY));
        
        
//       //beispiel fuer einen setter-validator 
//       this.registerSetterValidators(EMAIL_SEND.id_xxx,new RB_Mask_Set_And_Valid() {
//			@Override
//			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
//					ExecINFO oExecInfo) throws myException {
//				return null;
//			}
//		});
 
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
    
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        }
        return null;
     }
	
}
 
 
