package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class BSAAL_ButtonCopy extends MyE2_Button 
{
	private BSAAL__ModulContainerLIST	oModulContainerList = null;

	public BSAAL_ButtonCopy(BSAAL__ModulContainerLIST	oModulContainer)
	{

		super(E2_ResourceIcon.get_RI("copy.png"), true);
		
		this.oModulContainerList = oModulContainer;

		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Kopieren der ausgewählten Positionen ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_COPY_POSITIONS));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSAAL_ButtonCopy oThis = BSAAL_ButtonCopy.this;
			
			Vector<String> vIDsToCopy = oThis.oModulContainerList.get_oNaviList().get_vSelectedIDs_Unformated();
			
			if (vIDsToCopy.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte selektieren Sie mindestens 1 Angebotsposition !!"));
			}
			else
			{
				bibMSG.add_MESSAGE(oThis.valid_IDValidation(vIDsToCopy));
				if (bibMSG.get_bIsOK())
				{
					try
					{
						new BSAAL_WindowPaneToCopyAngebote(oThis.oModulContainerList,vIDsToCopy);
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufruf des Kopierdialogs !!"));
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					}
				}
			}
		}
	}
}
