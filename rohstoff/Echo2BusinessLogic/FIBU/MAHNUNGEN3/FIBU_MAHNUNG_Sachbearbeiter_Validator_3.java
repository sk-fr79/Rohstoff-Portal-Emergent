package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_SACHBEARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_SACHBEARBEITER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_MAHNUNG_Sachbearbeiter_Validator_3 extends XX_ActionValidator {


	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		RECLIST_FIBU_SACHBEARBEITER relistSachbearbeiter = new RECLIST_FIBU_SACHBEARBEITER(RECORD_FIBU_SACHBEARBEITER.FIELD__ID_MANDANT + "=" + bibALL.get_ID_MANDANT(), RECORD_FIBU_SACHBEARBEITER.FIELD__ID_USER);
		MyE2_MessageVector 	oMV = new MyE2_MessageVector();
		if(relistSachbearbeiter.size()==0){
			oMV.add(new MyE2_Warning_Message(new MyE2_String("Es ist kein FIBU Sachbearbeiter eingetragen.")));
		}

		return oMV;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
		return null;
	}

	public static void sachBearbeiterWarnung() throws myException{
		RECLIST_FIBU_SACHBEARBEITER relistSachbearbeiter = new RECLIST_FIBU_SACHBEARBEITER(RECORD_FIBU_SACHBEARBEITER.FIELD__ID_MANDANT + "=" + bibALL.get_ID_MANDANT(), RECORD_FIBU_SACHBEARBEITER.FIELD__ID_USER);
		if(relistSachbearbeiter.size()==0){
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es ist kein FIBU Sachbearbeiter eingetragen.")));
		}else if(relistSachbearbeiter.size()>3){
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es sind mehr als 3 FIBU Sachbearbeiter eingetragen.")));
		}
	}
}


