package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_LIST_BT_CREATE_BELEG__ID_Validator extends XX_ActionValidator
{

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException
	{
		return null;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_VPOS_RG)  	throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		RECORD_VPOS_RG  recVPOS_RG = new RECORD_VPOS_RG(cID_VPOS_RG);
		
		if (recVPOS_RG.is_DELETED_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die POSITION wurde bereits gelöscht !")));
		}
		
		if (S.isFull(recVPOS_RG.get_ID_VKOPF_RG_cUF()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die POSITION wurde bereits einem Kopfsatz zugeordnet !")));
		}

		if (S.isEmpty(recVPOS_RG.get_ANZAHL_cUF()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Position hat keine Menge !")));
		}
		if (S.isEmpty(recVPOS_RG.get_EINZELPREIS_FW_cUF()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Position hat keine Einzelpreis !")));
		}
		if (S.isEmpty(recVPOS_RG.get_STEUERSATZ_cUF()))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Position hat keinen Steuersatz !")));
		}
		
		return oMV;
	}

}
