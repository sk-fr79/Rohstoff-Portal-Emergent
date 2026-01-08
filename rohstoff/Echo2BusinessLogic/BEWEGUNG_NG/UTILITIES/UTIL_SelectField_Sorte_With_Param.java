package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class UTIL_SelectField_Sorte_With_Param extends MyE2_SelectFieldWithParameters 
{

	public UTIL_SelectField_Sorte_With_Param() throws myException 
	{
		super("SELECT DISTINCT  AR.Anr1 || ' - ' ||  AR.ArtBez1 , AR.id_artikel  from "
				+ bibE2.cTO()
				+ ".JT_ARTIKEL AR  "
				+ " WHERE AR.id_mandant="
				+ bibALL.get_ID_MANDANT() + " #P1# ORDER BY 1", false, true,
		false, false);
		
		this.AddParameter("#P1#");
		this.RefreshComboboxFromSQL();
	}

	public void add_ActionAgent( XX_ActionAgent oActionAgent) throws myException{
		this.add_oActionAgent(oActionAgent);
		this.RefreshComboboxFromSQL();
	}
	
}



