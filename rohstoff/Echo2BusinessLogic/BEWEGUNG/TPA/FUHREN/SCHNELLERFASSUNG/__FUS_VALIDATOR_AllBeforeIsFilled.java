package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public class __FUS_VALIDATOR_AllBeforeIsFilled extends XX_ActionValidator
{
	
	private __FUS_STANDARD_Element  oCompWhichIsValidated = null;
	
	
	
	public __FUS_VALIDATOR_AllBeforeIsFilled(__FUS_STANDARD_Element CompWhichIsValidated)
	{
		super();
		this.oCompWhichIsValidated = CompWhichIsValidated;
	}

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
		Vector<__FUS_STANDARD_Element>  vElements = this.oCompWhichIsValidated.get_KomponentenDieGefuelltSeinMuessen();
		
		if (vElements!=null)
		{
			for (int i=0;i<vElements.size();i++)
			{
				__FUS_STANDARD_Element oTest = vElements.get(i);
				
				if (!oTest.get_bIsCorrectFilled(true))
				{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(oTest.get_InfoMessage()));
				}
			}
		}
		return oMVRueck;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) 	throws myException
	{
		return null;
	}

}
