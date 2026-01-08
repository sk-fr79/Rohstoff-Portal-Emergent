package panter.gmbh.Echo2.ListAndMask.List.Selektion;


import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_E2_DataSearch;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_NavigationList;
import panter.gmbh.Echo2.components.E2_ExpandableRow_NG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_TabbedPaneForFirstContainer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class E2_ListSelectorContainer extends E2_ExpandableRow_NG
{
	private E2_NavigationList   			oNaviList = null;
	private Vector<String> 					vSpeicherFuerLastSegmentationVector = null;
	private Component[]         			arrayComponents = null;
	private MyE2_Grid           			oContainer4Ruecksprungbutton = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	/*
	 * sammler fuer die Refresh-buttons, die im falle des sonderstatus inaktiv werden.
	 * Die einzige Moeglichkeit, wieder in den standard-modus zu kommen, ist der button <oButtonZurueckZumAltenZustand>
	 */
	private Vector<MyE2IF__Component>   vSetInactivComponents = new Vector<MyE2IF__Component>(); 
	
	public E2_ListSelectorContainer() throws myException
	{
		super(new MyE2_String("Selektionsbereich ausgeblendet ..."),null,null);
	}

	
	public E2_ListSelectorContainer(MyE2_String oInfoWhenClosed,Border oBorderopen, Border oBorderclosed) throws myException
	{
		super(oInfoWhenClosed,oBorderopen, oBorderclosed);
	}

	
	public void SONDERSTATUS_EINSCHALTEN(Vector<String> vIDs, String cAusgangsModul, String cZielModul)  throws myException
	{
		E2_BasicModuleContainer oContainer = new E2_RecursiveSearchParent_BasicModuleContainer(this).get_First_FoundContainer();
		
		if (oContainer != null)
		{
			Vector<E2_NavigationList>  vNaviLists = new E2_RecursiveSearch_NavigationList(oContainer).get_vNavigationLists();
			
			if (vNaviLists.size()==1)   //nur wenn eindeutig
			{
				this.oNaviList = vNaviLists.get(0);
			}
			
		}
		
		//jetzt checken, ob der status bereits ein Sonderstatus via Jumper ist
		if (this.oNaviList != null)
		{
			if (this.vSpeicherFuerLastSegmentationVector==null)
			{
				//dann den status der letzten selektion speichern
				this.vSpeicherFuerLastSegmentationVector = new Vector<String>();
				this.vSpeicherFuerLastSegmentationVector.addAll(this.oNaviList.get_vectorSegmentation());
				this.arrayComponents = new Component[this.getComponents().length];
				
				for (int i=0;i<this.getComponents().length;i++)
				{
					this.arrayComponents[i]=this.getComponents()[i];
				}
				
				this.removeAll();
				
				this.ADD(new MyE2_Label(new MyE2_String("Die momentane Liste basiert auf einer Sprung-Auswahl ....")),E2_INSETS.I_0_0_20_0);
				this.ADD(new ownButtonRestoreSelektion_IN_TargetModule(cZielModul),E2_INSETS.I_0_0_20_0);
				this.ADD(this.oContainer4Ruecksprungbutton);
					
				
				
				//jetzt noch die refresh-buttons ausschalten, da sonst verwirrung aufkommt
				E2_DataSearch  oSearchObject = new E2_RecursiveSearch_E2_DataSearch(oContainer).get_found_E2_DataSearch();
				if (oSearchObject != null)
				{
					if (oSearchObject.get_oButtonADDSearchField().isEnabled())  		this.vSetInactivComponents.add(oSearchObject.get_oButtonADDSearchField());
					if (oSearchObject.get_oButtonClearFieldsAndReload().isEnabled()) 	this.vSetInactivComponents.add(oSearchObject.get_oButtonClearFieldsAndReload());
					if (oSearchObject.get_oButtonSelectSearchFields().isEnabled())  	this.vSetInactivComponents.add(oSearchObject.get_oButtonSelectSearchFields());
					if (oSearchObject.get_oButtonStartSearch().isEnabled())  			this.vSetInactivComponents.add(oSearchObject.get_oButtonStartSearch());
					if (oSearchObject.get_oPopdownEinzelsuche().isEnabled())  			this.vSetInactivComponents.add(oSearchObject.get_oPopdownEinzelsuche());
				}
				if (oNaviList.get_ButtonReload().isEnabled())    this.vSetInactivComponents.add(oNaviList.get_ButtonReload());
				
				for (MyE2IF__Component oComp: this.vSetInactivComponents)
				{
					oComp.set_bEnabled_For_Edit(false);
				}
				this.oNaviList.set_bSortViaCompleteQuery(false);    //sortierung via temptable
				//----done---- jetzt noch die refresh-buttons ausschalten, da sonst verwirrung aufkommt
				
			}

			this.oNaviList.get_vectorSegmentation().removeAllElements();
			this.oNaviList.get_vectorSegmentation().addAll(vIDs);
			this.oNaviList.gotoSiteWithID_orFirstSite("");
			
			//2012-02-01: ruecksprungbutton auch beim evtl. 2. oder 3. aufruf einbauen
			this.oContainer4Ruecksprungbutton.removeAll();
			if (S.isFull(cAusgangsModul))
			{
				ownButtonZurueckZumAusgangsModul oButtonReturnToSender = new ownButtonZurueckZumAusgangsModul(cAusgangsModul);
				if (oButtonReturnToSender.get_bButtonAnzeigen())
				{
					this.oContainer4Ruecksprungbutton.add(oButtonReturnToSender);
				}
			}
			
			
		}
		
	}
	
	
	
	
	
	public void SONDERSTATUS_AUSSCHALTEN() throws myException
	{
		if (this.oNaviList != null)
		{
			if (this.vSpeicherFuerLastSegmentationVector!=null)
			{
				//dann den status der letzten selektion speichern
				this.oNaviList.get_vectorSegmentation().removeAllElements();
				this.oNaviList.get_vectorSegmentation().addAll(this.vSpeicherFuerLastSegmentationVector);
				this.oNaviList.gotoSiteWithID_orFirstSite("");
				this.vSpeicherFuerLastSegmentationVector = null;
				
				this.removeAll();
				
				for (int i=0;i<this.arrayComponents.length;i++)
				{
					this.ADD(this.arrayComponents[i]);
				}
				
				this.arrayComponents=null;
				
				
				//jetzt die refresh-buttons wieder einschalten
				for (MyE2IF__Component oComp: this.vSetInactivComponents)
				{
					oComp.set_bEnabled_For_Edit(true);
				}
				this.oNaviList.set_bSortViaCompleteQuery(true);
				
				this.vSetInactivComponents.removeAllElements();

			}
		}
	}



	private class ownButtonRestoreSelektion_IN_TargetModule extends MyE2_Button
	{

		public ownButtonRestoreSelektion_IN_TargetModule(String cNAME_OF_TARGET_MODUL) throws myException
		{
			super("");
			
			MyE2_String cButtonText = new MyE2_String("Zurück zur alten Auswahl ...");
			if (S.isFull(cNAME_OF_TARGET_MODUL))
			{
				MyE2_String cName4User_of_targetmodul = E2_MODULNAME_ENUM.get_hmModul_Kenner_And_Names().get(cNAME_OF_TARGET_MODUL);
				
				if (cName4User_of_targetmodul!=null)
				{
					cButtonText = new MyE2_String("Liste <",true,cName4User_of_targetmodul.CTrans(),false,"> wiederherstellen",true);
				}
			}
			this.set_Text(cButtonText);
			
			this.setFont(new E2_FontBold(0));
			this.setBackground(new E2_ColorHelpBackground());
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					E2_ListSelectorContainer.this.SONDERSTATUS_AUSSCHALTEN();
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der alte Selektor ist wieder aktiv ...")));
				}
			});
		}
	}
	
	
	
	
	
	private class ownButtonZurueckZumAusgangsModul extends MyE2_Button
	{
		private String 		cAusgangsModul = 	null;
		private boolean 	bButtonAnzeigen = 	true;
		
		public ownButtonZurueckZumAusgangsModul(String cAusgangsModul) throws myException
		{
			super(new MyE2_String("Zurück ins Ausgangsmodul"));
			
			this.setFont(new E2_FontBold());
			this.setBackground(new E2_ColorHelpBackground());

			this.cAusgangsModul = cAusgangsModul;
			if (S.isEmpty(this.cAusgangsModul))
			{
				throw new myException(this,"Error: Call without Startmodul is not allowed !");
			}

			HashMap<String, MyE2_String>  hmModule_Kenner_And_Names = E2_MODULNAME_ENUM.get_hmModul_Kenner_And_Names();
			
			if (hmModule_Kenner_And_Names!=null && hmModule_Kenner_And_Names.get(cAusgangsModul)!=null)
			{
				this.set_Text(new MyE2_String("Zurück zu <",true,hmModule_Kenner_And_Names.get(cAusgangsModul).CTrans(),false,">",false));
				this.add_oActionAgent(new ownActionAgent());
			}
			else
			{
				//sollte nie passieren
				this.bButtonAnzeigen = false;
			}
		}

		public boolean get_bButtonAnzeigen()
		{
			return bButtonAnzeigen;
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				//auf den tab mit dem cAusgangsmodul springen
				E2_TabbedPaneForFirstContainer  	oTabbedPane = 			bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION();
				//E2_NavigationList   				oNaviList_TargetModule = 	null;

				E2_TabbedPaneForFirstContainer.ContainerInfo oContainerInfo =  oTabbedPane.get_ContainerInfo(ownButtonZurueckZumAusgangsModul.this.cAusgangsModul);

				if (oContainerInfo==null)
				{
					//2. nachsehen, ob die liste aufgerufen werden darf ..
					HashMap<String, String> hmMenues = bibSES.get_ALLOWED_MENUES_4_USER();
					if (hmMenues.containsKey("echo2_starter:"+ownButtonZurueckZumAusgangsModul.this.cAusgangsModul))
					{
						oTabbedPane.add_TabModule("echo2_starter:"+ownButtonZurueckZumAusgangsModul.this.cAusgangsModul+"@@@@@"+hmMenues.get("echo2_starter:"+ownButtonZurueckZumAusgangsModul.this.cAusgangsModul),true);
					}
				}
				//nochmal probieren
				oContainerInfo =  oTabbedPane.get_ContainerInfo(ownButtonZurueckZumAusgangsModul.this.cAusgangsModul);
				
				if (oContainerInfo!=null)
				{
					oTabbedPane.setSelectedIndex(oContainerInfo.iTabPosOfContainer);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ziel-Modul konnte nicht gefunden werden !")));
				}
			}
		}
		
	}


}
