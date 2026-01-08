package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class FU_LIST_ValidatorDeleteAllowed extends XX_ActionValidator
{

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
	{
		return new MyE2_MessageVector();
	}

	
	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		try
		{
			
			PRUEF_RECORD_VPOS_TPA_FUHRE  recFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(cID_Unformated,true);
			
			
			if	 	(!recFuhre.get_ID_VPOS_TPA_cF_NN("--").equals("--"))
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Hier können nur freie Transport-Positionen (ohne TPA) gelöscht werden !")));
			else if (recFuhre.__Actual_StatusBuchung()==myCONST.STATUS_FUHRE__GANZGEBUCHT)
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen verboten! Es liegen schon alle Rechnungs- und/oder Gutschriftsbuchungen vor !")));
			else if (recFuhre.__Actual_StatusBuchung()==myCONST.STATUS_FUHRE__TEILSGEBUCHT)
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen verboten! Es liegen schon Teile der Rechnungs- und/oder Gutschriftsbuchungen vor !")));
			else if (recFuhre.get_bHasMengenEintraege())
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen verboten! Es wurden bereits Buchungsmengen eingetragen !")));
			else if (recFuhre.is_DELETED_YES())
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Transport-Position wurde bereits gelöscht")));
			else if (recFuhre.get_bHasWiegekarte())
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Transport-Position hat bereits eine zugeordnete Wiegekarte")));
			else if (!recFuhre.get_DAT_FAHRPLAN_FP_cUF_NN("").equals(""))
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fahrplan-Position wurde bereits einem Fahrplan zugeordnet!")));
			
		}
		catch (myException ex)
		{
			oMV.add_MESSAGE(ex.get_ErrorMessage());
		}
		return oMV;
	}

}
