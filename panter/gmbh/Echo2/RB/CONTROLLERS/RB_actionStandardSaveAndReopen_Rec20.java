package panter.gmbh.Echo2.RB.CONTROLLERS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public abstract class RB_actionStandardSaveAndReopen_Rec20 extends XX_ActionAgent {

	private RB_KM 							mask_key_main = null;
	private _TAB  							leading_table_on_mask = null;
	
	
	
	public RB_actionStandardSaveAndReopen_Rec20() {
		super();
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		//RB_ModuleContainerMASK  mask_container = this.bt_generating_mask_container.get_MaskContainer();
		if (this.mask_key_main==null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Design-Error: no mask_key_main was set !"));
			return;
		}
		if (this.leading_table_on_mask==null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Design-Error: no leading_table_on_mask was set !"));
			return;
		}
		
		
		
		RB_ComponentMapCollector  component_map_collector = this.get_componentMapCollector();
		if (component_map_collector==null) {
			throw new myException(this,"cannot find the leading componentmap-collector");
		} else {
			MyE2_MessageVector  mv = new MyE2_MessageVector();
			MASK_STATUS  status_old = component_map_collector.rb_Actual_DataobjectCollector().get(this.mask_key_main).rb_MASK_STATUS();
			
			if (status_old.isStatusNew()) {
				mv.add_MESSAGE(component_map_collector.rb_COMPLETE_SAVE_CYCLE(true));
				if (mv.get_bIsOK()) {
					//neue id rausfinden
					String new_id = component_map_collector.rb_Actual_DataobjectCollector().get_LastWrittenNewID(this.get_leading_table_on_mask());
					
					if (S.isFull(new_id)) {
						RB_DataobjectsCollector od_coll = this.generate_dataObjectsCollector_4_edit(new_id,component_map_collector);
						mv.add_MESSAGE(component_map_collector.rb_COMPLETE_FILL_CYCLE(od_coll));
					} else {
						mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")));
					}
				}
			} else if (status_old.isStatusEdit()) {
				String id_edit = ""+component_map_collector.rb_Actual_DataobjectCollector().get(this.mask_key_main).get_RecORD().get_PRIMARY_KEY_VALUE();

				
				if (S.isFull(id_edit)) {
					mv.add_MESSAGE(component_map_collector.rb_COMPLETE_SAVE_CYCLE(true));
					if (mv.get_bIsOK()) {
						component_map_collector.rb_Actual_DataobjectCollector().rb_Rebuild_ALL_RECORD(true);
						//RB_DataobjectsCollector od_coll = this.generate_dataObjectsCollector_4_edit(id_edit);
						mv.add_MESSAGE(component_map_collector.rb_COMPLETE_MASK_RELOAD());
						
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
	
	
	public abstract RB_DataobjectsCollector 	generate_dataObjectsCollector_4_edit(String id_record,RB_ComponentMapCollector componentmapCollectorActual) throws myException;
	public abstract RB_ComponentMapCollector  	get_componentMapCollector() throws myException;


	
	public RB_KM get_mask_key_main() {
		return mask_key_main;
	}


	public _TAB get_leading_table_on_mask() {
		return leading_table_on_mask;
	}


	public RB_actionStandardSaveAndReopen_Rec20 _set_mask_key_main(RB_KM p_mask_key_main) {
		this.mask_key_main = p_mask_key_main;
		return this;
	}


	public RB_actionStandardSaveAndReopen_Rec20 _set_leading_table_on_mask(_TAB p_leading_table_on_mask) {
		this.leading_table_on_mask = p_leading_table_on_mask;
		return this;
	}
	
}
