package panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.components.MyE2_CheckBox;

public abstract class XX_SelectAgentForCheckboxesVisible implements E2_IF_Copy
{

	/**
	 * Hier wird definiert, welche checkbox sichtbar ist und welche nicht
	 * @param oCB
	 * @return 
	 */
	public abstract boolean get_Visible(MyE2_CheckBox oCB);
	

}
