package panter.gmbh.Echo2.RB.CONTROLLERS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public abstract class RB_actionStandardSaveAndReopen extends XX_ActionAgent {

	//private IF_generating_mask_container  	bt_generating_mask_container = null;
	private RB_KM 							mask_key_main = null;
	
	
	public RB_actionStandardSaveAndReopen(RB_KM p_mask_key_main) {
		super();
//		this.bt_generating_mask_container = p_bt_generating_mask_container;
		this.mask_key_main = p_mask_key_main;
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		//RB_ModuleContainerMASK  mask_container = this.bt_generating_mask_container.get_MaskContainer();
		RB_ModuleContainerMASK  mask_container = this.get_RB_ModuleContainerMASK();
		if (mask_container==null) {
			throw new myException(this,"MaskContainer/RB_MASK_VECTOR: NOT SET !!!");
		} else {
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			MASK_STATUS  status_old = mask_container.rb_FirstComponentMapCollector().rb_Actual_DataobjectCollector().get(this.mask_key_main).rb_MASK_STATUS();
			
			if (status_old.isStatusNew()) {
				oMV.add_MESSAGE(mask_container.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true));
				if (oMV.get_bIsOK()) {
					// TODO Martin: muss prüfen, ob das stimmt. _TAB.email_send war hardcoded..2
					//neue id rausfinden
					String new_id = mask_container.rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector().get_LastWrittenNewID(mask_key_main.get_db_table());
//					String new_id = mask_container.rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.email_send);
					
					if (S.isFull(new_id)) {
						RB_DataobjectsCollector od_coll = this.generate_dataObjectsCollector_4_edit(new_id);
						oMV.add_MESSAGE(mask_container.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(od_coll));
					} else {
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")));
					}
				}
			} else if (status_old.isStatusEdit()) {
				String id_edit = ""+mask_container.rb_FirstComponentMapCollector().rb_Actual_DataobjectCollector().get(this.mask_key_main).get_RecORD().get_PRIMARY_KEY_VALUE();

				
				if (S.isFull(id_edit)) {
					oMV.add_MESSAGE(mask_container.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true));
					if (oMV.get_bIsOK()) {
						mask_container.rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector().rb_Rebuild_ALL_RECORD(true);
						//RB_DataobjectsCollector od_coll = this.generate_dataObjectsCollector_4_edit(id_edit);
						oMV.add_MESSAGE(mask_container.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_MASK_RELOAD());
						
						if (oMV.get_bIsOK()) {
							oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Datensatz wurde gespeichert")));
						}
					}
					
				} else {
					throw new myException(this,"Error saving mask after edit!!");
				}
			} else {
				throw new myException(this, " only on new and edit possible !");
			}
			bibMSG.add_MESSAGE(oMV);
		}

	}
	
	
	public abstract RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record) throws myException;
	public abstract RB_ModuleContainerMASK  get_RB_ModuleContainerMASK() throws myException;
	
}
