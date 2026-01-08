package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class KFIX_K_L_BT_DELETE_VORGANG extends MyE2_Button
{



	public KFIX_K_L_BT_DELETE_VORGANG(E2_NavigationList onavigationList, VORGANGSART belegtyp) throws myException
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));

		this.add_IDValidator(new ownValidator());
		
		if(belegtyp==VORGANGSART.EK_KONTRAKT){
			this.add_GlobalValidator(ENUM_VALIDATION.VKOPF_KON_EK_DELETE.getValidator());
		}else{
			this.add_GlobalValidator(ENUM_VALIDATION.VKOPF_KON_VK_DELETE.getValidator());
		}
		
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		//this.add_GlobalValidator(new E2_ButtonAUTHValidator(modulKenner,"LOESCHEN_KONTRAKT"));
		
		
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Kontrakte").CTrans());

	}

	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList,  "JT_VKOPF_KON", false);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}



	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String ID_VKOPF_KON) 	throws myException
		{
			String cMessagePart1 = "Kontrakt löschen VERBOTEN: Grund: ";

			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			RECORD_VKOPF_KON  recKON = new RECORD_VKOPF_KON(ID_VKOPF_KON);

			if (recKON.is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Ist bereits geschlossen !"));
				return oMV;
			}


			if (recKON.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Ist bereits gelöscht !"));
				return oMV;
			}


			for (int i=0;i<recKON.get_DOWN_RECORD_LIST_VPOS_KON_id_vkopf_kon("NVL(DELETED,'N')='N'", "ID_VPOS_KON",false).size();i++)
			{
				RECORD_VPOS_KON recVPOS_KON = recKON.get_DOWN_RECORD_LIST_VPOS_KON_id_vkopf_kon().get(i);

				if (recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Eine Position ist bereits abgeschlossen !"));
					return oMV;
				}

				if (recVPOS_KON.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek().size()>0 ||
						recVPOS_KON.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk().size()>0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Eine Position hat bereits EK-VK-Zuordnungen !"));
					return oMV;
				}

				if (recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon().size()>0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Eine Position hat bereits Lager-Zuordnungen !"));
					return oMV;
				}

				if (recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N'", "ID_VPOS_TPA_FUHRE",false).size()>0 || 
						recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N'", "ID_VPOS_TPA_FUHRE",false).size()>0	)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Eine Position hat bereits Fuhren-Zuordnungen !"));
					return oMV;
				}

				if (recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord("NVL(DELETED,'N')='N'", "ID_VPOS_RG",false).size()>0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessagePart1+" Eine Position hat bereits Rechnungs/Gutschrift-Zuordnungen !"));
					return oMV;
				}
			}



			return oMV;
		}

	}



}
