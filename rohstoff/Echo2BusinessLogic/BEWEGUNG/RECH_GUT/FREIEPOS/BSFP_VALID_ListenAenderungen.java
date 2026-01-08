package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_VALID_ListenAenderungen extends XX_ActionValidator {

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
	{
		return null;
	}

	protected MyE2_MessageVector isValid(String cID_Unformated_POS)	throws myException 
	{
		RECORD_VPOS_RG  oVPos = new RECORD_VPOS_RG(cID_Unformated_POS);
		
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (oVPos.is_DELETED_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei gelöschten Datensätzen nicht möglich !")));
		}
		
		if (oVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg() != null)
		{
			if (oVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei abgeschlossenen Vorgängen nicht möglich !")));
			}
		}
		
		return oMV;
	}

}
