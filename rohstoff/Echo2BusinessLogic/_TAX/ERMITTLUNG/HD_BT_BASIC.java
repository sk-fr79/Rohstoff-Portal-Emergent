package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;

public class HD_BT_BASIC extends MyE2_Button
{

	public HD_BT_BASIC(ImageReference oImg, ENUM_VALIDATION valid)
	{
		super(oImg,true);
		
		//2018-07-12: neue validierung:  this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(ValidTag));
		this.add_GlobalValidator(valid.getValidatorWithoutSupervisorPersilschein());
	}

}
