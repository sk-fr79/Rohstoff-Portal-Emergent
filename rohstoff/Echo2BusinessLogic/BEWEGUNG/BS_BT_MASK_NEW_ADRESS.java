package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
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
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class BS_BT_MASK_NEW_ADRESS extends MyE2_Button 
{

	private String HashKeyOf_AdressID_Field = null;

	//entweder  muss der neueingabebutton der Componentmap angehoeren oder das suchfeld muss dazugehoeren
	private MyE2_DB_MaskSearchField   oDBSearchField = null;

	
	public BS_BT_MASK_NEW_ADRESS(String cHashKeyOf_AdressID_Field) 
	{
		super(E2_ResourceIcon.get_RI("new.png"),true);
		
		this.HashKeyOf_AdressID_Field = cHashKeyOf_AdressID_Field;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_ADRESSE"));
		this.setToolTipText(new MyE2_String("Neuingabe einer Adresse").CTrans());
	}
	
	
	public BS_BT_MASK_NEW_ADRESS(String cHashKeyOf_AdressID_Field, MyE2_DB_MaskSearchField  oSearchField) 
	{
		super(E2_ResourceIcon.get_RI("new_mini.png"),true);
		
		this.HashKeyOf_AdressID_Field = cHashKeyOf_AdressID_Field;
		this.oDBSearchField = oSearchField;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_ADRESSE"));
		this.setToolTipText(new MyE2_String("Neuingabe einer Adresse").CTrans());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		FS_ModulContainer_MASK oAdressContainer = null;

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			MyE2_Button					oButton = 			(MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 			oMap = 				oButton.EXT().get_oComponentMAP();
			
			if (oMap==null)
			{
				oMap = BS_BT_MASK_NEW_ADRESS.this.oDBSearchField.EXT().get_oComponentMAP();
			}
			
			MyE2_DB_MaskSearchField 	oSearchAdress =  	(MyE2_DB_MaskSearchField)  oMap.get__Comp(BS_BT_MASK_NEW_ADRESS.this.HashKeyOf_AdressID_Field);
			
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
	}
	
	
	private class ownMaskSaver extends E2_SaveMASK
	{
		
		MyE2_DB_MaskSearchField oSearchAdress = null;
	
		public ownMaskSaver(E2_BasicModuleContainer_MASK maskContainer, MyE2_DB_MaskSearchField SearchAdress) 
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
			this.oSearchAdress.set_cActualMaskValue(this.get_cActualMaskID_Unformated(), true, true,true);
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
