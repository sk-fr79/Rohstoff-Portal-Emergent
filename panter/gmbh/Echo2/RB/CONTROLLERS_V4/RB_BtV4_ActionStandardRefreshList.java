package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.indep.exceptions.myException;

public class RB_BtV4_ActionStandardRefreshList extends XX_ActionAgent {

	private RB_TransportHashMap  m_transportHashMap = null;
	
	public RB_BtV4_ActionStandardRefreshList _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}
	
	public RB_BtV4_ActionStandardRefreshList() {
		super();
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		if (m_transportHashMap==null || this.m_transportHashMap.getNavigationList()==null) {
			throw new myException(this,"MaskContainer/RB_MASK_VECTOR: NOT SET !!!");
		} else {
			this.m_transportHashMap.getNavigationList()._RebuildSiteAndKeepMarkers("");
		}

	}

}
