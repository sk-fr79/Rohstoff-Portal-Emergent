package rohstoff.Echo2BusinessLogic.EAK.GRUPPE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;
import rohstoff.utils.EAK_DataRecordHashMap_BRANCHE;
import rohstoff.utils.EAK_DataRecordHashMap_GRUPPE;

public class EAKG_LIST_ACTIONAGENT_Goto_CODES extends XX_ActionAgent
{

	public void executeAgentCode(ExecINFO oExecInfo)
	{
		MyE2_DB_Button oButton = (MyE2_DB_Button) bibE2.get_LAST_ACTIONEVENT().getSource();
		
		String cID_GRUPPE = oButton.get_cActualRowID();

		try
		{
			// auf das 3. tab umschalten
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerCODE().set_Gruppe(cID_GRUPPE);
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oTabbed().setSelectedIndex(2);
			
			EAK_DataRecordHashMap_GRUPPE oGruppe = new EAK_DataRecordHashMap_GRUPPE(cID_GRUPPE);
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerCODE().get_labelGruppenText().setText(oGruppe.get_UnFormatedValue("GRUPPE"));
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerCODE().get_labelGruppenCode().setText(oGruppe.get_UnFormatedValue("KEY_GRUPPE"));
	
			EAK_DataRecordHashMap_BRANCHE oBranche = new EAK_DataRecordHashMap_BRANCHE(oGruppe.get_UnFormatedValue("ID_EAK_BRANCHE"));
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerCODE().get_labelBranchenText().setText(oBranche.get_UnFormatedValue("BRANCHE"));
			((EAK_BasicModuleContainer)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oContainerCODE().get_labelBranchenCode().setText(oBranche.get_UnFormatedValue("KEY_BRANCHE"));
			
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		

	}

}
