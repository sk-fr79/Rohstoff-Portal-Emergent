package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class HADM_bt_validator extends XX_ActionValidator {

	private String id_hilfeText = null;

	
	
	public HADM_bt_validator(String p_id_hilfeText) {
		super();
		this.id_hilfeText = p_id_hilfeText;
	}

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		RECORD_USER  recUser = new RECORD_USER(bibALL.get_RECORD_USER().get_ID_USER_lValue(-1l).longValue());
		RECORD_HILFETEXT  rec = new RECORD_HILFETEXT(HADM_bt_validator.this.id_hilfeText);

		boolean b_erlaubt = false;
		
		if ((recUser.is_DEVELOPER_YES() || recUser.is_GESCHAEFTSFUEHRER_YES())) {
			b_erlaubt = true;
		} else {
			if (rec.get_ID_USER_URSPRUNG_cUF_NN("-1").equals(recUser.get_ID_USER_cUF())) {
				b_erlaubt = true;
			}
		}

		if (!b_erlaubt) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Aktion nur für eigene Einträge erlaubt !")));
		}

		
		return mv;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
		return null;
	}

}
