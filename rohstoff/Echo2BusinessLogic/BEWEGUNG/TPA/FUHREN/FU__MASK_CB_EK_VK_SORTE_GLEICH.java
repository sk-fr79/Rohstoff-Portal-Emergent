package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer.XX_BuildAddonComponents_4_Dialog;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_NavigationList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU__MASK_CB_EK_VK_SORTE_GLEICH extends MyE2_DB_CheckBox
{

	public FU__MASK_CB_EK_VK_SORTE_GLEICH(SQLField osqlField) throws myException
	{
		super(osqlField);
		
		this.add_GlobalAUTHValidator_AUTO("TRENNEN_EK_VK_SORTE");
		

		this.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				//bei neuerfassung nicht direkt speichern
				if (FU__MASK_CB_EK_VK_SORTE_GLEICH.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
				{
					return;
				}
				
				new ownConfirmcontainer().show_POPUP_BOX();
			}
		});
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

			E2_ComponentMAP  oMAP =  FU__MASK_CB_EK_VK_SORTE_GLEICH.this.EXT().get_oComponentMAP();
			
			try 
			{
				
				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
				if (oMAP.get_bIs_Edit())
				{
					new E2_SaveMaskStandard(oMAP.get_oModulContainerMASK_This_BelongsTo(),null).doSaveMask(true);					
	
					if (bibMSG.get_bHasAlarms())   //und wenn das nicht geht, ...
					{
						//wert wieder zurueck und raus
						FU__MASK_CB_EK_VK_SORTE_GLEICH.this.setSelected(!FU__MASK_CB_EK_VK_SORTE_GLEICH.this.isSelected());
						return;
					}
					
					
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
			FU__MASK_CB_EK_VK_SORTE_GLEICH.this.setSelected(!FU__MASK_CB_EK_VK_SORTE_GLEICH.this.isSelected());
		}
	}
	
	
}
