package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

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
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BSRG_P_LIST_BT_EDIT_VORGANGSPOS extends MyE2_ButtonWithKey
{
	private E2_NavigationList oNaviList_VPOS = null;

	public BSRG_P_LIST_BT_EDIT_VORGANGSPOS(	E2_NavigationList 				onavigationList,
											BSRG_P_MASK__ModulContainer 		oPositionModulContainerMASK, 
											E2_BasicModuleContainer_MASK 	oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true,KeyStrokeListener.VK_F4);
		
		// vor dem oeffnen der positionsmaske die kopfmaske speichern
		this.add_oActionAgent(new actionSaveBeforeDaughterMask(oKopfMaskContainer));
	
		this.oNaviList_VPOS = onavigationList;

		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"BEARBEITEN_POSITION_IN_MASKE"));
		
		// validator prueft, ob der kopf bereits abgeschlossen wurde
//		String cQuery = "SELECT   NVL(ABGESCHLOSSEN,'N') FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN (SELECT   NVL(ID_VKOPF_RG,0) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_RG=#WERT#)";
//		this.add_IDValidator(new E2_Validator_ID_DBQuery(cQuery,bibALL.get_Array("N"),true, new MyE2_String("Der Vorgang wurde bereits abgeschlossen !")));
//
//		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_RG","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

		this.add_IDValidator(new ownValidator());
		
		this.setToolTipText(new MyE2_String("Bearbeiten eines (oder mehrerer) Positionen").CTrans());
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
	
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSRG_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten einer Position"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartEdit(new MyE2_String("Bearbeiten eine Position ..."));
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
			Vector<String> vID_actualSite = BSRG_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_vSelectedIDs_Unformated();
			int iActualPage = BSRG_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.get_iActualPage();

			oSaveMask.doSaveMask(true);
			
			BSRG_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.goToPage(null, iActualPage);
			BSRG_P_LIST_BT_EDIT_VORGANGSPOS.this.oNaviList_VPOS.set_SelectIDs(vID_actualSite);


		}
	}

}
