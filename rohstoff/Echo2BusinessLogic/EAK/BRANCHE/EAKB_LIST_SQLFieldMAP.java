package rohstoff.Echo2BusinessLogic.EAK.BRANCHE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class EAKB_LIST_SQLFieldMAP extends Project_SQLFieldMAP
{

	public EAKB_LIST_SQLFieldMAP() throws myException
	{
		super("JT_EAK_BRANCHE", "", false);
		
		// ein weiteres Feld fuer die benutzung im umschalt-button, der auf den Gruppen-Tab schaltet
		this.add_SQLField(new SQLField("JT_EAK_BRANCHE.ID_EAK_BRANCHE","ID_EAK_BRANCHE_B",new MyE2_String("Feld Für Button"),bibE2.get_CurrSession()), false);
		
		this.initFields();
	}

	
}
