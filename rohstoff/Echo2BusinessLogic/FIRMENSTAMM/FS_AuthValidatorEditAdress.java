package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class FS_AuthValidatorEditAdress extends E2_ButtonAUTHValidator
{

	public FS_AuthValidatorEditAdress()
	{
		super(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"BEARBEITE_ADRESSE");
	}

}
