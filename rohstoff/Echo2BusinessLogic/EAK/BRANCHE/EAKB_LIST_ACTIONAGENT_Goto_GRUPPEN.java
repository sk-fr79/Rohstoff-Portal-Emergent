package rohstoff.Echo2BusinessLogic.EAK.BRANCHE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;
import rohstoff.utils.EAK_DataRecordHashMap_BRANCHE;

public class EAKB_LIST_ACTIONAGENT_Goto_GRUPPEN extends XX_ActionAgent
{

	public void executeAgentCode(ExecINFO oExecInfo)
	{
		MyE2_DB_Button oButton = (MyE2_DB_Button) bibE2.get_LAST_ACTIONEVENT().getSource();
		
		String cID_BRANCHE = oButton.get_cActualRowID();

		try
		{
			// auf das 2. tab umschalten
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerGRUPPE().set_Branche(cID_BRANCHE);
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oTabbed().setSelectedIndex(1);
			
			
			EAK_DataRecordHashMap_BRANCHE oBranche = new EAK_DataRecordHashMap_BRANCHE(cID_BRANCHE);
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerGRUPPE().get_labelBranchenText().setText(oBranche.get_UnFormatedValue("BRANCHE"));
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerGRUPPE().get_labelBranchenNummer().setText(oBranche.get_UnFormatedValue("KEY_BRANCHE"));
			
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		

	}

}
