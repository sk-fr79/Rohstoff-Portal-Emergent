 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
   
import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_MGE_ABZ_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public WK_RB_CHILD_MGE_ABZ_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
         
       this.registerComponent(new RB_KF(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz),    new RB_lab());
       this.registerComponent(new RB_KF(WIEGEKARTE_MGE_ABZ.id_wiegekarte),    new RB_lab());
       this.registerComponent(new RB_KF(WIEGEKARTE_MGE_ABZ.id_abzugsgrund),    new WK_RB_CHILD_MGE_ABZ_SelAbzuege()._width(400));
       this.registerComponent(new RB_KF(WIEGEKARTE_MGE_ABZ.abzug_menge),    new RB_TextField()._width(50));
       this.registerComponent(new RB_KF(WIEGEKARTE_MGE_ABZ.abzug_prozent),    new RB_TextField()._width(50));
       this.registerComponent(new RB_KF(WIEGEKARTE_MGE_ABZ.langtext),    new RB_TextArea()._width(400)._rows(4)._fsa(0));

       
       // Validierer 
       this.registerSetterValidators(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz,new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
					ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = bibMSG.newMV();
				
				
				if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
					
					RB_MaskController mc = new RB_MaskController(WK_RB_CHILD_MGE_ABZ_MASK_ComponentMap.this) ;
					BigDecimal bdMenge = mc.getBigDecimalLiveVal(WK_RB_CHILD_MGE_ABZ_CONST.getLeadingMaskKey(), WIEGEKARTE_MGE_ABZ.abzug_menge.fk());
					BigDecimal bdProzent = mc.getBigDecimalLiveVal(WK_RB_CHILD_MGE_ABZ_CONST.getLeadingMaskKey(), WIEGEKARTE_MGE_ABZ.abzug_prozent.fk());
					
					// Prüfung, ob alle Felder DB-Konform sind
					mv._add(WK_RB_CHILD_MGE_ABZ_MASK_ComponentMap.this.rb_Mask_to_Dataobject_SIMULATE());
					
					// Spezielle Prüfung
					if (bdMenge == null && bdProzent == null) {
						mv._addAlarm("Es muss genau ein Wert gesetzt sein!");
					} else if (bdMenge != null && bdProzent != null) {
						mv._addAlarm("Es darf nur genau ein Wert gesetzt sein!");
					}
				}
				
				return mv;
			}
		});
 
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
 
 
