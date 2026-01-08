package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MAIL_INBOX_BT_CLEAR_CONFIRMATION extends MyE2_Button
{

	public MAIL_INBOX_BT_CLEAR_CONFIRMATION( )
	{
		super(E2_ResourceIcon.get_RI("clear.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyString("Löscht die Bestätigung und öffnet den Datensatz.").CTrans());
		this.add_oActionAgent(new ownActionAgent(/*omaskContainer,*/this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("CLEAR_CONFIRMATION_MAIL_INBOX"));
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date   18.03.2013
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent( MyE2_Button oownButton)
		{
			super();
		}

		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			// ermitteln der Component-Map der Maske
			MyE2IF__Component oComponentFromExecInfo 	= (MyE2IF__Component)oExecInfo.get_MyActionEvent().getSource();
			E2_ComponentMAP oMap = null;
			if (oComponentFromExecInfo instanceof MyE2_Button){
				oMap = oComponentFromExecInfo.EXT().get_oComponentMAP();
			}
			
			E2_BasicModuleContainer_MASK oMaskContainer = oMap.get_oModulContainerMASK_This_BelongsTo();

			// den User löschen und das Datumsfeld auch automatisch ändern
			String sIDUser = bibALL.get_ID_USER_FORMATTED();
			DB_Component_USER_DROPDOWN oUser = (DB_Component_USER_DROPDOWN)oMap.get__Comp("ID_USER_GELESEN");
			oUser.set_ActiveValue("");
			oUser.get_vActionAgents().get(0).ExecuteAgentCode(new ExecINFO(oExecInfo.get_MyActionEvent(),true));

			// die Maske Speichern
			new E2_SaveMaskStandard(oMaskContainer, oMaskContainer.get_oNavigationListWhichBelongsToTheMask()).doSaveMask(true);
			
			// neues Laden der Maske nachdem die unerliegenden Daten geändert wurden
//			String sSTATUS_MASKE = oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS();
//			oMap.get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs().Requery_All_ActualResultMAPs(sSTATUS_MASKE);
			


//			((MAIL_INBOX_MASK_ComponentMAP)oMap).setButtonStatus_Of_MaskButtons();
			

		}
	}

}
