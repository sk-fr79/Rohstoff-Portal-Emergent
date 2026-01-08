 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
   
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_STATUS;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
  
public class WF_SIMPLE_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
	
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
    
	public WF_SIMPLE_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);

        this.m_tpHashMap._setToExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.DEFAULT_STATUS , getDefaultStatus());
        this.m_tpHashMap._setToExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.ID_STATUS_ABGESCHLOSSEN , getStatusAbgeschlossen());
        this.m_tpHashMap._setToExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.DEFAULT_PRIO   , getDefaultPrio());
        
        this.registerComponent(WF_SIMPLE_CONST.getLeadingMaskKey(), new WF_SIMPLE_MASK_ComponentMap(this.m_tpHashMap));
        this.registerComponent(WF_SIMPLE_CONST.getMaskKeyLaufzettel(), new WF_SIMPLE_MASK_CompMap_LAUFZETTEL(this.m_tpHashMap));
    }
 	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}

	
	/**
	 * ermittelt den default-Status 
	 * @author manfred
	 * @date 25.03.2019
	 *
	 * @return
	 * @throws myException
	 */
	private String getDefaultStatus() throws myException {
		// status new 
		String idStatusDefault = null;
		SqlStringExtended sql = new SqlStringExtended("SELECT * FROM JT_LAUFZETTEL_STATUS WHERE nvl(ISDEFAULT,'N') = ? order by ID_LAUFZETTEL_STATUS" );
		sql.getValuesList().add(new Param_String("default", "Y"));
		
		RecList21 rlStatus = new RecList21(LAUFZETTEL_STATUS._tab())._fill(sql);
		
		if (rlStatus != null && rlStatus.size() == 1) {
			idStatusDefault = rlStatus.get(0).get_key_value();
		} else {
			throw new myException("Kein oder kein eindeutiger Default-Status für den Laufzettel definiert.");
		}
		
		return idStatusDefault;
	}
	
	
	/**
	 * ermittelt die ID des Status "Abgeschlossen"
	 * @author manfred
	 * @date 25.03.2019
	 *
	 * @return
	 * @throws myException
	 */
	private String getStatusAbgeschlossen() throws myException {
		String idStatusAbgeschlossen = null;
		SqlStringExtended sql = new SqlStringExtended("SELECT * FROM JT_LAUFZETTEL_STATUS WHERE nvl(TRIGGER_ABSCHLUSS,'N') = ? order by ID_LAUFZETTEL_STATUS" );
		sql.getValuesList().add(new Param_String("default", "Y"));

		RecList21 rlStatus = new RecList21(LAUFZETTEL_STATUS._tab())._fill(sql);
		
		if (rlStatus != null && rlStatus.size() == 1) {
			idStatusAbgeschlossen = rlStatus.get(0).get_key_value();
		} else {
			throw new myException("Kein oder kein eindeutiger Status für den Abschluss eines Laufzettels definiert.");
		}
		
		return idStatusAbgeschlossen;
	}
	
	
	/**
	 * ermittelt die Default-Prio 
	 * @author manfred
	 * @date 25.03.2019
	 *
	 * @return
	 * @throws myException
	 */
	private String getDefaultPrio() throws myException {
		// status new 
		String idPrioDefault = null;
		SqlStringExtended sql = new SqlStringExtended("SELECT * FROM JT_LAUFZETTEL_PRIO WHERE nvl(ISDEFAULT,'N') = ? order by ID_LAUFZETTEL_PRIO" );
		sql.getValuesList().add(new Param_String("default", "Y"));
		
		RecList21 rlStatus = new RecList21(LAUFZETTEL_PRIO._tab())._fill(sql);
		
		if (rlStatus != null && rlStatus.size() == 1) {
			idPrioDefault = rlStatus.get(0).get_key_value();
		} else {
			throw new myException("Keine oder keine eindeutige Default-PRIO für den Laufzettel definiert.");
		}
		
		return idPrioDefault;
	}
	
	
}
 
 
