package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FS_BT_EU_Vertrag_DruckMail_validator extends XX_ActionValidator{

	private FS_POPUP_PrintMail_EU_VERTRAG parent;

	public FS_BT_EU_Vertrag_DruckMail_validator(FS_POPUP_PrintMail_EU_VERTRAG p_parent) {
		super();
		this.parent = p_parent;

	}

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		MyE2_MessageVector omv = new MyE2_MessageVector();
		if(bibALL.isEmpty(this.parent.getVertragsDatum())){
			this.parent.getVertragsDatumFeld().show_InputStatus(false);
			omv.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Sie ein Vertragsdatum an !"));
		}

		if(S.isEmpty(this.parent.getVerantworlichPerson())){
			this.parent.getVerantworlichePersonInuptStatus(false);
			omv.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Sie eine verantwortliche Person an !"));
		}
		return omv;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {

		return null;
	}

}
