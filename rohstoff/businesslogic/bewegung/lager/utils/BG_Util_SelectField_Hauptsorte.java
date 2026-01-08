package rohstoff.businesslogic.bewegung.lager.utils;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BG_Util_SelectField_Hauptsorte extends MyE2_SelectField 
{

	public BG_Util_SelectField_Hauptsorte(int iWidth) throws myException 
	{
		super("SELECT DISTINCT  SUBSTR(AR.ANR1,0,2) , SUBSTR(AR.ANR1,0,2)  from "
				+ bibE2.cTO()
				+ ".JT_BEWEGUNG_ATOM A1"
				+ " INNER JOIN "
				+ bibE2.cTO()
				+ ".JT_ARTIKEL AR on A1.ID_ARTIKEL= AR.ID_ARTIKEL "
				+ " WHERE A1.id_mandant= "
				+ bibALL.get_ID_MANDANT() + " ORDER BY 1", false, true,
		false, false);
		
		this.setWidth(new Extent(iWidth));
		this.RefreshComboboxFromSQL();
	}

	public void add_ActionAgent( XX_ActionAgent oActionAgent) throws myException{
		this.add_oActionAgent(oActionAgent);
		this.RefreshComboboxFromSQL();
	}
	
}




