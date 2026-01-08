 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params;
   
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_SelFieldComboBoxV3;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN_PARAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
public class RVP_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	@SuppressWarnings("unchecked")
	public RVP_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
       HashMap<String,String> mapOfParams = new HashMap();
       HashMap<String,String> paramList = (HashMap<String,String>)((RB_TransportHashMap)this.m_tpHashMap.getMotherTransportHashMap()).getSB("PARAMETERLISTE");
       if (paramList!=null) {
    	   mapOfParams.putAll(paramList);
       }
       
       VEK<String>  params = new VEK<String>()._a(mapOfParams.keySet()); 
       params.sort((s1,s2)-> {
    	   return S.NN(s1).toUpperCase().compareTo(S.NN(s2).toUpperCase());
       });
       
       this.registerComponent(new RB_KF(REP_VARIANTEN_PARAM.id_rep_varianten_param),    new RB_lab());
       this.registerComponent(new RB_KF(REP_VARIANTEN_PARAM.param_name),    new RB_SelFieldComboBoxV3()._setSizes(300, 350, 300)._populate(params)._width(200));
       this.registerComponent(new RB_KF(REP_VARIANTEN_PARAM.param_value),    new RB_TextField()._width(200));
 
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
 
 
