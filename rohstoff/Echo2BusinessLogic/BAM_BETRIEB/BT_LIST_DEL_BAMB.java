/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentSingleDelete_Basic;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class BT_LIST_DEL_BAMB extends MyE2_Button
{
	public BT_LIST_DEL_BAMB(	E2_NavigationList oList)
	{
		super(E2_ResourceIcon.get_RI("delete.png"), true);
		this.add_oActionAgent(new ButtonActionAgentSingleDelete_Basic(new MyE2_String("Löschen einer Betriebs-Beanstandung:"),oList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_BBAM_LIST,"LOESCHE_BBAM"));

	}
}