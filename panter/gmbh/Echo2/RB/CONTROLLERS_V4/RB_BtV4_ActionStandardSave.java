package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RB_BtV4_ActionStandardSave extends XX_ActionAgent {

	private RB_TransportHashMap  m_transportHashMap = null;
	
	public RB_BtV4_ActionStandardSave _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}
	
	public RB_BtV4_ActionStandardSave() {
		super();
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		if (m_transportHashMap==null || m_transportHashMap.getRBModulContainerMask()==null) {
			throw new myException(this,"MaskContainer NOT existing !!!");
		} else {
			MyE2_MessageVector  oMV = m_transportHashMap.getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);
			if (oMV.isOK()) {
				//falls voraussetzungen erfuellt, dann die liste ergaenzen und refreshen
				if (m_transportHashMap.getLastMaskLoadStatus().isStatusNew()) {
					if (m_transportHashMap.getNavigationList()!=null &&  m_transportHashMap.getMaskDataObjectsCollector()!=null) {
						String id = m_transportHashMap.getMaskDataObjectsCollector()
										.get_LastWrittenNewID(this.m_transportHashMap.getLeadingTableOnMask().fullTableName());
						VEK<String> vIdsMarked = new VEK<String>()._a(id)._a(m_transportHashMap.getNavigationList().getMarkedIds());
						m_transportHashMap.getNavigationList().ADD_NEW_ID_TO_ALL_VECTORS(id);
						m_transportHashMap.getNavigationList()._RebuildSiteAndKeepMarkers("");
						m_transportHashMap.getNavigationList()._setMarkedIds(vIdsMarked);
					}
				}
			}
			
			bibMSG.add_MESSAGE(oMV);
		}

	}

}
