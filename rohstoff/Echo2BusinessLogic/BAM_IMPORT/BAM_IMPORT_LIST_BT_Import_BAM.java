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
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_LIST_BT_Import_BAM extends MyE2_Button
{

	E2_NavigationList m_naviList = null;
	public BAM_IMPORT_LIST_BT_Import_BAM(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(new MyE2_String("Import APP-Daten"), E2_ResourceIcon.get_RI("down.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("IMPORT_BAM_FROM_WEBSERVICE"));
		this.setToolTipText(new MyE2_String("Import der Einträge aus der Lagerplatz-APP").CTrans());
		
		m_naviList = onavigationList;
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			BAMImporter bam = new BAMImporter();
			bam.doImport();
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Abzugslisten der APP wurden importiert."));
			m_naviList.RefreshList();
		}
	}
	
}
