package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class FU__Validator_Fuhre_ist_gloescht_ODER_ist_storniert extends XX_ActionValidator
{

	@Override
	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
	{
		return null;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_VPOS_TPA_FUHRE_UF) throws myException
	{
		MyE2_MessageVector  oMV = new  MyE2_MessageVector();
		
		RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE_UF);
		
		if (recFuhre.is_DELETED_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde bereits gelöscht !")));
		}
		
		if (recFuhre.is_IST_STORNIERT_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde storniert !")));
		}
		
//		if (recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT) ||
//			recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
//		{
//			if (recFuhre.is_ALT_WIRD_NICHT_GEBUCHT_NO())
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fuhre kann nicht mehr bearbeitet werden, da bereits Buchungspositionen vorliegen !")));
//			}
//		}
		return oMV;
	}

}
