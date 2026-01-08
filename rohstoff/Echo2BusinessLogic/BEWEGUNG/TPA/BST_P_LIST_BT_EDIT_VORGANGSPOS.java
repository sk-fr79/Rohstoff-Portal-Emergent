package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Validator_KOPF_UND_POSITION_OFFEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK;

public class BST_P_LIST_BT_EDIT_VORGANGSPOS extends MyE2_Button
{
	private E2_NavigationList oNaviList_VPOS = null;

	public BST_P_LIST_BT_EDIT_VORGANGSPOS(	E2_NavigationList 			onavigationList,
			 								BST_P_MASK__ModulContainer 	oPositionModulContainerMASK, 
			 								BST_K_MASK__ModulContainer 	oKopfMaskContainer) throws myException
			 								
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true);
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));

		this.oNaviList_VPOS = onavigationList;
		
		Vector<Component> vZusatzKomponent =new Vector<Component>();
		vZusatzKomponent.add(new ___BUTTON_LIST_BT_Mail_and_Print(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK(oPositionModulContainerMASK,false),"TRANSPORTAUFTRAGSMODUL"));
		
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this,vZusatzKomponent));
		this.add_IDValidator(new BS_Validator_KOPF_UND_POSITION_OFFEN("JT_VPOS_TPA"));
		this.add_IDValidator(new ownValidator_Fuhre_ist_noch_nicht_gebucht_ODER_ist_gloescht_ODER_ist_storniert());

		this.add_IDValidator(new ownActionValidator_Storno_or_deleted());

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"BEARBEITEN_TPA_POS"));
		this.setToolTipText(new MyE2_String("Bearbeiten eines (oder mehrerer) TPA-Positionen").CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, BST_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton,Vector<Component> vZusatzKomponent) throws myException
		{
			super(new MyE2_String("Bearbeiten einer TPA-Position"), onavigationList, omaskContainer, oownButton, vZusatzKomponent, null);
			this.set_cMessageStartEdit(new MyE2_String("Bearbeiten einer TPA-Position ..."));
//			this.get_oButtonMaskSave().get_vSQL_STACK_DAEMON().add(new FUHREN_SQL_DAEMON());
		}
	}
	
	
	private class ownActionValidator_Storno_or_deleted extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated) 	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			RECORD_VPOS_TPA orec_vpos = new RECORD_VPOS_TPA(unformated);
			RECORD_VPOS_TPA_FUHRE orec = new RECORD_VPOS_TPA(unformated).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0); 
			
			if (orec_vpos.get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Transportauftrag wurde bereits geschlossen !")));
				return oMV;
			}
			
			
			if (orec.is_IST_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position wurde bereits storniert !!"));
			}
			
			if (orec_vpos.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position ist gelöscht worden !!"));
			}
			
			

			
			return oMV;
		}
		
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
			return oMV;
		}

	}

	
	
	
	private class actionSaveBeforeDaughterMask extends XX_ActionAgent
	{
		private E2_BasicModuleContainer_MASK  oMaskContainer = null;
		
		public actionSaveBeforeDaughterMask(E2_BasicModuleContainer_MASK maskContainer)
		{
			super();
			oMaskContainer = maskContainer;
		}

		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(this.oMaskContainer,null);
			
			
			// die position und der status der tochterseite muss gesichert und nach dem neuladen nach speichern wiederhergestellt werden
			Vector<String> vID_actualSite = BST_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_vSelectedIDs_Unformated();
			int iActualPage = BST_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_iActualPage();

			oSaveMask.doSaveMask(true);
			
			BST_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.goToPage(null, iActualPage);
			BST_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.set_SelectIDs(vID_actualSite);

		}
	}

}
