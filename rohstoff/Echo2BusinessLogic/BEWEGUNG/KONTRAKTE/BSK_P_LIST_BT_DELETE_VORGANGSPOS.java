package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

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
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class BSK_P_LIST_BT_DELETE_VORGANGSPOS extends MyE2_Button
{

	public BSK_P_LIST_BT_DELETE_VORGANGSPOS(	E2_NavigationList 			onavigationList,
												BSK_K_MASK__ModulContainer 	oKopfMaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_KOPF_OFFEN);
		this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_POSITION_OFFEN);
		this.add_IDValidator(new ownValidatorLoeschenErlaubt());
		this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_KEINE_EK_VK_ZUORDNUNGEN);
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

		
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"LOESCHEN_KONTRAKT_POS"));
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Kontrakt-Positionen").CTrans());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList,  "JT_VPOS_KON", true);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
	
	private class ownValidatorLoeschenErlaubt extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String idVposKon) 	throws myException
		{
			MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
			
			RECORD_VPOS_KON  recVpos = new RECORD_VPOS_KON(idVposKon);
			
			RECLIST_VPOS_TPA_FUHRE reclistFuhren = null;
			
			if (recVpos.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT))
			{
				reclistFuhren = recVpos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' " +
																							" AND NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'","JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE",true);
			}
			else
			{
				reclistFuhren = recVpos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' " +
																							" AND NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'","JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE",true);
			}
			
			if (reclistFuhren.get_vKeyValues().size()>0)
			{
				oMVRueck.add(new MyE2_Alarm_Message("Die Kontraktposition hat zugeordnete, unstornierte Fuhren-> Löschen ist verboten !!"));
			}
			
			return oMVRueck;
		}
		
	}
	
	
}
