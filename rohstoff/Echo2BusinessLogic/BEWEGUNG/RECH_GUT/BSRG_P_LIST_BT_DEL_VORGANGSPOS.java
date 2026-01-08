package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class BSRG_P_LIST_BT_DEL_VORGANGSPOS extends MyE2_Button
{

	public BSRG_P_LIST_BT_DEL_VORGANGSPOS(E2_NavigationList onavigationList,BSRG_K_MASK__ModulContainer oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"LOESCHEN_POSITION_IN_MASKE"));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_RG","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));
		
		// validator prueft, ob der kopf bereits abgeschlossen wurde
//		String cQuery = "SELECT   NVL(ABGESCHLOSSEN,'N') FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN (SELECT   NVL(ID_VKOPF_RG,0) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_RG=#WERT#)";
//		this.add_IDValidator(new E2_Validator_ID_DBQuery(cQuery,bibALL.get_Array("N"),true, new MyE2_String("Der Vorgang wurde bereits abgeschlossen !")));
		
		this.add_IDValidator(new ownValidator());
		
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Positionen").CTrans());
	}
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String ID_VPOS_RG)  	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VPOS_RG recVPOS = new RECORD_VPOS_RG(ID_VPOS_RG);
			
			if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
			{
				if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Vorgang wurde bereits abgeschlossen !"));
				}
			}
			
			if (recVPOS.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position wurde bereits gelöscht !"));
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
			super(onavigationList,  "JT_VPOS_RG", true);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
}
