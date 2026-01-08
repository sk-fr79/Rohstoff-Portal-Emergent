package panter.gmbh.Echo2.RB.VALID;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIELD_RULE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class RB_Validator_FieldRules_NOT_EMPTY extends RB_Validator_Component {

	
	private RECORD_FIELD_RULE 		RecFieldRuleNotEmpty = null;
	
	public RB_Validator_FieldRules_NOT_EMPTY(RECORD_FIELD_RULE   recFieldRuleNotEmpty)  {
		super();
		this.RecFieldRuleNotEmpty = recFieldRuleNotEmpty;
	}


	@Override
	public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
	
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();

		//nur savable sinnvoll
		if (rb_Component instanceof IF_RB_Component_Savable) {
			
			IF_RB_Component_Savable compSave = (IF_RB_Component_Savable)rb_Component;
			
			//wert der komponente beschaffen
			String cWert = compSave.rb_readValue_4_dataobject();
		
			if (S.isEmpty(cWert)) {
				String cInfo = "<"+rb_Component.rb_KF().HASHKEY()+"> Erlaubte Feldregeln: ";
				cInfo=cInfo+RecFieldRuleNotEmpty.get_RULE_INFO_cUF_NN("<keine Info zur Eingaberegel ...>");
				oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String(cInfo,false)));
			}
		
		}
		return oMV_Rueck;
	}
	
}
