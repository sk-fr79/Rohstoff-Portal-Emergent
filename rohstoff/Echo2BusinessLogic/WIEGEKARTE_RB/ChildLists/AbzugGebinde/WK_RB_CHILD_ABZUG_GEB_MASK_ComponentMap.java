 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
   
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_GEBINDE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
         
        this.registerComponent(new RB_KF(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde),    new RB_selField()
        				._width(new Extent(400,Extent.PX))
        				._populate(new RecList21(_TAB.wiegekarte_gebinde)._fill("AKTIV = 'Y'", "SORTIERUNG ASC"),WIEGEKARTE_GEBINDE.kurzbez,_TAB.wiegekarte_gebinde.key(),true)
        				._aaa(new XX_ActionAgent() {
							@Override
							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
								// Beschreibung und Gewicht in die Felder übernehmen
								new WK_RB_CHILD_ABZUG_GEB_MaskController(WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMap.this, bibMSG.MV())
										.__loadGebindeData();
								
							}
						}));
        this.registerComponent(new RB_KF(WIEGEKARTE_ABZUG_GEB.gebinde),    new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(WIEGEKARTE_ABZUG_GEB.gewicht_einzel),    new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(WIEGEKARTE_ABZUG_GEB.menge),    new RB_TextField()._width(100));

        this.registerComponent(new RB_KF(WIEGEKARTE_ABZUG_GEB.id_wiegekarte),    new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb),    new RB_lab());

        this.registerComponent(WK_RB_CHILD_ABZUG_GEB_MASK_btSave.key, new WK_RB_CHILD_ABZUG_GEB_MASK_btSave(m_tpHashMap));
        
        
//       //beispiel fuer einen setter-validator 
       this.registerSetterValidators(WIEGEKARTE_ABZUG_GEB.menge,new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
					ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = bibMSG.newMV();

				if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION ) {
//					mv._add(new MyE2_Info_Message("lade.."));
				} 
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					// Spezielle Feldprüfung
					mv._add(new WK_RB_CHILD_ABZUG_GEB_MaskController(WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMap.this, bibMSG.MV()).__checkMenge());
					
					// Standard-Feldprüfung
					mv._add(WK_RB_CHILD_ABZUG_GEB_MASK_ComponentMap.this.rb_Mask_to_Dataobject_SIMULATE());
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
 
 
