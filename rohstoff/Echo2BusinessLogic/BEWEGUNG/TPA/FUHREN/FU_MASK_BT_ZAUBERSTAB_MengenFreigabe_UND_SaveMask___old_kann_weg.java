package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_MaskSaveButton;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_BT_ZAUBERSTAB_MengenFreigabe_UND_SaveMask___old_kann_weg extends MyE2_Button
{
	
	public FU_MASK_BT_ZAUBERSTAB_MengenFreigabe_UND_SaveMask___old_kann_weg() 
	{
		super(E2_ResourceIcon.get_RI("wizard_plus_save.png"),true);
		this.add_oActionAgent(new ownActionAgent());
//		this.add_GlobalValidator(new ownValidator());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo)	throws myException
		{
			//zuerst die fuhre maske identifizieren
			FU_MASK_BT_ZAUBERSTAB_MengenFreigabe_UND_SaveMask___old_kann_weg oThis = FU_MASK_BT_ZAUBERSTAB_MengenFreigabe_UND_SaveMask___old_kann_weg.this;
			
			E2_BasicModuleContainer_MASK oMask = oThis.EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo();
			
			//jetzt den NUR-Freigabe-Zauberstab suchen
			FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben oButtonNurFreigabe = 
				new E2_RecursiveSearch_ComponentExt<FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben>(oMask, FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben.class).getSingleComponent();
				
				
			//2012-10-16: suche des save-buttons auf der maske, da sonst evtl. in darunterliegenden masken vorhandene save-buttons ebenfalls gefunden werden
			//alt	MyE2_Button     oMaskSaveButton = 
			//alt		new E2_RecursiveSearch_ComponentExt<E2_MaskSaveButton>(E2_MaskSaveButton.class).getFirstComponent();
			MyE2_Button     oMaskSaveButton = 
					new E2_RecursiveSearch_ComponentExt<E2_MaskSaveButton>(oMask, E2_MaskSaveButton.class).getFirstComponent();
				
				
			if (oButtonNurFreigabe!=null)
			{
				//nur wenn alles ok und freigabe erteilt, dann speichern
				boolean bCheckBoxLinksSelected = oThis.EXT().get_oComponentMAP().get_cActualDBValueFormated("PRUEFUNG_LADEMENGE").equals("Y");
				boolean bCheckBoxRechtsSelected = oThis.EXT().get_oComponentMAP().get_cActualDBValueFormated("PRUEFUNG_ABLADEMENGE").equals("Y");

				
				if (!(bCheckBoxLinksSelected && bCheckBoxRechtsSelected))    //sind bereits beide ausgewaehlt, dann wird nur gespeichert
				{
					oButtonNurFreigabe.do_OnlyCode_from_OtherActionAgent(execInfo.get_MyActionEvent());
				}
			
				bCheckBoxLinksSelected = oThis.EXT().get_oComponentMAP().get_cActualDBValueFormated("PRUEFUNG_LADEMENGE").equals("Y");
				bCheckBoxRechtsSelected = oThis.EXT().get_oComponentMAP().get_cActualDBValueFormated("PRUEFUNG_ABLADEMENGE").equals("Y");
	
				
				if (bibMSG.get_bIsOK()  && bCheckBoxLinksSelected && bCheckBoxRechtsSelected)
				{
					if (oMaskSaveButton!=null)
					{
						oMaskSaveButton.do_OnlyCode_from_OtherActionAgent(execInfo.get_MyActionEvent());
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Speicher-Funktion konnte nicht gefunden werden !!! (-1)")));
					}
				}
			
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Funktion konnte nicht gefunden werden !!! (-1)")));
			}
		}
		
	}

	
//	private class ownValidator extends XX_ActionValidator
//	{
//
//		@Override
//		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
//		{
//			E2_ComponentMAP  oMap = FU_MASK_BT_ZAUBERSTAB_MengenFreigabe_UND_SaveMask.this.EXT().get_oComponentMAP();
//			
//			boolean bCheckBoxLinksSelected = oMap.get_cActualDBValueFormated("PRUEFUNG_LADEMENGE").equals("Y");
//			boolean bCheckBoxRechtsSelected = oMap.get_cActualDBValueFormated("PRUEFUNG_ABLADEMENGE").equals("Y");
//
//			MyE2_MessageVector oMV = new MyE2_MessageVector();
//			
//			boolean bOK = (bCheckBoxLinksSelected && bCheckBoxRechtsSelected) || (!bCheckBoxLinksSelected && !bCheckBoxRechtsSelected);
//			
//			if (!bOK)
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nur möglich, wenn beide Seiten den gleichen Prüfstatus haben !!")));
//			}
//			
//			return oMV;
//		}
//
//		@Override
//		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
//		{
//			return null;
//		}
//		
//	}
	
}
