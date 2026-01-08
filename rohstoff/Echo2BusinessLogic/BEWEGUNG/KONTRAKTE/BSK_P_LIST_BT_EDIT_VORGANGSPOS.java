package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BSK_P_LIST_BT_EDIT_VORGANGSPOS extends MyE2_ButtonWithKey
{
	private E2_NavigationList oNaviList_VPOS = null;

	public BSK_P_LIST_BT_EDIT_VORGANGSPOS(	E2_NavigationList 			onavigationList,
			 								BSK_P_MASK__ModulContainer 	oPositionModulContainerMASK, 
			 								BSK_K_MASK__ModulContainer 	oKopfMaskContainer) throws myException
			 								
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true,KeyStrokeListener.VK_F4);
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));
		
		this.oNaviList_VPOS = onavigationList;

		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this.get_oButton()));
		this.add_IDValidator(new ownValidator());

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"BEARBEITEN_POSITION_IN_MASKE"));
		this.setToolTipText(new MyE2_String("Bearbeiten eines (oder mehrerer) Positionen").CTrans());
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			RECORD_VPOS_KON recVPOS_KON = new RECORD_VPOS_KON(unformated);
			
			if (recVPOS_KON.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position wurde bereits gelöscht !"));
			}
			else if (recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position wurde bereits ABGESCHLOSSEN!"));
			}
			
			return oMV;
		}
		
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSK_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten einer Position"), onavigationList, omaskContainer, oownButton, null, new BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise(omaskContainer));
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
			Vector<String> vID_actualSite = BSK_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_vSelectedIDs_Unformated();
			int iActualPage = BSK_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_iActualPage();

			oSaveMask.doSaveMask(true);
			
			BSK_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.goToPage(null, iActualPage);
			BSK_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.set_SelectIDs(vID_actualSite);

		}
	}

}
