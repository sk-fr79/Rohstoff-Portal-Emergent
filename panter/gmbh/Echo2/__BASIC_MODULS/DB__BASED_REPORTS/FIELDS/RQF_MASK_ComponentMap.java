 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
   
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_CONST.RQ_NAMES;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_FIELD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
  
public class RQF_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public RQF_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
         
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.aktiv),    			new RB_cb());
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.alignment),    		new SelectLeftMidRight()._width(100));
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.breite_liste_px),    new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.fieldname),    		new SelectFieldNames()._width(400));
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.fieldname_4_user),   new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.id_reporting_query), new RB_TextAnzeige(100)._setDisableEverytime());
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.id_reporting_query_field),   new RB_lab());
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.is_searchfield),    	new RB_cb());
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.is_selectorfield),   new RB_cb());
        this.registerComponent(new RB_KF(REPORTING_QUERY_FIELD.sortierfolge),   	new RB_TextField()._width(100));
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
	
	
    
    private class SelectLeftMidRight extends RB_selField {

		public SelectLeftMidRight() throws myException {
			super();
			
			this._populate(RQF_LEFT_MID_RIGHT.LEFT.get_dd_Array(true));
		}
   	 
    }
    
    
    
    private class SelectFieldNames extends RB_selField {

		public SelectFieldNames() {
			super();

			this._aaa(new OwnAction());
			
			String[][] empty = {{"",""}};
			
			try {
				//nachsehen, welche reportId vorhanden ist
				Rec20 r = ((RB_Dataobject_V2)m_tpHashMap.getMotherTransportHashMap().getMaskComponentMapCollector().get(RQ_CONST.getLeadingMaskKey()).getRbDataObjectActual()).get_rec20();
				
				if (r.is_ExistingRecord()) {
					String[][] fieldNames = DB_META.getColumnsOra(r.getUfs(REPORTING_QUERY.realname_temptable));
					if (fieldNames.length>0) {
						fieldNames = bibARR.removeLinesContaining(fieldNames, RQ_NAMES.SESSIONFIELDNAME.db_val());
						fieldNames = bibARR.removeLinesContaining(fieldNames, RQ_NAMES.TIMESTAMPFIELDNAME.db_val());
						fieldNames = bibARR.removeLinesContaining(fieldNames, RQ_NAMES.USERIDFIELDNAME.db_val());
						
						if (fieldNames!=null) {
							this._populate(fieldNames);
						}
					}
				} else {
					this._populate(empty);
				}
			} catch (myException e) {
				e.printStackTrace();
				try {
					this._populate(empty);
				} catch (myException e1) {
				}
			}
		}
    	
		
		private class OwnAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_MaskController c = new RB_MaskController(RQF_MASK_ComponentMap.this);
				
				if (S.isEmpty(c.get_liveVal(REPORTING_QUERY_FIELD.fieldname_4_user))) {
					if (S.isFull(SelectFieldNames.this.rb_readValue_4_dataobject())) {
						c.set_maskVal(REPORTING_QUERY_FIELD.fieldname_4_user, SelectFieldNames.this.rb_readValue_4_dataobject(), bibMSG.MV());
					}
				}
				
			}
		}
		
    }
    
    
}
 
 
