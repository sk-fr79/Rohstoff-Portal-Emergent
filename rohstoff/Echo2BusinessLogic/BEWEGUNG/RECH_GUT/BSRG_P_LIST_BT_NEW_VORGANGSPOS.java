package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BSRG_P_LIST_BT_NEW_VORGANGSPOS extends MyE2_ButtonWithKey
{

	private BSRG_K_MASK__ModulContainer 	oBSRG_K_ModulContainerMASK  = null;
	
	public BSRG_P_LIST_BT_NEW_VORGANGSPOS(E2_NavigationList onavigationList,BSRG_P_MASK__ModulContainer oPositionModulContainerMASK, BSRG_K_MASK__ModulContainer oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , true, KeyStrokeListener.VK_F8);
		this.oBSRG_K_ModulContainerMASK = oKopfMaskContainer;
		
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));

		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"NEUEINGABE_POSITION_IN_MASKE"));
		this.setToolTipText(new MyE2_String("Neue Position eingeben").CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		
		private BSRG_P_MASK__ModulContainer oBSRG_P_MASK_ModulContainer = null;
		
		public ownActionAgent(E2_NavigationList onavigationList, BSRG_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe eine Position"), onavigationList, omaskContainer, oownButton, null, null);
			this.oBSRG_P_MASK_ModulContainer = omaskContainer;
			
			this.set_cMessageStartingNew(new MyE2_String("Neueingabe einer Position ..."));
			this.set_cMessageNewRowIsSaved(new MyE2_String("Neue Position wurde gespeichert ..."));
		}
		
		public void do_innerAction() throws myException
		{
			/*
			 * hier pruefen, ob der aussere satz mittlerweile geloescht wurde
			 */
			SQLFieldMAP oSQLFieldMAP = this.get_oMaskContainer().get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oSQLFieldMAP();
			SQLFieldForRestrictTableRange oSQLField_ID_HEAD = (SQLFieldForRestrictTableRange)oSQLFieldMAP.get_("ID_VKOPF_RG");
			
			String cID_VKOPF = oSQLField_ID_HEAD.get_cRestictionValue_IN_DB_FORMAT();
			
			if (bibDB.EinzelAbfrage("SELECT   NVL(DELETED,'N') FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG="+cID_VKOPF).equals("Y"))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Der Kopfsatz wurde bereits geloescht !! - Neueingabe nicht mehr moeglich !"));
				return;
			}
			
			/*
			 * jetzt fuer liefer- und zahlungsbedingungen die momentanen einstellungen aus der kopfmaske setzen
			 */
			E2_ComponentMAP oMAP_KOPF_MASK =			BSRG_P_LIST_BT_NEW_VORGANGSPOS.this.oBSRG_K_ModulContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
			String cZahlungsbedingungen =  ((MyE2IF__DB_Component)oMAP_KOPF_MASK.get__Comp("ZAHLUNGSBEDINGUNGEN")).get_cActualMaskValue();
			String cLieferbedingungen = 	((MyE2IF__DB_Component)oMAP_KOPF_MASK.get__Comp("LIEFERBEDINGUNGEN")).get_cActualMaskValue();
			
			this.oBSRG_P_MASK_ModulContainer.get_oComponentMAP_Mask().get_oSQLFieldMAP().get_("ZAHLUNGSBEDINGUNGEN").set_cDefaultValueFormated(bibALL.null2leer(cZahlungsbedingungen));
			this.oBSRG_P_MASK_ModulContainer.get_oComponentMAP_Mask().get_oSQLFieldMAP().get_("LIEFERBEDINGUNGEN").set_cDefaultValueFormated(bibALL.null2leer(cLieferbedingungen));

			
			
			super.do_innerAction();
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
			oSaveMask.doSaveMask(true);
		}
	}

	
}
