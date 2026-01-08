package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonCancelMask;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class BST_K_MASK_BT_NEW_ADRESS extends MyE2_Button 
{


	
	public BST_K_MASK_BT_NEW_ADRESS(BST_K_MASK__ModulContainer MaskContainer) 
	{
		super(E2_ResourceIcon.get_RI("new.png"),true);
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(MaskContainer.get_MODUL_IDENTIFIER(),"NEUEINGABE_ADRESSE"));
		this.setToolTipText(new MyE2_String("Neuingabe einer Adresse").CTrans());
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		FS_ModulContainer_MASK oAdressContainer = null;

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			
			try
			{
				MyE2_Button			oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
				E2_ComponentMAP 		oMap = oButton.EXT().get_oComponentMAP();
				DB_SEARCH_Adress 	oSearchAdress =  (DB_SEARCH_Adress)  oMap.get__Comp("ID_ADRESSE");
				
				String cID = bibALL.null2leer(oSearchAdress.get_oTextFieldForSearchInput().getText());
				
				cID = bibALL.ReplaceTeilString(cID,".","");   // evtl.tausenderpunkt wegnehmen 

				this.oAdressContainer = new FS_ModulContainer_MASK();

				ownMaskSaver oMaskSaver=new ownMaskSaver(oAdressContainer,oSearchAdress);
				E2_ComponentGroupHorizontal oButtonGroup = new E2_ComponentGroupHorizontal(0,
											new maskButtonSaveMask(this.oAdressContainer,oMaskSaver,null, null), 
											new ownCancelButton(oAdressContainer), E2_INSETS.I_0_2_10_2);
				this.oAdressContainer.get_oRowForButtons().add(oButtonGroup);
				
				
				if (bibALL.isEmpty(cID))
				{
					/*
					 * Maske einstellen
					 */
					E2_vCombinedComponentMAPs vComponentMAPs = oAdressContainer.get_vCombinedComponentMAPs();
					vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
					
					oAdressContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Neue Adresse eingeben !!!"));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Neueingabe ist nur bei leerem Adressfeld möglich"));
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error opening Adress-Mask !!",false)));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
	
	
	private class ownMaskSaver extends E2_SaveMASK
	{
		
		DB_SEARCH_Adress oSearchAdress = null;
	
		public ownMaskSaver(E2_BasicModuleContainer_MASK maskContainer, DB_SEARCH_Adress SearchAdress) 
		{
			super(maskContainer, null);
			this.oSearchAdress = SearchAdress;
		}

		public boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK oMaskContainer, E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps, String cActualMaskStatus) 
		{
			return true;
		}

		public void actionAfterSaveMask() throws myException
		{
			this.oSearchAdress.set_cActualMaskValue(this.get_cActualMaskID_Unformated(), true,true,true);
		}
		
	}
	
	
	private class ownCancelButton extends maskButtonCancelMask
	{
		public ownCancelButton(E2_BasicModuleContainer_MASK maskContainer) 
		{
			super(maskContainer);
		}
		public boolean doActionAfterCancelMask() 
		{
			return true;
		}
		
	}
	
	
}
