package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Validator_KOPF_UND_POSITION_OFFEN;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class BST_P_LIST_BT_DELETE_VORGANGSPOS extends MyE2_Button
{

	public BST_P_LIST_BT_DELETE_VORGANGSPOS(	E2_NavigationList 			onavigationList,
												BST_K_MASK__ModulContainer 	oKopfMaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_IDValidator(new BS_Validator_KOPF_UND_POSITION_OFFEN("JT_VPOS_TPA"));
		this.add_IDValidator(new ownValidator_Fuhre_ist_noch_nicht_gebucht_ODER_ist_gloescht_ODER_ist_storniert());

		//2012-08-02: neue schaerfere validierer fuer das loeschen von tpa-positionen
		this.add_IDValidator(new ownID_Validator_CheckFuhren());
		
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"LOESCHEN_TPA_POS"));
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) TPA-Positionen").CTrans());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList, "JT_VPOS_TPA",true);
		}

		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
	
	
	private class ownValidator_Fuhre_ist_noch_nicht_gebucht_ODER_ist_gloescht_ODER_ist_storniert extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VPOS_TPA_UF) throws myException
		{
			MyE2_MessageVector  oMV = new  MyE2_MessageVector();
			
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA(cID_VPOS_TPA_UF).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
			
			if (recFuhre.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde bereits gelöscht !")));
			}
			
			if (recFuhre.is_IST_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde storniert !")));
			}
			
			long iAnzahlBuchungen = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get_ID_VPOS_RG_l_Count_NotNull(
					new RECLIST_VPOS_RG.Validation()
					{
						@Override
						public boolean isValid(RECORD_VPOS_RG orecord) throws myException
						{
							if (orecord.is_DELETED_NO())
							{
								return true;
							}
							return false;
						}
						
					});

			
			if (recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT) ||
				recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT)   || iAnzahlBuchungen>0)
			{
				if (recFuhre.is_ALT_WIRD_NICHT_GEBUCHT_NO())
				{
//					TEST20110325
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fuhre kann nicht mehr bearbeitet werden, da bereits Buchungspositionen vorliegen !")));
				}
			}
			return oMV;
		}

	}	
	
	
	
	
	/*
	 * 2012-08-02: neue schaerfere validierer fuer das loeschen von tpa-positionen
	 */
	private class ownID_Validator_CheckFuhren extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VPOS_TPA) throws myException
		{
			MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
			
			RECORD_VPOS_TPA  recVPosTPA = new RECORD_VPOS_TPA(cID_VPOS_TPA);
			
			PRUEF_RECORD_VPOS_TPA_FUHRE recFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(recVPosTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0),true);
			oMVRueck.add_MESSAGE(recFuhre.Check_if_can_be_deleted());
			
			return oMVRueck;
		}
	}

	
	
	
}
