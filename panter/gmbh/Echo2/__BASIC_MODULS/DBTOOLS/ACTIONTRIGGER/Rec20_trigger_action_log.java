package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_trigger_action_log extends Rec20 {

	
//	//falls es fehler bei der interpretation gibt, werden diese hier abgefanden und in ein popup-fenster geschrieben.
//	//sie duerfen nicht in die bib-Message , sonst kann ein fehlerhafter trigger das system lahmlegen!
//	private String ErrorStatus = null;
	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec20_trigger_action_log() throws myException {
		super(_TAB.trigger_action_log);
	}


	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_trigger_action_log(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	
	
	@SuppressWarnings("rawtypes")
	public void validate_and_execute_trigger(MyE2_MessageVector mv) throws myException {
		try {
			Rec20_trigger_action_def recTriggerDef = new Rec20_trigger_action_def(this.get_up_rec20(TRIGGER_ACTION_DEF.id_trigger_action_def));
			
			String classNameValidation = recTriggerDef.get_ufs_dbVal(TRIGGER_ACTION_DEF.validation_class);
			String classNameExecution = recTriggerDef.get_ufs_dbVal(TRIGGER_ACTION_DEF.execution_class);
			
			Class validator =	Class.forName(classNameValidation);
			Class executor = 	Class.forName(classNameExecution);
			 
			Object validator_o = validator.newInstance();
			Object executor_o = executor.newInstance();
			
			
			if (validator_o instanceof IF_trigger_validator) {
				
				if (executor_o instanceof IF_trigger_executer) {
				
					if (((IF_trigger_validator)validator_o).isValid(this,recTriggerDef,mv)) {
						((IF_trigger_executer)executor_o).execute(this,recTriggerDef,mv);
					}
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Executor implementiert nicht das Interface  <IF_trigger_executer>...")));
				}
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Validierer implementiert nicht das Interface  <IF_trigger_validator>...")));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen eines ActionTriggers ...")));
		}
	}
	
	
}
