package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public class __FUS_ActionAfterFound 
{
	private __FUS_STANDARD_Element  oCompWhichIsValidated = null;
	
	
	public __FUS_ActionAfterFound(__FUS_STANDARD_Element CompWhichIsValidated) throws myException
	{
		super();
		this.oCompWhichIsValidated = CompWhichIsValidated;
		
		Vector<__FUS_STANDARD_Element>  vElements = this.oCompWhichIsValidated.get_KomponentenDieGeleertWerden();
		
		if  (vElements != null)
		{
			for (int i=0;i<vElements.size();i++)
			{
				__FUS_STANDARD_Element oTest = vElements.get(i);
				
				oTest.clean__Field();
			}
		}

		
	}

}
