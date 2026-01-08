package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;


//sorgt dafuer, dass bei acconto-lieferungen papiere nur gedruckt werden koennen, wenn die zugehoerigen rechnungen bezahlt (verbucht) sind
public class BST___Valid_BelegDruck_Acconto_Kunden extends XX_ActionValidator
{
  
	@Override
	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
	{
		return null;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_VPOS_TPA_FUHRE) throws myException
	{
		return new ___FUHRE_VALID_HELPER_AKONTO_Sicherung().pruefe_akonto_status(new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE));
	}

	
	
}
