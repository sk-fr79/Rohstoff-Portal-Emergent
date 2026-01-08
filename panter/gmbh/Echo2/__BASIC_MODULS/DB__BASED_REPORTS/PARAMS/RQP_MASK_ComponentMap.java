 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
   
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ__PARAM_TYP;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.exceptions.myException;
  
public class RQP_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public RQP_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
         
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.id_reporting_query),    			new RB_TextAnzeige(100)._setDisableEverytime());
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.id_reporting_query_param),    	new RB_lab());
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.paramdefault),    				new RB_TextField()._width(400));
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.paramkey),    					new RB_TextField()._width(400));
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.paramname_4_user),    			new RB_TextField()._width(400));
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.typ),    							new SelectFieldType()._width(400));
       this.registerComponent(new RB_KF(REPORTING_QUERY_PARAM.paramdef),    					new RB_TextArea()._width(400)._height(200));
        
       this.registerSetterValidators(REPORTING_QUERY_PARAM.typ, new SetAndValidOpenFieldForDefinition());
       
        //       //beispiel fuer einen setter-validator 
//       this.registerSetterValidators(REPORTING_QUERY_FIELD.aktiv,new RB_Mask_Set_And_Valid() {
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
	
	private class SelectFieldType extends RB_selField {
		public SelectFieldType() throws myException {
			super();
			this._populate(RQ__PARAM_TYP.TEXT.get_dd_Array(true));
		}
	}
	
	private class SetAndValidOpenFieldForDefinition extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			RB_MaskController  c = new RB_MaskController(rbMASK);
			
			String 			liveValTyp = c.get_liveVal(REPORTING_QUERY_PARAM.typ);
			RQ__PARAM_TYP   typ = RQ__PARAM_TYP.DATE.getEnum(liveValTyp);	
			
			c.get_comp(REPORTING_QUERY_PARAM.paramdef).set_bEnabled_For_Edit(false);

			if (typ !=null && typ==RQ__PARAM_TYP.DROPDOWN) {
				c.get_comp(REPORTING_QUERY_PARAM.paramdef).set_bEnabled_For_Edit(true);
			}
			return new MyE2_MessageVector();
		}
		
	}
	
}
 
 
