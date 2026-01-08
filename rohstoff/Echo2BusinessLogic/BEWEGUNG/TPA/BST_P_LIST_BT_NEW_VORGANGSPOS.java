package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BST_P_LIST_BT_NEW_VORGANGSPOS extends MyE2_ButtonWithKey
{

	public BST_P_LIST_BT_NEW_VORGANGSPOS(	E2_NavigationList onavigationList,
											BST_P_MASK__ModulContainer oPositionModulContainerMASK, 
											BST_K_MASK__ModulContainer oKopfMaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("new.png") , true, KeyStrokeListener.VK_F8);
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));

		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"NEUEINGABE_TPA_POS"));
		this.setToolTipText(new MyE2_String("Neue TPA-Position eingeben").CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BST_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Neueingabe einer TPA-Position"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartingNew(new MyE2_String("Neueingabe einer TPA-Position ..."));
			this.set_cMessageNewRowIsSaved(new MyE2_String("Neue TPA-Position wurde gespeichert ..."));
			
//			this.get_oButtonMaskSave().get_vSQL_STACK_DAEMON().add(new FUHREN_SQL_DAEMON());

		}
		
		public void do_innerAction() throws myException
		{
			/*
			 * hier pruefen, ob der aussere satz mittlerweile geloescht wurde
			 */
			SQLFieldMAP oSQLFieldMAP = this.get_oMaskContainer().get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oSQLFieldMAP();
			SQLFieldForRestrictTableRange oSQLField_ID_HEAD = (SQLFieldForRestrictTableRange)oSQLFieldMAP.get_("ID_VKOPF_TPA");
			
			String cID_VKOPF = oSQLField_ID_HEAD.get_cRestictionValue_IN_DB_FORMAT();
			
			if (bibDB.EinzelAbfrage("SELECT   NVL(DELETED,'N') FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_VKOPF_TPA="+cID_VKOPF).equals("Y"))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Der Kopfsatz wurde bereits geloescht !! - Neueingabe nicht mehr moeglich !"));
				return;
			}
			
			super.do_innerAction();
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
			oSaveMask.doSaveMask(true);
		}
	}

}
