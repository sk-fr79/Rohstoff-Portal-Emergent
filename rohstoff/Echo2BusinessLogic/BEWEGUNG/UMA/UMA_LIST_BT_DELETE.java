package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;


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
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class UMA_LIST_BT_DELETE extends MyE2_Button
{

	public UMA_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , true);
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_UMA_LIST,"LOESCHE_UMA_KONTRAKT"));
		this.add_IDValidator(new ownValidatorDeleteAllowed());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList, "JT_UMA_KONTRAKT",false);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	

	
	private class ownValidatorDeleteAllowed extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			return null;
		}
		@Override
		protected MyE2_MessageVector isValid(String cID_UMA_KONTRAKT) throws myException
		{
			RECORD_UMA_KONTRAKT recUMA = new RECORD_UMA_KONTRAKT(cID_UMA_KONTRAKT);
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (recUMA.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Dieser UMA-Kontrakt ist bereits gelöscht"));
			}
			if (recUMA.is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Dieser UMA-Kontrakt wurde bereits GESCHLOSSEN und kann daher nicht mehr gelöscht werden !"));
			}

			if (recUMA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_uma_kontrakt("NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'","",true).get_vKeyValues().size()>0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Dieser UMA-Kontrakt hat bereits Verbindungen zu Fuhren und darf deshalb nicht gelöscht werden !"));
			}
			
			
			return oMV;
		}
	}
	
	
}
