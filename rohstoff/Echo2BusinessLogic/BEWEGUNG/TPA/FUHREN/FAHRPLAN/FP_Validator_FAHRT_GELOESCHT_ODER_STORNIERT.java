package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class FP_Validator_FAHRT_GELOESCHT_ODER_STORNIERT extends XX_ActionValidator
{
	@Override
	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
	{
		return new MyE2_MessageVector();
	}

	@Override
	protected MyE2_MessageVector isValid(String unformated) throws myException
	{
		RECORD_VPOS_TPA_FUHRE oTestRec = new RECORD_VPOS_TPA_FUHRE(unformated);
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (oTestRec.is_DELETED_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fahrt ist bereits gelöscht !"));
		}
		
		if (oTestRec.is_IST_STORNIERT_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fahrt wurde storniert !"));
		}
		
		return oMV;
	}

}
