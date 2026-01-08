package panter.gmbh.Echo2.decisions;

import java.util.UUID;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.IF_Message_4_Decisions;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyE2_AlarmMessage_4_Confirm_ABSTRACT extends MyE2_Alarm_Message implements IF_Message_4_Decisions {
	
	private UUID uuid_ComponentWhichStartedThis = null;
	private UUID uuid_ActionAgent_OR_Validator = null;
	
	//optional kann auch die Ausloesende Komponente uebergeben werden, damit der Klick gleich ausgefuehrt werden kann
	private E2_IF_Handles_ActionAgents  o_ComponentCalling = 	null;
	
	public MyE2_AlarmMessage_4_Confirm_ABSTRACT(MyString cmessage, E2_IF_Handles_ActionAgents  oComponentCalling, UUID uuidActionAgent_OR_Validator) throws myException {
		super(new MyE2_String("ERROR: YOU MUST CALL INIT in Class >MyE2_AlarmMessageBasic_4_Confirm>"));
		this.o_ComponentCalling = oComponentCalling;
		this.uuid_ComponentWhichStartedThis = 	((MyE2IF__Component)oComponentCalling).EXT().get_UUID();
		this.uuid_ActionAgent_OR_Validator = 	uuidActionAgent_OR_Validator;
		this.set_Message(cmessage);
	}
	
	public UUID get_uuid_ComponentWhichStartedThis() {
		return uuid_ComponentWhichStartedThis;
	}

	public UUID get_uuid_ActionAgent_OR_Validator() {
		return uuid_ActionAgent_OR_Validator;
	}

	
	/**
	 * @return ActionComponent, can be null
	 */
	public E2_IF_Handles_ActionAgents get_oComponentCalling() {
		return o_ComponentCalling;
	}

	public void set_oComponentCalling(E2_IF_Handles_ActionAgents oComponentCalling) {
		this.o_ComponentCalling = oComponentCalling;
	}
	
	
}
