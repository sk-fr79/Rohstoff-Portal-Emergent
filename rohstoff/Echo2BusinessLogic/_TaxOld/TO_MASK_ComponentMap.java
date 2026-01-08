 
package rohstoff.Echo2BusinessLogic._TaxOld;
   
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
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic._TaxOld.Changes.TOC_MASK_DaughterListForMotherMask;
  
public class TO_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public TO_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
       RecList21 land = new RecList21(_TAB.land)._fillWithAll(new VEK<IF_Field>()._a(LAND.laendername), (r)-> {
    	   try {
			return S.isFull(r.get_ufs_dbVal(LAND.laendername));
		} catch (myException e) {
			return false;
		}
       });
         
        this.registerComponent(new RB_KF(MWSTSCHLUESSEL.bezeichnung),    		new RB_TextField()._width(300));
        this.registerComponent(new RB_KF(MWSTSCHLUESSEL.id_land),    			new RB_selField()._populate(land,LAND.laendername,_TAB.land.key(),true)._width(150));
        this.registerComponent(new RB_KF(MWSTSCHLUESSEL.id_mwstschluessel),    	new RB_lab());
        this.registerComponent(new RB_KF(MWSTSCHLUESSEL.ist_standard),    		new RB_cb());
        this.registerComponent(new RB_KF(MWSTSCHLUESSEL.kurzbezeichnung),    	new RB_TextField()._width(150));
        this.registerComponent(new RB_KF(MWSTSCHLUESSEL.steuersatz),    		new RB_TextField()._width(150));

        this.registerComponent(TOC_MASK_DaughterListForMotherMask.keyForMotherMask, new TOC_MASK_DaughterListForMotherMask(p_tpHashMap,true));
 
        
        this.registerSetterValidators(MWSTSCHLUESSEL.id_mwstschluessel, (map,typ)-> {
        	MyE2_MessageVector mv = bibMSG.getNewMV();
			if (typ==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
 	
				RB_MaskController  cont = new RB_MaskController(map);
	
	        	if (!cont.isNew()) {
	        		
	        		Long id = cont.getLongDBVal(_TAB.mwstschluessel.key());
	        		
	        		Rec21 mwst = new Rec21(_TAB.mwstschluessel)._fill_id(id);
	        		
	        		RecList21 rlChanges = mwst.get_down_reclist21(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel);
	        		
	        		//jetzt ueberschneidungen pruefen
	        		HMAP<Date, Date> checkMap = new HMAP<Date, Date>();
	        		
	        		for (Rec21 rc: rlChanges) {
	        			Date von = rc.getDateDbValue(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von);
	        			Date bis = rc.getDateDbValue(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis);
	        			
	        			if (checkMap.containsKey(von)) {
	        				mv._addAlarm(S.ms("Achtung ! Zwei Einträge für Steuersatzwechsel besitzen das Gleiche Startdatum !!"));
	        				break;
	        			} else {
	        				checkMap._put(von, bis);
	        			}
	        		}
	        		
	        		//jetzt ueberschneidungen pruefen
	        		for (Date dVon: checkMap.keySet()) {
	        			Date dBis =  checkMap.get(dVon);
	        			
	        			//mit diesen beiden jetzt eine pruefschleife
	        			
	        			for (Date dVonInnen: checkMap.keySet()) {
	        				Date dBisInnen = checkMap.get(dVonInnen);
	        				
	        				if (!dVon.equals(dVonInnen)) {   //sonst der gleiche eintrag wie aussen
	        					
	        					if ((dVonInnen.after(dVon) || dVonInnen.equals(dVon)) && ( dVonInnen.before(dBis) || dVonInnen.equals(dBis))) {
	                				mv._addAlarm(S.ms("Achtung ! Zwei Einträge überschneiden sich im Zeitraum !!"));
	                				break;
	        					}
	        					
	        					if ((dBisInnen.after(dVon) || dBisInnen.equals(dVon)) && ( dBisInnen.before(dBis) || dBisInnen.equals(dBis))) {
	                				mv._addAlarm(S.ms("Achtung ! Zwei Einträge überschneiden sich im Zeitraum !!"));
	                				break;
	        					}
	        				}
	        			}
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
 
 
