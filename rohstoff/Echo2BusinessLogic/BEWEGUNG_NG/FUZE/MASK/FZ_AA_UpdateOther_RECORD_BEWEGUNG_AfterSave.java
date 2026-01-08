package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;


/**
 * werden mehrere vektoren auf der maske behandelt, dann kann es sein, dass diese von der gleichen Bewegung kommen.
 * dann wird diese bewegung gespeichert und wird beim Speichern eines weiteren Vektors als geaendert erkannt
 * Deshalb wird in alle ComponentMapCollectors mit der gleichen bewegung ein neues recordobjekt geladen 
 * @author martin
 *
 */

public class FZ_AA_UpdateOther_RECORD_BEWEGUNG_AfterSave extends XX_ActionAgent {
	private IF_mapCollector_4_FZ_MaskModulContainer  comp_map_collector = null;

	public FZ_AA_UpdateOther_RECORD_BEWEGUNG_AfterSave(IF_mapCollector_4_FZ_MaskModulContainer p_comp_map_collector) {
		super();
		this.comp_map_collector = p_comp_map_collector;
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {

		Vector<IF_mapCollector_4_FZ_MaskModulContainer>  v_ComponentMapCollectors = this.comp_map_collector.find_all_componentMapContainers_in_mask(true);
		
		RB_ComponentMap mapBEWEGUNG = this.find_Bewegung_Map_if_correct(this.comp_map_collector);

		if (mapBEWEGUNG !=null) {
			if (! mapBEWEGUNG.getRbDataObjectActual().rb_MASK_STATUS().isStatusNew()) {
				String id_bewegung = ((MyRECORD)mapBEWEGUNG.getRbDataObjectActual().get_RecORD()).ufs(BEWEGUNG.id_bewegung);
				
				if (v_ComponentMapCollectors.size()>0) {
					
					Vector<RB_ComponentMapCollector>  v_sammle_to_update = new Vector<>();
					
					for (IF_mapCollector_4_FZ_MaskModulContainer container_on_mask: v_ComponentMapCollectors) {
						RB_ComponentMap testMap = this.find_Bewegung_Map_if_correct(container_on_mask);
						if (!testMap.getRbDataObjectActual().rb_MASK_STATUS().isStatusNew()) {
							String id_bewegung_test = ((MyRECORD)testMap.getRbDataObjectActual().get_RecORD()).ufs(BEWEGUNG.id_bewegung);
							
							if (id_bewegung.equals(id_bewegung_test)) {
								v_sammle_to_update.add((RB_ComponentMapCollector)container_on_mask);
							}
							
						}
					}
					
//					bibMSG.add_MESSAGE(new MyE2_Warning_Message("HABS gefunden: ID_BEWEGUNG="+id_bewegung));
//					bibMSG.add_MESSAGE(new MyE2_Warning_Message("zeilen mit der gleichen id_Bewegung ="+v_sammle_to_update.size()));
					
					for (RB_ComponentMapCollector  mapCollector: v_sammle_to_update) {
						//jetzt den masterkey rausziehen
						IF_MasterKey master = null;
						for (RB_KM key: mapCollector.rb_hm_RB_ComponentMaps().keySet()) {
							if (key instanceof IF_MasterKey) {
								master = (IF_MasterKey)key;
								break;
							}
								
							if (master != null) {
								//sicherheithalber nochmal checken
								if (mapCollector.get((RB_KM)master).getRbDataObjectActual().rec20().get_tab()==_TAB.bewegung) {
									mapCollector.get((RB_KM)master).getRbDataObjectActual().rec20()._rebuild();
								}
							} else {
								throw new myException("Error: every mask must have an masterkey !!");
							}
						}
					}
				}
			}
		} else {
			throw new myException("Error: Masterkey MUST be an BEWEGUNG-type");
		}
	}
	
	private RB_ComponentMap find_Bewegung_Map_if_correct(IF_mapCollector_4_FZ_MaskModulContainer componentMapCollector) {
		RB_ComponentMap map = null;
		
		IF_MasterKey  master_key = componentMapCollector.get_master_key();
		
		if (  ((RB_KM)master_key).get_REALNAME().equals(_TAB.bewegung.fullTableName())) {
			//sollte immer so sein, da der masterkey immer bei einer bewegung startet
			map = ((RB_ComponentMapCollector)this.comp_map_collector).get((RB_KM)master_key);
		}
		
		return map;
	}
	

}
