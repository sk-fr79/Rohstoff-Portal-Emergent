package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public abstract class RB_BtV4_ActionStandardSaveAndReopen extends XX_ActionAgent {
	
	public abstract RB_DataobjectsCollector	  generate_dataObjectsCollector_4_edit(String id_record,RB_ComponentMapCollector 	componentmapCollectorActual) throws myException;

	public RB_BtV4_ActionStandardSaveAndReopen() {
		super();
	}

	private RB_TransportHashMap  m_transportHashMap = null;
	
	public RB_BtV4_ActionStandardSaveAndReopen _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}

	
	public MASK_STATUS getStatusAfterReload() throws myException {
		if (this.m_transportHashMap!=null && this.m_transportHashMap.getLastMaskLoadStatus()!=null) {
			return this.m_transportHashMap.getLastMaskLoadStatus();
		}
		throw new myException(this,"MASK_STATUS is not defined !");
	}
	

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		if (this.m_transportHashMap==null || this.m_transportHashMap.getLeadingMaskKey()==null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Design-Error: Leading Mask-key is not defined !"));
		}
		
		if (this.m_transportHashMap==null || this.m_transportHashMap.getLeadingTableOnMask()==null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Design-Error: Leading Mask-table is not defined !"));
		}
		
		if (this.m_transportHashMap==null || this.m_transportHashMap.getLastMaskLoadStatus()==null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Design-Error: Mask-Status is not defined !"));
		}
		
		if (this.m_transportHashMap==null || this.m_transportHashMap.getMaskComponentMapCollector()==null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Design-Error: RB_ComponentMapCollector is not defined !"));
		}
		
		
		RB_KM 						leadingMaskKey = 		this.m_transportHashMap.getLeadingMaskKey();
		_TAB  						leadingTable =			this.m_transportHashMap.getLeadingTableOnMask();
		MASK_STATUS  				statusOnLoad = 			this.m_transportHashMap.getLastMaskLoadStatus();
		RB_ComponentMapCollector    componentMapCollector = this.m_transportHashMap.getMaskComponentMapCollector();
		
		
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		if (statusOnLoad.isStatusNew()) {
			mv.add_MESSAGE(componentMapCollector.rb_COMPLETE_SAVE_CYCLE(true));
			if (mv.get_bIsOK()) {
				//neue id rausfinden
				String new_id = componentMapCollector.rb_Actual_DataobjectCollector().get_LastWrittenNewID(leadingTable);
				
				if (S.isFull(new_id)) {
					RB_DataobjectsCollector od_coll = this.generate_dataObjectsCollector_4_edit(new_id,componentMapCollector);
					
					mv.add_MESSAGE(componentMapCollector.rb_COMPLETE_FILL_CYCLE(od_coll));

					if (mv.isOK()) {
						this.m_transportHashMap._setMaskDataObjectsCollector(od_coll);
						this.m_transportHashMap._setMaskStatusOnLoad(MASK_STATUS.EDIT);
						
						if (this.m_transportHashMap.getNavigationList()!=null) {
							VEK<String> vIdsMarked = new VEK<String>()._a(new_id)._a(m_transportHashMap.getNavigationList().getMarkedIds());
							this.m_transportHashMap.getNavigationList().ADD_NEW_ID_TO_ALL_VECTORS(new_id);
							this.m_transportHashMap.getNavigationList()._RebuildSiteAndKeepMarkers("");
							this.m_transportHashMap.getNavigationList()._setMarkedIds(vIdsMarked);
						}
					}
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")));
				}
			}
		} else if (statusOnLoad.isStatusEdit()) {
			String id_edit = ""+componentMapCollector.rb_Actual_DataobjectCollector().get(leadingMaskKey).get_RecORD().get_PRIMARY_KEY_VALUE();

			
			if (S.isFull(id_edit)) {
				mv.add_MESSAGE(componentMapCollector.rb_COMPLETE_SAVE_CYCLE(true));
				if (mv.get_bIsOK()) {
					if (componentMapCollector.rb_Actual_DataobjectCollector() instanceof RB_DataobjectsCollector_V2) {
						((RB_DataobjectsCollector_V2)componentMapCollector.rb_Actual_DataobjectCollector()).rb_RebuildAllRecords();
						componentMapCollector.rb_CompleteMaskReload(MASK_STATUS.EDIT);
						if (this.m_transportHashMap.getNavigationList()!=null) {
							this.m_transportHashMap.getNavigationList()._RebuildSiteAndKeepMarkers("");
						}
					} else if (componentMapCollector.rb_Actual_DataobjectCollector() instanceof RB_DataobjectsCollector_V21) {
						((RB_DataobjectsCollector_V21)componentMapCollector.rb_Actual_DataobjectCollector()).rb_RebuildAllRecords();
						componentMapCollector.rb_CompleteMaskReload(MASK_STATUS.EDIT);
						if (this.m_transportHashMap.getNavigationList()!=null) {
							this.m_transportHashMap.getNavigationList()._RebuildSiteAndKeepMarkers("");
						}

					} else if (componentMapCollector.rb_Actual_DataobjectCollector() instanceof RB_DataobjectsCollector_V22) {
						((RB_DataobjectsCollector_V22)componentMapCollector.rb_Actual_DataobjectCollector()).rb_RebuildAllRecords();
						componentMapCollector.rb_CompleteMaskReload(MASK_STATUS.EDIT);
						if (this.m_transportHashMap.getNavigationList()!=null) {
							this.m_transportHashMap.getNavigationList()._RebuildSiteAndKeepMarkers("");
						}
						
					} else {
						throw new myException(this,"Only in V2/V21/V22-variants useable !");
					}
					
					if (mv.get_bIsOK()) {
						mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Datensatz wurde gespeichert")));
					}
				}
				
			} else {
				throw new myException(this,"Error saving mask after edit!!");
			}
		} else {
			throw new myException(this, " only on new and edit possible !");
		}
		bibMSG.add_MESSAGE(mv);
		
	}
	
	

	
}
