 
package rohstoff.Echo2BusinessLogic._TaxOld.Changes;
   
import java.util.Date;
import java.util.Vector;

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
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
  
public class TOC_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public TOC_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
    
       	this.m_tpHashMap = p_tpHashMap;        
         
		this.registerComponent(new RB_KF(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung),    new RB_TextArea()._width(400)._rows(5));
		this.registerComponent(new RB_KF(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis),    new RB_date_selektor(100,true));
		this.registerComponent(new RB_KF(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von),    new RB_date_selektor(100,true));
		this.registerComponent(new RB_KF(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen),    new RB_lab());
		this.registerComponent(new RB_KF(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz),    new RB_TextField()._width(100));

 
		this.registerSetterValidators(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen, (mask,typ)-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			if (typ==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				RB_MaskController  cont = new RB_MaskController(mask);
				Date dateVon = (Date)cont.getRawLiveVal(_TAB.mwstschluessel_aenderungen.rb_km(), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fk());
				Date dateBis = (Date)cont.getRawLiveVal(_TAB.mwstschluessel_aenderungen.rb_km(), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis.fk());
				
				if (O.isNoOneNull(dateVon,dateBis)) {
					if (dateBis.before(dateVon)) {
						mv._addAlarm(S.ms("Das Enddatum liegt vor dem Startdatum !"));
					}
				}
			}
			
			return mv;
			
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
 
 
