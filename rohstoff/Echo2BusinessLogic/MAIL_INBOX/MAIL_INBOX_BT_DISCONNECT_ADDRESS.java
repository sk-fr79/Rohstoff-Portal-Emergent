package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.exceptions.myException;


public class MAIL_INBOX_BT_DISCONNECT_ADDRESS extends MyE2_Button
{

	public MAIL_INBOX_BT_DISCONNECT_ADDRESS( E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("clear.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("MAIL_INBOX_DISCONNECT_ADDRESS"));
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date   12.03.2013
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		
		E2_BasicModuleContainer_MASK m_omaskContainer = null;
		String m_sIDEmailEntry = null;
		
		public ownActionAgent( E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super();
			m_omaskContainer = omaskContainer;
		}

		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_Button			oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 	oMap = oButton.EXT().get_oComponentMAP();
			
			MyE2_DB_TextField   oID = (MyE2_DB_TextField) oMap.get__Comp("ID_EMAIL_INBOX");
			String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			
			MAIL_INBOX_Handler oHandler = new MAIL_INBOX_Handler();
			oHandler.disconnectAddressFromEmailEntry(cID);

			if (bibMSG.get_bIsOK()){
				m_omaskContainer.get_oNavigationListWhichBelongsToTheMask().RefreshList();
				
				
				// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
				String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
				oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
				// ********
			}

			// buttonstatus nach Speichern setzen
			((MAIL_INBOX_MASK_ComponentMAP)oMap).setButtonStatus_Of_MaskButtons();
			
			
		}
	}
	

}
