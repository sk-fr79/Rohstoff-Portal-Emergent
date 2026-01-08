package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class BSFP_LIST_BT_DELETE extends MyE2_Button
{

	private BS_ButtonActionAgentMULTIDELETE  ActionAgentForDelete = null;
	
	public BSFP_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , true);
		
		this.ActionAgentForDelete = new ownActionAgent(onavigationList);
		
		this.add_oActionAgent(this.ActionAgentForDelete);
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN,"LOESCHE_POSITION"));
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

	
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList, "JT_VPOS_RG",false);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}


	public BS_ButtonActionAgentMULTIDELETE get_ActionAgentForDelete()
	{
		return ActionAgentForDelete;
	}
}
