package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSA_P_LIST_BT_EDIT_VORGANGSPOS extends MyE2_Button
{

	private E2_NavigationList oNaviList_VPOS = null;
	
	public BSA_P_LIST_BT_EDIT_VORGANGSPOS(E2_NavigationList onavigationList,BSA_P_MASK__ModulContainer oPositionModulContainerMASK, E2_BasicModuleContainer_MASK oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true);
		
		this.oNaviList_VPOS = onavigationList;
		
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"BEARBEITEN_POSITION_IN_MASKE"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

		
		this.setToolTipText(new MyE2_String("Bearbeiten eines (oder mehrerer) Positionen").CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSA_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten einer Position"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartEdit(new MyE2_String("Bearbeiten eine Position ..."));
		}
	}
	
	
	private class actionSaveBeforeDaughterMask extends XX_ActionAgent
	{
		private E2_BasicModuleContainer_MASK  oMaskContainer = null;
		
		public actionSaveBeforeDaughterMask(E2_BasicModuleContainer_MASK maskContainer)
		{
			super();
			oMaskContainer = maskContainer;
		}

		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(this.oMaskContainer,null);
			
			// die position und der status der tochterseite muss gesichert und nach dem neuladen nach speichern wiederhergestellt werden
			Vector<String> vID_actualSite = BSA_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_vSelectedIDs_Unformated();
			int iActualPage = BSA_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_iActualPage();

			oSaveMask.doSaveMask(true);
			
			BSA_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.goToPage(null, iActualPage);
			BSA_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.set_SelectIDs(vID_actualSite);
			
		}
		
	}
}
