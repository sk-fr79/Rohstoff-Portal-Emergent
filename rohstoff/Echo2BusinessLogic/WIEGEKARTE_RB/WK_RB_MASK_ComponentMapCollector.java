 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
   
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Mehrfachverwiegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;
  
public class WK_RB_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
	
    //zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
	
    
	public WK_RB_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);

        // Child Befundung registrieren
        this.registerComponent(RecDOWiegekarteBefund.key, new WK_RB_MASK_ComponentMap_Befund(this.m_tpHashMap));
        // Wiegekarte registrieren
        this.registerComponent(RecDOWiegekarte.key, 	new WK_RB_MASK_ComponentMap_Wiegekarte 	(this.m_tpHashMap));
        
        WK_RB_cb_Mehrfachverwiegung cb = (WK_RB_cb_Mehrfachverwiegung) this.rb_get_ComponentMAP(RecDOWiegekarte.key).get__Comp(WK_RB_cb_Mehrfachverwiegung.key.get_HASHKEY());
        
    }
 	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}

	
	
	
	
	private class ownActionSaveAndReopen_Wiegekarte extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			WK_RB_MASK_ComponentMapCollector oThis = WK_RB_MASK_ComponentMapCollector.this;
			_DoCompleteSaveCycle();
			
		}
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 08.10.2020
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_MASK_ComponentMapCollector _DoCompleteSaveCycle() throws myException {
		return _DoCompleteSaveCycle(true);
	}
		
	
	/**
	 * 
	 * @author manfred
	 * @date 16.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_MASK_ComponentMapCollector _DoCompleteSaveCycle(boolean bForceSaveEvenWhenNothingWasChanged) throws myException {
		MASK_STATUS  stat = rb_Actual_DataobjectCollector().get(RecDOWiegekarte.key).rb_MASK_STATUS();
		E2_NavigationList navilist = m_tpHashMap.getNavigationList();


		if (stat==MASK_STATUS.NEW || stat == MASK_STATUS.NEW_COPY) {
			bibMSG.add_MESSAGE(rb_COMPLETE_SAVE_CYCLE(bForceSaveEvenWhenNothingWasChanged));

			
			if (bibMSG.get_bIsOK()) {
			
				//falls neueingabe, dann die id der liste zufuegen und seite refresh
				String id_vektor_to_reload = this.rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.wiegekarte);
				if (S.isFull(id_vektor_to_reload) && navilist != null) {
					navilist.ADD_NEW_ID_TO_ALL_VECTORS_IN_FRONT(bibVECTOR.get_Vector(id_vektor_to_reload));
					navilist._REBUILD_ACTUAL_SITE(id_vektor_to_reload);
				} 
			
				bibMSG.add_MESSAGE(rb_CompleteMaskReload(MASK_STATUS.EDIT));
				m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.EDIT);

			} else	if (navilist != null) {
				navilist._REBUILD_COMPLETE_LIST(null);
			}


		} else {
			bibMSG.add_MESSAGE(rb_COMPLETE_SAVE_CYCLE(bForceSaveEvenWhenNothingWasChanged));

			if (navilist != null) {
				navilist._REBUILD_ACTUAL_SITE(null);
				
			}
			
			bibMSG.add_MESSAGE(rb_CompleteMaskReload(MASK_STATUS.EDIT));
		}
		
		return this;
	}


	
	@Override
	public MyE2_MessageVector rb_COMPLETE_SAVE_CYCLE(boolean bForceSaveEvenWhenNothingWasChanged) throws myException {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		mv = super.rb_COMPLETE_SAVE_CYCLE(bForceSaveEvenWhenNothingWasChanged);
		return mv;
	}
	
	
	

	

	/**
	 * lädt die Maske neu, ohne vorher zu speichern.
	 * @author manfred
	 * @date 08.10.2020
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_MASK_ComponentMapCollector do_CompleteMaskReload() throws myException {
		
		MASK_STATUS  currentMaskStatus = rb_Actual_DataobjectCollector().get(RecDOWiegekarte.key).rb_MASK_STATUS();
		E2_NavigationList navilist = m_tpHashMap.getNavigationList();

		rb_CompleteMaskReload(currentMaskStatus);
		
		return this;
		
	}
	
	
	
	
}
 
 






