 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
   
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_WaegungPos;
  
public class WK_RB_MASK_ComponentMap_Waegung extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
	
	private ENUM_WaegungPos 	  _waegung_pos = null; 

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public WK_RB_MASK_ComponentMap_Waegung(RB_TransportHashMap  p_tpHashMap, ENUM_WaegungPos waegung_pos  ) throws myException {
		super();

		_waegung_pos = waegung_pos;
		m_tpHashMap = p_tpHashMap;        

		this.registerComponent(new RB_KF(WAEGUNG.datum),    new RB_date_selektor(100,true));
		this.registerComponent(new RB_KF(WAEGUNG.gewicht),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.handeingabe),    new RB_cb());
		this.registerComponent(new RB_KF(WAEGUNG.handeingabe_bem),    new RB_TextField()._width(400));
		this.registerComponent(new RB_KF(WAEGUNG.id_user_waegung),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.id_waage_settings),    new RB_lab());
		this.registerComponent(new RB_KF(WAEGUNG.id_waegung),    new RB_lab());
		this.registerComponent(new RB_KF(WAEGUNG.id_wiegekarte),    new RB_lab());
		this.registerComponent(new RB_KF(WAEGUNG.storno),    new RB_cb());
		this.registerComponent(new RB_KF(WAEGUNG.sys_trigger_timestamp),    new RB_date_selektor(100,true));
		this.registerComponent(new RB_KF(WAEGUNG.sys_trigger_uuid),    new RB_TextField()._width(400));
		this.registerComponent(new RB_KF(WAEGUNG.waage_ds_ori),    new RB_TextField()._width(400));
		this.registerComponent(new RB_KF(WAEGUNG.waegung_pos),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_brutto_gewicht),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_datum),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_einheit),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_fehlercode),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_ident_nr),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_netto_gewicht),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_pruefziffer),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_status),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_taracode),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_taragewicht),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_terminal),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.w_waagen_nr),    new RB_cb());
		this.registerComponent(new RB_KF(WAEGUNG.w_waegebereich),    new RB_cb());
		this.registerComponent(new RB_KF(WAEGUNG.w_zeit),    new RB_TextField()._width(100));
		this.registerComponent(new RB_KF(WAEGUNG.zeit),    new RB_TextField()._width(100));
		//	       //beispiel fuer einen setter-validator 
		//	       this.registerSetterValidators(WAEGUNG.id_xxx,new RB_Mask_Set_And_Valid() {
		//				@Override
		//				public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
		//						ExecINFO oExecInfo) throws myException {
		//					return null;
		//				}
		//			});
 
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
 
 
