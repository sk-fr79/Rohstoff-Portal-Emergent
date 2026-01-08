package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


/*
 * umbuchen einer Fahrt oder Fahrtgruppe in einen anderen fahrplan
 */
public class FP_BUTTON_UMBUCHEN extends MyE2_Button
{
	
	public FP_BUTTON_UMBUCHEN(E2_NavigationList  list) throws myException
	{
		super(new MyE2_String("Übernahme"));
		
		this.add_oActionAgent(new FP_ActionAgent_UMBUCHEN_STD(list, null, null));
		
		this.add_IDValidator(new FP_Validator_FAHRT_GELOESCHT_ODER_STORNIERT());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("FAHRT_UMBUCHEN"));
	}
}
