package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BSK_P_LIST_BT_NEW_VORGANGSPOS extends MyE2_ButtonWithKey
{

	
	private BSK_K_MASK__ModulContainer VKopfMaskContainer = null; 
	
	public BSK_P_LIST_BT_NEW_VORGANGSPOS(	E2_NavigationList onavigationList,
											BSK_P_MASK__ModulContainer oPositionModulContainerMASK, 
											BSK_K_MASK__ModulContainer oKopfMaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("new.png") , true, KeyStrokeListener.VK_F8);
		
		this.VKopfMaskContainer = oKopfMaskContainer;
		
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));

		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"NEUEINGABE_POSITION_IN_MASKE"));
		this.add_GlobalValidator(new ownValidatorDisableWhenHeadClosed());
		
		this.setToolTipText(new MyE2_String("Neue Position eingeben").CTrans());
	}
	
	
	private class ownValidatorDisableWhenHeadClosed extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException 
		{
			MyE2_MessageVector  mvRueck = new MyE2_MessageVector();
			
			String c_ID_HEAD =BSK_P_LIST_BT_NEW_VORGANGSPOS.this.VKopfMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			RECORD_VKOPF_KON recVkopf = new RECORD_VKOPF_KON(c_ID_HEAD);
			
			if (recVkopf.is_ABGESCHLOSSEN_YES())
			{
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Kontrakt ist abgeschlossen (=gedruckt) - Es kann keine neue Position angefügt werden")));
			}
			return mvRueck;
			
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException 
		{
			return null;
		}
		
		
	}
	
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSK_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe eine Position"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartingNew(new MyE2_String("Neueingabe einer Position ..."));
			this.set_cMessageNewRowIsSaved(new MyE2_String("Neue Position wurde gespeichert ..."));
		}
		
		public void do_innerAction() throws myException
		{
			/*
			 * hier pruefen, ob der aussere satz mittlerweile geloescht wurde
			 */
			SQLFieldMAP oSQLFieldMAP = this.get_oMaskContainer().get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oSQLFieldMAP();
			SQLFieldForRestrictTableRange oSQLField_ID_HEAD = (SQLFieldForRestrictTableRange)oSQLFieldMAP.get_("ID_VKOPF_KON");
			
			String cID_VKOPF = oSQLField_ID_HEAD.get_cRestictionValue_IN_DB_FORMAT();
			
			if (bibDB.EinzelAbfrage("SELECT   NVL(DELETED,'N') FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON="+cID_VKOPF).equals("Y"))
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
