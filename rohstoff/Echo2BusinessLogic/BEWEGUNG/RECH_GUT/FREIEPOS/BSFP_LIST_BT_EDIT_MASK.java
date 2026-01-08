package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_LIST_BT_EDIT_MASK extends MyE2_Button
{

	private ownActionAgent oAgentEdit = null;
	
	public BSFP_LIST_BT_EDIT_MASK(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true);
		this.oAgentEdit = new ownActionAgent(onavigationList,omaskContainer,this);
		this.add_oActionAgent(this.oAgentEdit);
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN,"BEARBEITE_POSITION"));
		this.add_IDValidator(new ownValidator());
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated) 	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VPOS_RG) 	throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_VPOS_RG  recVPOS = new RECORD_VPOS_RG(cID_VPOS_RG);
			if (recVPOS.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die POSITION wurde bereits gelöscht !"));
			}
			if (S.isFull(recVPOS.get_ID_VKOPF_RG_cUF_NN("")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die POSITION wurde bereits einem Kopfsatz zugeordnet !"));
			}
			if (S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) || S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position ist Teil eines Stornozyklus !"));
			}
			
			return oMV;
		}
		
	}
	
	
	public MyE2_Button get_oSaveButtonInMask()
	{
		return oAgentEdit.get_oButtonMaskSave();
	}

	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten einer Position"), onavigationList, omaskContainer, oownButton, null, null);
		}
	}

	
}
