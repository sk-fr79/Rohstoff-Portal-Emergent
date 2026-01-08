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
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben extends MyE2_Button 
{

	private boolean bSaveAfterFreigabe = false;
	
	
	public FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben(boolean SaveAfterFreigabe) 
	{
		super(E2_ResourceIcon.get_RI(SaveAfterFreigabe?"wizard_plus_save.png":"wizard.png"),true);
		
		this.bSaveAfterFreigabe = SaveAfterFreigabe;
		this.add_oActionAgent(new ownActionAgent());

	}

	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo)	throws myException
		{
			/*
			 * betroffene felder:
			 */
			FU_MASK_ComponentMAP  oMAP = (FU_MASK_ComponentMAP)FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben.this.EXT().get_oComponentMAP();
			
			MyE2_DB_CheckBox  oCB_Mengenfreigabe_EK = ((MyE2_DB_CheckBox)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE));
			MyE2_DB_CheckBox  oCB_Mengenfreigabe_VK = ((MyE2_DB_CheckBox)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE));
			MyE2_DB_CheckBox  oCB_Preisfreigabe_EK = ((MyE2_DB_CheckBox)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS));
			MyE2_DB_CheckBox  oCB_Preisfreigabe_VK = ((MyE2_DB_CheckBox)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS));
			
			boolean bMenge_EK = oCB_Mengenfreigabe_EK.isSelected();
			boolean bMenge_VK = oCB_Mengenfreigabe_VK.isSelected();
			boolean bPreis_EK = oCB_Preisfreigabe_EK.isSelected();
			boolean bPreis_VK = oCB_Preisfreigabe_VK.isSelected();
			

			FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben oThis = FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben.this;
			E2_BasicModuleContainer_MASK oMask = oThis.EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo();

			//2012-10-16: suche des save-buttons auf der maske, da sonst evtl. in darunterliegenden masken vorhandene save-buttons ebenfalls gefunden werden
			//alt	MyE2_Button     oMaskSaveButton = 
			//alt		new E2_RecursiveSearch_ComponentExt<E2_MaskSaveButton>(E2_MaskSaveButton.class).getFirstComponent();
			MyE2_Button     oMaskSaveButton = new E2_RecursiveSearch_ComponentExt<E2_MaskSaveButton>(oMask, E2_MaskSaveButton.class).getFirstComponent();


			//falls alles angehakt ist und der schalter als "auch speichern" definiert ist, muss hier gleich gespeichert werden
			if (oThis.bSaveAfterFreigabe)
			{
				if (bMenge_EK && bMenge_VK && bPreis_EK && bPreis_VK)
				{
					if (oMaskSaveButton!=null)
					{
						oMaskSaveButton.do_OnlyCode_from_OtherActionAgent(execInfo.get_MyActionEvent());
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Speicher-Funktion konnte nicht gefunden werden !!! (-1)")));
					}
					return;
				}
			}
			
			

			boolean bOK = false;
			if (	(bMenge_EK && bMenge_VK && bPreis_EK && bPreis_VK) || 
					(!bMenge_EK && !bMenge_VK && !bPreis_EK && !bPreis_VK) ||
					(bMenge_EK && bMenge_VK && !bPreis_EK && !bPreis_VK)
				)
			{
				bOK = true;
			}
			if (!bOK)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nur möglich, wenn beide Seiten den gleichen Prüfstatus haben !!")));
				return;
			}
			
			// jetzt die moeglichkeiten pruefen. Wenn noch gar nichts angeklickt ist, dann sollten die mengenknoepfe bei Bedarf auch die Preisknoepfe ausloesen
			if (!bMenge_EK)   //bedeutete alle 4 schalter sind aus
			{
				if (bibALL.get_RECORD_USER().is_MENGENABSCHLUSS_FUHRE_OK_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES())
				{
					oCB_Mengenfreigabe_EK.setSelected(true);
					oCB_Mengenfreigabe_VK.setSelected(true);
					oCB_Mengenfreigabe_EK.doActionPassiv();
					oCB_Mengenfreigabe_VK.doActionPassiv();
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben nicht die Berechtigung zur Mengenfreigabe !!")));
				}
			}
			else if (!bPreis_EK)  //die preisschalter sind aus, mengenschalter sind an
			{
				if (bibALL.get_RECORD_USER().is_PREISABSCHLUSS_FUHRE_OK_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES())
				{
					oCB_Preisfreigabe_EK.setSelected(true);
					oCB_Preisfreigabe_VK.setSelected(true);
					oCB_Preisfreigabe_EK.doActionPassiv();
					oCB_Preisfreigabe_VK.doActionPassiv();
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben nicht die Berechtigung zur Preisfreigabe !!")));
				}
			}
			else if (bPreis_EK)  //alle schalter sind an, alle ausschalten
			{
				oCB_Preisfreigabe_EK.setSelected(false);
				oCB_Preisfreigabe_VK.setSelected(false);
				oCB_Mengenfreigabe_EK.setSelected(false);
				oCB_Mengenfreigabe_VK.setSelected(false);
				
				oCB_Preisfreigabe_EK.doActionPassiv();
				oCB_Preisfreigabe_VK.doActionPassiv();
				oCB_Mengenfreigabe_EK.doActionPassiv();
				oCB_Mengenfreigabe_VK.doActionPassiv();
			}
			else if (bMenge_EK) //mengen-schalter sind an
			{
				oCB_Mengenfreigabe_EK.setSelected(false);
				oCB_Mengenfreigabe_VK.setSelected(false);
				oCB_Mengenfreigabe_EK.doActionPassiv();
				oCB_Mengenfreigabe_VK.doActionPassiv();
			}
			
			
			
			
			//jetzt den status der symmetrie pruefen und korrigieren
			//es kann durch die aufmatische preisfreigabe passieren, dass nur eine seite mit dem zauberstab freigegeben wird 
			bMenge_EK = oCB_Mengenfreigabe_EK.isSelected();
			bMenge_VK = oCB_Mengenfreigabe_VK.isSelected();
			bPreis_EK = oCB_Preisfreigabe_EK.isSelected();
			bPreis_VK = oCB_Preisfreigabe_VK.isSelected();

			if ((bPreis_EK && !bPreis_VK)  ||  (!bPreis_EK && bPreis_VK)  )
			{
				//dan beide auf false setzen
				if (bPreis_EK)
				{
					oCB_Preisfreigabe_EK.setSelected(false);
					oCB_Preisfreigabe_EK.doActionPassiv();
				}
				//dan beide auf false setzen
				if (bPreis_VK)
				{
					oCB_Preisfreigabe_VK.setSelected(false);
					oCB_Preisfreigabe_VK.doActionPassiv();
				}
			}
			
			if ((bMenge_EK && !bMenge_VK)  ||  (!bMenge_EK && bMenge_VK)  )
			{
				//dan beide auf false setzen
				if (bMenge_EK)
				{
					oCB_Mengenfreigabe_EK.setSelected(false);
					oCB_Mengenfreigabe_EK.doActionPassiv();
				}
				//dan beide auf false setzen
				if (bMenge_VK)
				{
					oCB_Mengenfreigabe_VK.setSelected(false);
					oCB_Mengenfreigabe_VK.doActionPassiv();
				}
			}
			//jetzt sind nur noch symmetrische zustaende aktiv
			
			
			
			
			//jetzt speichern (falls kombibutton)
			if (FU_MASK_BT_ZAUBERSTAB_Beide_MengenFreigaben.this.bSaveAfterFreigabe)
			{
				bMenge_EK = oCB_Mengenfreigabe_EK.isSelected();
				bMenge_VK = oCB_Mengenfreigabe_VK.isSelected();
				bPreis_EK = oCB_Preisfreigabe_EK.isSelected();
				bPreis_VK = oCB_Preisfreigabe_VK.isSelected();

				boolean bBeideMengenFreigegeben = bMenge_EK && bMenge_VK;
				boolean bBeidePreiseFreigegeben = bPreis_EK && bPreis_VK;
				boolean bBeidePreiseOffen = (!bPreis_EK) && (!bPreis_VK);
				
				
				if (bibMSG.get_bIsOK())
				{
					if (bBeideMengenFreigegeben && (bBeidePreiseFreigegeben || bBeidePreiseOffen))
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
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Speichern ist nicht möglich, da die Prüfschalter assymetrisch gesetzt sind!")));
					}
				}
			}
		}
	}

}
