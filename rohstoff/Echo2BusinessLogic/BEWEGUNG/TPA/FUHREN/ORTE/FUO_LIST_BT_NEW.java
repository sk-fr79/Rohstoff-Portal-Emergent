package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer.XX_BuildAddonComponents_4_Dialog;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import echopointng.KeyStrokeListener;

public class FUO_LIST_BT_NEW extends MyE2_ButtonWithKey
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;
	private E2_NavigationList               oNaviList = null;
	private E2_BasicModuleContainer_MASK 	oMaskContainer = null;

	public FUO_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.VK_F8);

		this.oFUO_DaughterComponent = 	FUO_DaugherComponent;
		this.oNaviList = 				onavigationList;
		this.oMaskContainer = 			omaskContainer;

		//2011-11-18: vor dem oeffenen einer neuerfassung eines fuhrenorts, muss die fuhre gespeichert werden 
		this.add_oActionAgent(new ownActionSaveFuhre());
		
		//this.add_oActionAgent(new ownActionAgentNeueingabe(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_FUHRENZUSATZORT"));
		
		//2012-01-04: validierer der verhindert, dass ein fuhrenort angelegt wird, wenn es einen UMA-vertrag gibt
		this.add_GlobalValidator(new ownValidator_nur_ohne_uma());
		
	}
	
	
	//2011-11-18: vor dem neuerfassen eines fuhrenortes muss die fuhre gepeichert werden, damit in der pruefung der maske kein fehler auftaucht
	//            wenn es ein leeres sortenfeld gibt.
	private class ownActionSaveFuhre extends XX_ActionAgent
	{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new ownConfirmcontainer().show_POPUP_BOX();
			
		}

	
		private class ownConfirmcontainer extends E2_ConfirmBasicModuleContainer
		{
			public ownConfirmcontainer() throws myException
			{
				super(	new MyE2_String("Achtung !"), 
						new MyE2_String("Bitte beachten ...."), 
						new MyE2_String("Dieser Vorgang speichert diese Fuhre sofort!"), new ownAddonBuilder(),
						new MyE2_String("OK-Speichern"), new MyE2_String("Nein - Abbruch"), new Extent(400), new Extent(400));
				
				this.add_ActionAgentForOK_AfterCloseEvent(new ownActionOK());
				this.add_ActionAgentForOK_AfterCloseEvent(new ownActionAgentNeueingabe(FUO_LIST_BT_NEW.this.oNaviList,FUO_LIST_BT_NEW.this.oMaskContainer,FUO_LIST_BT_NEW.this.get_oButton()));
				
				this.get_oButtonCancel().add_oActionAgent(new ownActionCancel());
			}
		}

		private class ownAddonBuilder extends XX_BuildAddonComponents_4_Dialog
		{

			@Override
			public Component build_AddonComponent(E2_ConfirmBasicModuleContainer oConfirmContainer)	throws myException
			{
				return null;
			}

			@Override
			public Vector<XX_ActionAgent> get_vActionAgents_from_AddonComponent(E2_ConfirmBasicModuleContainer oConfirmContainer) throws myException
			{
				return new Vector<XX_ActionAgent>();
			}
		}
		
		
		private class ownActionOK extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{

				FU_MASK_ComponentMAP  oMAP =  FUO_LIST_BT_NEW.this.oFUO_DaughterComponent.get_oFU_MASK_ComponentMAP();
				
				try 
				{
					
					RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				
					if (oMAP.get_bIs_Edit())
					{
						new E2_SaveMaskStandard(oMAP.get_oModulContainerMASK_This_BelongsTo(),null).doSaveMask(true);					
		
						//jetzt die liste manuell refreshen
						boolean bRefreshWasOK = true;

						
						E2_BasicModuleContainer_MASK oMaskContainer = oMAP.get_oModulContainerMASK_This_BelongsTo();
						
						//jetzt den rufenden Container 
						E2_BasicModuleContainer     oContainerWithNavilist = oMaskContainer.get_vBasicContainerHierarchie().get(oMaskContainer.get_vBasicContainerHierarchie().size()-1);

						if (oContainerWithNavilist != null)
						{
							Vector<E2_NavigationList>  vNaviLists = new E2_RecursiveSearch_NavigationList(oContainerWithNavilist).get_vNavigationLists();
							
							//es muss eine navilist eindeutig gefunden werden
							
							if (vNaviLists.size()==1)
							{
								E2_NavigationList  oNaviList = vNaviLists.get(0);
								
								SQLFieldMAP  oSQLFieldMap = oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP();
								
								if (oSQLFieldMap!=null)
								{
									String cLeadingTableOfNaviList = oSQLFieldMap.get_cMAIN_TABLE();
									
									if (cLeadingTableOfNaviList.toUpperCase().equals("JT_VPOS_TPA_FUHRE"))
									{
										oNaviList.Refresh_ComponentMAP(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), E2_ComponentMAP.STATUS_VIEW);
									}
									else if (cLeadingTableOfNaviList.toUpperCase().equals("JT_VPOS_TPA"))
									{
										oNaviList.Refresh_ComponentMAP(recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_ID_VPOS_TPA_cUF(), E2_ComponentMAP.STATUS_VIEW);
									}
									else if (cLeadingTableOfNaviList.toUpperCase().equals("JT_VKOPF_TPA"))
									{
										oNaviList.Refresh_ComponentMAP(recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_ID_VKOPF_TPA_cUF(), E2_ComponentMAP.STATUS_VIEW);
									}
									else if (cLeadingTableOfNaviList.toUpperCase().equals("JT_FBAM"))
									{
										//hier muss nichts aktualisiert werden
									}
									else
									{
										bRefreshWasOK = false;
									}
								}
							}
						}
						
						if (!bRefreshWasOK)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mit der Selektion wurde die Maske gespeichert, die zugrunde liegende Liste konnte nicht aktualisiert werden. Bitte Info an Entwickler !")));
						}
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Unbekannter Fehler. Bitte Info an Entwickler !")));
				}
			}
		}
		
		

		private class ownActionCancel extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
			}
		}

	}
	

	
	
	private class ownActionAgentNeueingabe extends ButtonActionAgentNEW
	{
		public ownActionAgentNeueingabe(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Neueingabe eines zusätzlichen Fuhrenortes "), onavigationList, omaskContainer, oownButton, null, null);
			this.get_oButtonMaskSave().add_oActionAgent(new ownActionReloadFuhrenMaske());
			this.get_oButtonMaskSave().add_oActionAgent(new ownActionReloadBasisNaviLists());
		}
	}
	
	
	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}

	

	private class ownActionReloadFuhrenMaske extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_BasicModuleContainer_MASK  oMaskFuhre = FUO_LIST_BT_NEW.this.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo();
			E2_ComponentMAP   			  oMapFuhre = FUO_LIST_BT_NEW.this.oFUO_DaughterComponent.EXT().get_oComponentMAP();
			
			if (oMaskFuhre == null)
			{
				throw new myException(this,"Cannot save changed fuhrenmask !");
			}
			
			oMapFuhre.update_ResultMAP_and_MaskComponents(null);
			
			
			E2_ComponentMAP 			oLeadingMaskMAP = 				oMaskFuhre.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
			/*
			 * !!!!!!!!!!!!!!!!
			 * die maske muss hier unbedingt geladen werden, damit neu eingetragene abzuege nicht im status NEW mehrmals gespeichert werden
			 * !!!!!!!!!!!!!!!!
			 * 
			 * 
			 * je nachdem, ob der vorgang "Fuhrenort-speichern" im Transportauftrag oder der fuhre stattfindet, muss die maske mit dem jeweils
			 * fuehrenden id-wert geladen werden
			 */
			String cID_FUHRE_oder_tpa_pos = oLeadingMaskMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			oMaskFuhre.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID_FUHRE_oder_tpa_pos);

			
			
		}
		
	}

	
	/*
	 * actionagent, der alle ausgangsnavilists ausser der in der maske eingebetteten fuhrenort-liste refresht
	 */
	private class ownActionReloadBasisNaviLists extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			Vector<E2_NavigationList> oNavis = new E2_RecursiveSearch_NavigationList().get_vNavigationLists();
			for (int i=0;i<oNavis.size();i++)
			{
				if (!oNavis.get(i).get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE().equals("JT_VPOS_TPA_FUHRE_ORT"))
				{
					oNavis.get(i)._REBUILD_ACTUAL_SITE(null);
				}
			}
		}
	}

	
	//2012-01-04: validierer der verhindert, dass ein fuhrenort angelegt wird, wenn es einen UMA-vertrag gibt
	private class ownValidator_nur_ohne_uma extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			//ueberpruefung des maskenfeldes auf der fuhrenmaske reicht aus um sicherzustellen, dass die bedingung erfuellt ist, da automatisch gespeichert wird
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			FU_MASK_ComponentMAP  oMAP =  				FUO_LIST_BT_NEW.this.oFUO_DaughterComponent.get_oFU_MASK_ComponentMAP();

			MyE2IF__DB_Component     umaTextComponent = 	oMAP.get__DBComp("ID_UMA_KONTRAKT");
			
			if (S.isFull(umaTextComponent.get_cActualMaskValue()))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei Fuhren zu UMA-Kontrakten kann es keine Zusatzorte geben !!")));
			}
			return oMV;
		}
		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException	{return null;}
		
	}
	
	
}
