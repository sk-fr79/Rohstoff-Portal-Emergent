package panter.gmbh.Echo2.RB.VALID;

import java.util.Vector;
import java.util.regex.Pattern;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIELD_RULE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class RB_Validator_FieldRules_RegEx extends RB_Validator_Component {

	
	private Vector<RECORD_FIELD_RULE>  		vRecAllowedRegEx = null;
	
	public RB_Validator_FieldRules_RegEx(Vector<RECORD_FIELD_RULE> AllowedRegEx)
	{
		super();
		this.vRecAllowedRegEx = AllowedRegEx;
	}


	@Override
	public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
	
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();

		//nur savable sinnvoll
		if (rb_Component instanceof IF_RB_Component_Savable) {
			
			IF_RB_Component_Savable compSave = (IF_RB_Component_Savable)rb_Component;
			
			//wert der komponente beschaffen
			String cWert = compSave.rb_readValue_4_dataobject();
		
			if (S.isFull(cWert))
			{
				boolean bEinAusdruckErfuellt = false;
				
				for (RECORD_FIELD_RULE recREGEX: this.vRecAllowedRegEx)
				{
					if (S.isFull(recREGEX.get_RULE_cUF_NN(""))) {
						if (Pattern.matches(recREGEX.get_RULE_cUF_NN(""), cWert)) {
							bEinAusdruckErfuellt=true;
							break;
						}
					}
				}
				
				if (!bEinAusdruckErfuellt)	{
					String cPraefix = "<"+rb_Component.rb_KF().HASHKEY()+"> Erlaubte Feldregeln: ";
					String cInfo = cPraefix;
					for (RECORD_FIELD_RULE recREGEX: this.vRecAllowedRegEx) {
						cInfo=cInfo+(cInfo.equals(cPraefix)?"":" // ")+recREGEX.get_RULE_INFO_cUF_NN(recREGEX.get_RULE_cUF_NN("<keine Info zur Eingaberegel ...>"));
					}
					oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String(cInfo,false)));
				}
			}
		
		}
		return oMV_Rueck;
	}
	
}
