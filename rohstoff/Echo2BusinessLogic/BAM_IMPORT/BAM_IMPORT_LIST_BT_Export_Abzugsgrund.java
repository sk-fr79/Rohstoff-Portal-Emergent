package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_LIST_BT_Export_Abzugsgrund extends MyE2_Button
{

	public BAM_IMPORT_LIST_BT_Export_Abzugsgrund(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(new MyE2_String("Abzugsgrund Export"),E2_ResourceIcon.get_RI("save.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EXPORT_XML_ABZUGSLISTE"));
		this.setToolTipText(new MyE2_String("Die Liste der Abzugsgründe für die Lagerplatz-APP exportieren.").CTrans());

		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			BAM_XMLGenerator_For_ABZUGSGRUND xmlGen = new BAM_XMLGenerator_For_ABZUGSGRUND();
			
			xmlGen.runExport();
			
			
			
		}
	}
	
}
